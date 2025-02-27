/* Realiza la lectura de un fichero XML, utilizando el parser SAX.
 * Los m�todos para tratar el archivo se definen sobrescribiendo la clase DefaultHandler
 */

import java.io.*;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class UD1_PruebaSax1 {
	
	/* Hay que capturar varias expciones, incluida SAXException */
	public static void main(String[] args) throws FileNotFoundException, IOException, SAXException{

		/* Crea el objeto XMLReader como procesador (parser) del fichero */
		XMLReader  procesadorXML = XMLReaderFactory.createXMLReader();	
	
		/* Crea el gestor de contenido */
		GestionContenido gestor= new GestionContenido();
	
		/* Asigna el gestor de contenido al procesador (parser) */
		procesadorXML.setContentHandler(gestor);
 	
		/* Indica la fuente (fichero XML) a procesar y le dice al procesador que lo procese */
		InputSource fileXML = new InputSource("alumnos.xml");	    
		procesadorXML.parse(fileXML);        	      	
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
