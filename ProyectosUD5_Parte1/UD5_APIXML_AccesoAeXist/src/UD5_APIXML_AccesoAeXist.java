// Ejemplo de acceso a BD eXist desde Java con API XML:DB

import java.io.BufferedReader;
import java.io.FileReader;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;

public class UD5_APIXML_AccesoAeXist {
	
	public static void main(String[] args) throws Exception {
		
		consultaDatos();							// Ejecuta una consulta con sintaxis XPath
		//consultaDesdeFichero("miconsulta.xq");		// Ejecuta una consulta con sintaxis XQuery que est� en un fichero
			
  	}  // fin main	
	
	public static void consultaDatos() throws Exception {
  		Collection col = null;  
	  	
  		// Define URI a la Colecci�n de documentos XML
		String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/ColeccionesXML/ColeccionPruebas", usu = "admin", pwd = "alumno"; // indicar desde donde empezar buscar
		try {
			// Carga el driver para eXist
			Class<?> c = Class.forName("org.exist.xmldb.DatabaseImpl");			
			Database bd = (Database) c.getDeclaredConstructor().newInstance();
			DatabaseManager.registerDatabase(bd);
			
			// Accede a la colecci�n de documentos
			col = DatabaseManager.getCollection(URI, usu, pwd);
			
			if (col == null) {
				System.out.println(" *** LA COLECCION NO EXISTE. ***");
			} else {
				// Especifica el servicio
				XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
			
				// Indica al servicio la consulta a realizar
				String consultaXPath = "/EMPLEADOS/EMP_ROW[EMP_NO=7782]";
				ResourceSet resultSet = servicio.query(consultaXPath);
				System.out.println("EJECUTA LA CONSULTA: " + consultaXPath);
				
				// Recorre los datos obtenidos en el ResourceSet (conjunto de recursos)
				ResourceIterator results = resultSet.getIterator();
				if (!results.hasMoreResources()) {
					System.out.println(" LA CONSULTA NO DEVUELVE NADA.");
				}
				while (results.hasMoreResources()) {
					Resource res = results.nextResource();
					System.out.println((String) res.getContent());
				}
				System.out.println("-------------------------- \n");
			}
			
		} catch (XMLDBException e) {
		         System.err.println("XML:DB Exception occured " + e.errorCode);
		        		 
		} finally {	if (col != null) { col.close(); } }
		
	}  // fin consultaDatos
  
  
	public static void consultaDesdeFichero(String fichero) throws Exception {
		Collection col = null;
		try {
			Class<?> c = Class.forName("org.exist.xmldb.DatabaseImpl");			
			Database bd = (Database) c.getDeclaredConstructor().newInstance();
			DatabaseManager.registerDatabase(bd);	
			String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/ColeccionesXML/BDProductosXML", usu = "admin", pwd = "alumno"; 
			col = DatabaseManager.getCollection(URI, usu, pwd);
			
			System.out.println("Convirtiendo el fichero a cadena...");
			BufferedReader entrada = new BufferedReader(new FileReader(fichero));
			String linea = null;
			StringBuilder stringBuilder = new StringBuilder();
			String salto = System.getProperty("line.separator"); // es el salto de l�nea \n

			while ((linea = entrada.readLine()) != null) {
				stringBuilder.append(linea);
				stringBuilder.append(salto);
			}
			String consultaXQuery = stringBuilder.toString();  // Asigna el texto leido al String consulta
			entrada.close();
			
			// Ejecutar consulta
			System.out.println("EJECUTA LA CONSULTA: " + consultaXQuery);
			XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
			ResourceSet result = servicio.query(consultaXQuery);
			ResourceIterator i; // se utiliza para recorrer un set de recursos
			i = result.getIterator();
			if (!i.hasMoreResources()) {
				System.out.println(" LA CONSULTA NO DEVUELVE NADA.");
			}
			while (i.hasMoreResources()) {
				Resource r = i.nextResource();
				System.out.println("Elemento: " + (String) r.getContent());
			}
			
			} catch (ClassNotFoundException ex) {
				System.out.println(" ERROR EN EL DRIVER. COMPRUEBA CONECTORES.");
			} catch (InstantiationException ex) {
				System.out.println("Error al crear Instancia de la BD (Database) cl.newInstance()");
			} catch (IllegalAccessException ex) {
				System.out.println("Error al crear Instancia de la BD (Database) cl.newInstance()");
			} catch (XMLDBException ex) {
				ex.printStackTrace();
			}
		
	}  // fin consultaDesdeFichero
   
} // fin clase
