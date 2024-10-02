/* Crea una lista de objetos (ListaPersonas) del tipo  (clase) Persona,
 * que obtiene leyendo desde un fichero .dat que incluye los datos.
 * Después convierte la lista de objetos (ListaPersonas) en un fichero XML, es decir,  
 * serializa objetos Java a XML. Lo hace empleando la librería XStream => https://x-stream.github.io/download.html
 */

import java.io.*;
import com.thoughtworks.xstream.XStream;

public class UD1_EscribirPersonas {
  
  public static void main(String[] args) throws IOException, ClassNotFoundException {   	
    
	/* Declara el fichero que contiene los datos de los objetos Persona */
	File fichero = new File("FichPersona.dat");
    FileInputStream filein = new FileInputStream(fichero);//flujo de entrada   
    //conecta el flujo de bytes al flujo de datos de tipo objeto
    ObjectInputStream dataIS = new ObjectInputStream(filein);      
    System.out.println ("Comienza el proceso de creación del fichero a XML ...");
				
    //Creamos un objeto Lista de Personas
    ListaPersonas listaper = new ListaPersonas();	 
     
    try {
      while (true) { //lectura de los datos de tipo objeto del fichero
        //leer una Persona
	    Persona persona= (Persona) dataIS.readObject();    
	    listaper.add(persona); //añadir persona a la lista  
      	}
      
    }catch (EOFException eo) {}
    dataIS.close();  //cerrar stream de entrada     
    
    /* Crea el archivo XML con la lista de personas, usando la clase XStream */
    try {
		XStream xstream = new XStream();   
		//cambiar de nombre a las etiquetas XML
		xstream.alias("ListaPersonasMunicipio", ListaPersonas.class);	
		xstream.alias("DatosPersona", Persona.class);
		
		xstream.aliasField("Nombre_alumno", Persona.class, "nombre");
		xstream.aliasField("Edad_alumno", Persona.class, "edad");
		
		//quitar etiqueta lista (atributo de la claseListaPersonas)
		xstream.addImplicitCollection(ListaPersonas.class, "lista");
		//Insertar los objetos en el XML
	    xstream.toXML(listaper, new FileOutputStream("Personas.xml"));	
		System.out.println("Creado fichero XML....");
	
     }catch (Exception e) 
	   {e.printStackTrace();}	    
 
  } // fin main
} //fin EscribirPersonas

