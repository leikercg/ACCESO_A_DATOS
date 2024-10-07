import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class UD1_Actividad1_6 {

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
		File fileXML = new File("empleados.xml");	    
		procesadorXML.parse(fileXML, gestor);        	      	
	}

}

class GestionContenido extends DefaultHandler {	 
	
	String etiqueta="";
	String valor="";
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
        //System.out.println("\tPrincipio Elemento: " + nombre);	 	   
        
        if (!nombreC.isEmpty()) {
			etiqueta=nombreC;
        }
    } 

    public void endElement(String uri, String nombre, String nombreC) {
      //  System.out.println("\t Fin Elemento: " + nombre);
        System.out.printf("\t Etiqueta: %s, valor: %s %n", etiqueta, valor);

    }
	
	public void characters(char[] ch, int start, int length) throws SAXException {
	   String car=new String(ch, start, length);
	   car = car.replaceAll("[\t\n]","");//quitar saltos de l�nea		   
	   //System.out.println ("\tCaracteres: " + car);		
	   
	   if (!car.isEmpty()) {
		valor = car;
	}
    }
	

}//fin GestionContenido