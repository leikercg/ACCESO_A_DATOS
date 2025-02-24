// Ejemplos de manipulaci�n de datos sobre BD eXist desde Java con API XML:DB

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;

public class UD5_APIXML_ManipulaDatosEneXist {
	
  public static void main(String[] args) throws Exception {
	  	
	  	Collection col = null;
	  
	  	// Define URI a la Colecci�n de documentos XML
		String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/ColeccionesXML/ColeccionPruebas";
		String usu = "admin"; 
		String pwd = "alumno"; 
		
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
			
				// INSERTO DATOS, nuevo departamento
				String consultaXPath = "update insert "
									+ "<DEP_ROW><DEPT_NO>50</DEPT_NO>"
									+ "<DNOMBRE>CONTABILIDAD</DNOMBRE> "
									+ "<LOC>ALBACETE</LOC></DEP_ROW>"
									+ "into doc('/ColeccionPruebas/departamentos.xml')/departamentos";
				ResourceSet resultSet = servicio.query(consultaXPath);
				System.out.println("INSERTADO DEPARTAMENTO.");
				
				// ACTUALIZO DATOS, cambio localidad en departamento
				consultaXPath = "update value "
									+ "doc('/ColeccionPruebas/departamentos.xml')/departamentos/filadpto[DEPT_NO = 50]/LOC "
									+ "with 'GUADALAJARA'";
				resultSet = servicio.query(consultaXPath);
				System.out.println("MODIFICADA LOCALIDAD EN DEPARTAMENTO.");
								
				// OBTENGO DATOS, localidad de un departamento
				consultaXPath = "/departamentos/DEP_ROW[DEPT_NO = 50]/LOC/text()";
				resultSet = servicio.query(consultaXPath);
				System.out.println("LOCALIDAD DEL DEPARTAMENTO.");
				ResourceIterator results = resultSet.getIterator();
				if (!results.hasMoreResources()) {
					System.out.println("*** LA CONSULTA NO DEVUELVE NADA. ***");
				}
				while (results.hasMoreResources()) {
					Resource res = results.nextResource();
					System.out.println((String) res.getContent());
				}
				
				// ELIMINO DATOS, elimino departamento
				consultaXPath = "update delete "
									+ "doc('/ColeccionPruebas/departamentos.xml')/departamentos/DEP_ROW[DEPT_NO = 50] ";
				resultSet = servicio.query(consultaXPath);
				System.out.println("MODIFICADA LOCALIDAD EN DEPARTAMENTO.");
				
				// OBTENGO DATOS, departamentos
				consultaXPath = "/departamentos/DEP_ROW[DEPT_NO = 50]";
				resultSet = servicio.query(consultaXPath);
				System.out.println("DEPARTAMENTOS.");
				results = resultSet.getIterator();
				if (!results.hasMoreResources()) {
					System.out.println("*** LA CONSULTA NO DEVUELVE NADA. ***");
				}
				while (results.hasMoreResources()) {
					Resource res = results.nextResource();
					System.out.println((String) res.getContent());
				}
										
			}  // fin else
			
		} catch (XMLDBException e) {
		         System.err.println("XML:DB Exception occured " + e.errorCode);
		        		 
		} finally {
		    	if (col != null) {
		    		col.close();
		        }
		}
		
  }  // fin main 
  
} // fin clase
