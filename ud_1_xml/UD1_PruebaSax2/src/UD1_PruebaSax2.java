/* Realiza la lectura de un fichero XML, utilizando el parser SAX.
 * Los m�todos para tratar el archivo se definen sobrescribiendo la clase DefaultHandler
 */

import java.io.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;

public class UD1_PruebaSax2 {
	
	/* Hay que capturar varias expciones, incluida SAXException */
	public static void main(String[] args) throws FileNotFoundException, IOException, SAXException, ParserConfigurationException{
		
		/* Crea el objeto SAXParser como procesador (parser) del fichero */
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser procesadorXML = factory.newSAXParser();
			
		/* Crea el gestor de contenido */
		GestionContenido gestor= new GestionContenido();
		
		/* https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/File.html
		/* Indica la fuente (fichero XML) a procesar y le dice al procesador que lo procese
		 * con el gestor de contenido personalizado */
		File fileXML = new File("alumnos.xml");	    
		procesadorXML.parse(fileXML, gestor);        	      	
	}

}//fin PruebaSax1

/* Se define los m�todos a usar en el programa (creamos el Parser)
 * sobreescribiendo los m�todos de la clase DefaultHandler
 */
class GestionContenido extends DefaultHandler {	 
	    public GestionContenido() {
	        super();
	    }	    
	    public void startDocument() {
	        System.out.println("Comienzo del Documento XML");
	    }	    
	    public void endDocument() {
	        System.out.println("Final del Documento XML");
	    }	 	    
	    public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
	        System.out.printf("\t Principio Elemento: %s %n",nombre);	 	        
	    } 	
	    public void endElement(String uri, String nombre, String nombreC) {
	        System.out.printf("\t Fin Elemento: %s %n", nombre);
	    }	
	    public void characters(char[] ch, int inicio, int longitud) throws SAXException {
		   String car=new String(ch, inicio, longitud);          //quitar saltos de l�nea	
		   car = car.replaceAll("[\t\n]","");	   
		   System.out.printf ("\t  Caracteres: %s %n", car);		
	    }	

}//fin GestionContenido
