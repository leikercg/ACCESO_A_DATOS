// Ejemplos de operaciones con Colecciones y documentos sobre BD eXist desde Java con API XML:DB

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;

public class UD5_APIXML_OperaCDocEneXist {
	
	public static void main(String[] args) throws Exception {
		verColecciones();
		verRecursosColecciones();
		crearColeccionyDocumento("nuevaColeccion");
		eliminarDocumento("nuevaColeccion", "NUEVAS_ZONAS.xml");
		eliminarColeccion("nuevaColeccion");
		
  	}  // fin main
	
	
	public static void verColecciones() throws Exception { 
	  	Collection col = null;
		try {
			Class<?> c = Class.forName("org.exist.xmldb.DatabaseImpl");			
			Database bd = (Database) c.getDeclaredConstructor().newInstance();
			DatabaseManager.registerDatabase(bd);
			
			String URI = "xmldb:exist://localhost:8090/exist/xmlrpc/db/", usu = "admin", pwd = "Ad1rectory";
			col = DatabaseManager.getCollection(URI, usu, pwd);
			
			// Obtiene el número de colecciones en la BD
			System.out.println("Número de colecciones: " + col.getChildCollectionCount());
			// Obtiene la lista de colecciones en la BD
			String[] colecciones = col.listChildCollections();
			for (int j = 0; j < colecciones.length; j++) {
				System.out.println(colecciones[j]);
				// Collection colecc = col.getChildCollection(colecciones[j]);
			}
		} catch (ClassNotFoundException ex) {
			System.out.println(" ERROR EN EL DRIVER. COMPRUEBA CONECTORES.");
		} catch (IllegalAccessException ex) {
			System.out.println("Error al crear Instancia de la BD (Database) cl.newInstance()");
		} catch (XMLDBException ex) {
			Logger.getLogger(UD5_APIXML_OperaCDocEneXist.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(UD5_APIXML_OperaCDocEneXist.class.getName()).log(Level.SEVERE, null, ex);
		}
	}  // fin verColecciones
	
	
	public static void verRecursosColecciones() throws Exception { 
	  	Collection col = null;
		try {
			Class<?> c = Class.forName("org.exist.xmldb.DatabaseImpl");			
			Database bd = (Database) c.getDeclaredConstructor().newInstance();
			DatabaseManager.registerDatabase(bd);
			
			String URI = "xmldb:exist://localhost:8090/exist/xmlrpc/db/", usu = "admin", pwd = "Ad1rectory";
			col = DatabaseManager.getCollection(URI, usu, pwd);
			// Obtiene el número y lista de colecciones
			System.out.println("Número de colecciones: " + col.getChildCollectionCount());
			String[] colecciones = col.listChildCollections();
			
			// Recorre las colecciones mostrando la lista de recursos
			for (int j = 0; j < colecciones.length; j++) {
				System.out.println("-------------------------- ");
				System.out.println(colecciones[j]);
				Collection colecc = col.getChildCollection(colecciones[j]);
				String[] lista = colecc.listResources();
				for (int i = 0; i < lista.length; i++) {
					Resource res = (Resource) colecc.getResource(lista[i]);
					System.out.println("ID del documento: " + res.getId());
					System.out.println("Contenido del documento:\n " + res.getContent());
				}
			}
		} catch (ClassNotFoundException ex) {
			System.out.println(" ERROR EN EL DRIVER. COMPRUEBA CONECTORES.");
		} catch (IllegalAccessException ex) {
			System.out.println("Error al crear Instancia de la BD (Database) cl.newInstance()");
		} catch (XMLDBException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		}
	}  // fin verRecursosColecciones
	
	
	public static void crearColeccionyDocumento(String nombreColecc) throws XMLDBException { 
		Collection col = null;
		try {
			Class<?> c = Class.forName("org.exist.xmldb.DatabaseImpl");			
			Database bd = (Database) c.getDeclaredConstructor().newInstance();
			DatabaseManager.registerDatabase(bd);
			
			String URI = "xmldb:exist://localhost:8090/exist/xmlrpc/db/", usu = "admin", pwd = "Ad1rectory";
			col = DatabaseManager.getCollection(URI, usu, pwd);
			
			// CREA UNA NUEVA COLECCION con el nombre pasado como parámetro al método y DENTRO DEL CONTEXTO definido en col
			if (col != null) {
				CollectionManagementService mgtService = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");
				mgtService.createCollection(nombreColecc);
				System.out.println(" *** COLECCION CREADA: " + nombreColecc);
			}
			
			
			// Sube un documento a la colección que acaba de crear
			URI = "xmldb:exist://localhost:8090/exist/xmlrpc/db/" + nombreColecc;	// Pone el contexto en la colección creada
			col = DatabaseManager.getCollection(URI, usu, pwd);
			File archivo = new File("NUEVAS_ZONAS.xml");			// El fichero está ubicado en la carpeta del proyecto
			if (!archivo.canRead()) {
				System.out.println("ERROR AL LEER EL FICHERO");
			} else {
				// Además del nombre del fichero al método createREsource debemos indicarle el tipo de recurso.
				// Si es un fichero binario ponemos "BinaryResource" y si es documento XML ponemos "XMLResource"
				Resource nuevoRecurso = col.createResource(archivo.getName(), "XMLResource");
				nuevoRecurso.setContent(archivo);
				col.storeResource(nuevoRecurso);
				System.out.println("FICHERO AÑADIDO A LA COLECCION " + nombreColecc);
			}
					
		} catch (Exception e) {
			System.out.println("Error al inicializar la BD eXist");
		}	
	}  // fin  crearColeccionyDocumento
	
	
	public static void eliminarDocumento(String colecc, String fichero) throws XMLDBException, Exception {
		Collection col = null;
		try {
			Class<?> c = Class.forName("org.exist.xmldb.DatabaseImpl");			
			Database bd = (Database) c.getDeclaredConstructor().newInstance();
			DatabaseManager.registerDatabase(bd);
			
			String URI = "xmldb:exist://localhost:8090/exist/xmlrpc/db/" + colecc +"/", usu = "admin", pwd = "Ad1rectory";
			System.out.println("URI: " + URI);
			col = DatabaseManager.getCollection(URI, usu, pwd);
			System.out.println("Voy a eliminar el fichero: " + fichero + " de la colección: " + colecc );
			Resource recursoAEliminar = col.getResource(fichero);		// Obtiene el recurso (fichero XML)
			col.removeResource(recursoAEliminar);						// Elimina el recurso (fichero XML)
			System.out.println("FICHERO BORRADO.");
			
		} catch (NullPointerException e) {
			System.out.println("No se puede borrar. No se encuentra.");
		} catch (ClassNotFoundException ex) {
			System.out.println("ERROR DRIVER.");
		} catch (InstantiationException ex) {
			System.out.println("ERROR AL CREAR LA INSTANCIA.");
		} catch (IllegalAccessException ex) {
			System.out.println("ERROR AL CREAR LA INSTANCIA.");
		}
			
	}  // fin eliminarDocumento
	
	
	public static void eliminarColeccion(String colecc) throws XMLDBException {
		Collection col = null;
		try {
			Class<?> c = Class.forName("org.exist.xmldb.DatabaseImpl");			
			Database bd = (Database) c.getDeclaredConstructor().newInstance();
			DatabaseManager.registerDatabase(bd);
			
			String URI = "xmldb:exist://localhost:8090/exist/xmlrpc/db/", usu = "admin", pwd = "Ad1rectory";
			col = DatabaseManager.getCollection(URI, usu, pwd);
			CollectionManagementService mgtService = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");
			mgtService.removeCollection(colecc);
			System.out.println(" *** COLECCION BORRADA. ***");
		} catch (Exception e) {
			System.out.println("Error al inicializar la BD eXist");
		}
		
	}  // fin eliminarColeccion
	
	
} // fin clase
