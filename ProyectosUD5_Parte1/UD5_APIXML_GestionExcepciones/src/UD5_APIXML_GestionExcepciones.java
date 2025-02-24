// Ejemplo de Gesti�n de Excepciones en BD eXist desde Java con API XML:DB

import org.xmldb.api.*;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;

public class UD5_APIXML_GestionExcepciones {
		
	public static void main(String[] args) throws Exception {
		// Declaraci�n de variables
		String driver = "org.exist.xmldb.DatabaseImpl";
		String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
		Database db;
		String usu ="admin";
		String pwd ="alumno";
		Class<?> cl =null;
		XPathQueryService service = null;
		ResourceSet result=null;
		Collection col = null; 	
	    
		try {  // Carga el driver
	    	cl = Class.forName(driver);		
		} catch (ClassNotFoundException e) {
			System.out.println("No se encuentra la clase del driver: " + e.getMessage()); }
		
		try {  // Crea una instancia de la BD y se conecta a ella
			db = (Database) cl.getDeclaredConstructor().newInstance();
			DatabaseManager.registerDatabase(db);
		} catch (InstantiationException e) {
			System.out.println("Error instanciando el driver. ");
		}catch(NullPointerException e) {
			System.out.println("Error al instanciar la clase del driver: " + e.getMessage());
		} catch (IllegalAccessException e) {
		  System.out.println("Se ha producido una IllegalAccessException");
		} catch (XMLDBException e) {
		  System.out.println("Error XMLDB :" + e.getMessage());	} 
		
		try	{  // Accede a la colecci�n
			col = DatabaseManager.getCollection(URI + "/db/ColeccionPruebas",usu,pwd);
		} catch (XMLDBException e) {
			System.out.println("ERROR XMLDBException en getCollection." + e.getMessage());	} 
		
		try {  // Especifica el servicio
			service =(XPathQueryService) col.getService("XPathQueryService", "1.0");
		}catch (NullPointerException n){
			System.out.println("Error en getService, no se puede crear el servicio.");
		}catch (XMLDBException e) {
			System.out.println("ERROR XMLDBException, en get service."
	          + e.getMessage());	} 
		
		try {  // Indica el servicio y realiza la consulta   
			result = service.query("for $b in /EMPLEADOS/EMP_ROW[APELLIDO='TOVAR'] return $b");
		}catch (NullPointerException n){
		  System.out.println("Error en query, no se ha inicializado la BD o el servicio.");
		}catch (XMLDBException e) {
		  System.out.println("Error XMLDBException en la query: " + e.getMessage());
		} 
		
		try {  // Recorre los resultados de la consulta 
			ResourceIterator i;
			i = result.getIterator();
	        if (!i.hasMoreResources()){
		   	   System.out.println("LA CONSULTA NO DEVUELVE NADA");	   }
	       while (i.hasMoreResources()) { //Procesamos el resultado
	           Resource r = i.nextResource();
	           System.out.println((String) r.getContent());    }
		}catch ( NullPointerException n){
			System.out.println("Error getIterator. Problemas con el servicio.");
		}catch (XMLDBException  e) {
		  System.out.println("Error XMLDBException, getIterator. : " + e.getMessage());	} 
		
		try {  // Cierra la conexi�n con la colecci�n
			col.close();
		}catch ( NullPointerException n){
			System.out.println("Error en el cierre de la colecci�n.");
		} catch (XMLDBException  e) {
			System.out.println("Error XMLDBException, col.close. : " + e.getMessage());	}
	 
	}//fin main   
} 
// fin clase
