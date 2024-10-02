/* Genera un fichero HTML a partir de un fichero XML y otro XSL (https://www.w3.org/Style/XSL/ )
*  Ficheros XSL (expresa hojas de estilo en lenguaje XML)
*/

import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class UD1_Convertidor {
 
 public static void main(String argv[]) throws IOException{ 
  String hojaEstilo = "alumnosPlantilla.xsl";
  String datosAlumnos = "alumnos.xml";
  File pagHTML = new File("mipagina.html");
  FileOutputStream os = new FileOutputStream(pagHTML); //crear fichero HTML
  
  Source estilos =new StreamSource(hojaEstilo); //fuente XSL
  Source datos =new StreamSource(datosAlumnos); //fuente XML
  Result result = new StreamResult(os);         //resultado de la transformación
  
  try{     
   Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);   
   transformer.transform(datos, result);	//obtiene el HTML
  }
  catch(Exception e){System.err.println("Error: "+e);}
  
  os.close();  //cerrar fichero 	
 
 }//de main
}//de la clase
