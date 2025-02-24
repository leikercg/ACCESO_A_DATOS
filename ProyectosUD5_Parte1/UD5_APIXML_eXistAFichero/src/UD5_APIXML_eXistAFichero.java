// Ejemplo de guardar documento desde BD eXist a Fichero con API XML:DB

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;

public class UD5_APIXML_eXistAFichero {
	
	public static void main(String[] args) throws Exception {
		
		documentoBDaFichero("zonas.xml");	// Crea fichero XML desde documento XML de la BD
		consultaBDaFichero();				// Crea fichero XML desde una consulta a la BD
				
  	}  // fin main
	
	
	public static void documentoBDaFichero(String doc) throws TransformerConfigurationException, TransformerException, Exception {

		Collection col = null;
		try {
			Class<?> c = Class.forName("org.exist.xmldb.DatabaseImpl");			
			Database bd = (Database) c.getDeclaredConstructor().newInstance();
			DatabaseManager.registerDatabase(bd);
			
			String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/ColeccionesXML/BDProductosXML", usu = "admin", pwd = "alumno";
			col = DatabaseManager.getCollection(URI, usu, pwd);
			
			XMLResource res = (XMLResource) col.getResource(doc);  // Obtiene el documento
			if (res == null) {									   // Comprueba si existe el documento
				System.out.println("NO EXISTE EL DOCUMENTO");
			} else {
				System.out.println("ID del documento: " + res.getDocumentId());
				// Volcado del documento a un �rbol DOM
				// El m�todo getContentAsDOM devuelve el contenido del recurso XML como nodo DOM
				Node documento = (Node) res.getContentAsDOM();
				Source source = new DOMSource(documento);

				// Volcado del documento de memoria a consola
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				Result console = new StreamResult(System.out);
				transformer.transform(source, console);
				
				// Volcado del documento a un fichero
				Result fichero = new StreamResult(new java.io.File("./zonas.xml"));
				transformer = TransformerFactory.newInstance().newTransformer();
				transformer.transform(source, fichero);
				
				System.out.println(" ... Se ha generado el fichero XML --------------------- \n");
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
	
	}  // fin documentoBDaFichero
	
	public static void consultaBDaFichero() throws TransformerConfigurationException, TransformerException, Exception {
		Collection col = null;
		try {
			Class<?> c = Class.forName("org.exist.xmldb.DatabaseImpl");			
			Database bd = (Database) c.getDeclaredConstructor().newInstance();
			DatabaseManager.registerDatabase(bd);
			
			String URI = "xmldb:exist://localhost:8090/exist/xmlrpc/db/", usu = "admin", pwd = "Ad1rectory";
			col = DatabaseManager.getCollection(URI, usu, pwd);
			
			
			// Consulta datos
			XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
			String consultaXPath = "/EMPLEADOS/fila_empleado[EMP_NO=7782]";
			ResourceSet resultSet = servicio.query(consultaXPath);
			System.out.println("EJECUTA LA CONSULTA: " + consultaXPath);
			
			// Recorre los datos obtenidos en el ResourceSet (conjunto de recursos)
			ResourceIterator results = resultSet.getIterator();
			if (!results.hasMoreResources()) {
				System.out.println(" LA CONSULTA NO DEVUELVE NADA.");
			}
			while (results.hasMoreResources()) {
				// Es necesario hacer un cast a XMLResource para despu�s usar el m�todo getContentAsDOM
				XMLResource res = (XMLResource) results.nextResource();  
				
				// Volcado del documento a un �rbol DOM
				// El m�todo getContentAsDOM devuelve el contenido del recurso XML como nodo DOM
				Node documento = (Node) res.getContentAsDOM();
				Source source = new DOMSource(documento);

				// Volcado del documento de memoria a consola
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				Result console = new StreamResult(System.out);
				transformer.transform(source, console);
				
				// Volcado del documento a un fichero
				Result fichero = new StreamResult(new java.io.File("./zonas1.xml"));
				transformer = TransformerFactory.newInstance().newTransformer();
				transformer.transform(source, fichero);
				
				System.out.println(" ... Se ha generado el fichero XML de la consulta --------------------- \n");
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
	
	}  // fin consultaBDaFichero
	
	
	
} // fin clase
