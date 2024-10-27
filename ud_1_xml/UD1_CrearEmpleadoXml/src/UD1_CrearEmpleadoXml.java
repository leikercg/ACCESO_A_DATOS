
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class UD1_CrearEmpleadoXml {
	
 public static void main(String args[]) throws IOException{
   
   /* Abre el fichero aleatorio que contiene los REGISTROS DE DATOS 
    * y lo abre en modo lectura */
   File fichero = new File("AleatorioEmple.dat");   
   RandomAccessFile file = new RandomAccessFile(fichero, "r");
   
   /* Definici�n de variables */
   int  id, dep, posicion = 0; //posici�n contiene el valor del cursor en el fichero       
   Double salario;
   char apellido[] = new char[10], aux;
     
   /* Crea una instancia del procesador o parser DOM. Necesita usar newInstance porque est� protegido
    * Controla excepci�n ParserConfigurationException */ 

   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
  
   try{
	 /* Crea una instancia de clase DocumentBuilder a partir del DocumentBuilderFactory */
     DocumentBuilder builder = factory.newDocumentBuilder();
     /* Crea la interfaz para realizar operaciones (m�todos) sobre el documento */
     DOMImplementation implementation = builder.getDOMImplementation();
     /* Crea el documento XML e indica su versi�n */
     Document document = implementation.createDocument(null, "Los_empleados", null); // La raiz del documento
     document.setXmlVersion("1.0"); 
   
     /* Recorre el fichero aleatorio y para cada registro crea un elemento en el documento XML */
     for(;;) {
    	 /* Realiza la lectura de un registro del fichero aleatorio */
    	 file.seek(posicion); //nos posicionamos 
    	 id=file.readInt();   // obtengo id de empleado	  	  
    	 for (int i = 0; i < apellido.length; i++) { // lee los caracteres del apellido, la longitud del []
    		 aux = file.readChar();
    		 apellido[i] = aux;    
    	 }   
    	 String apellidos = new String(apellido); // convierte el array de char en string
    	 dep = file.readInt();
    	 salario = file.readDouble();  
	   
	 if(id>0) { //id validos a partir de 1
		 /* Crea el nodo <empleado> y lo a�ade a la raiz del documento XML*/
		 Element raiz = document.createElement("empleado"); //nodo empleado
         document.getDocumentElement().appendChild(raiz); 
        
         /* A�ade los hijos del nodo (raiz) */
         //a�adir ID                       
         CrearElemento("id",Integer.toString(id), raiz, document); 
         //Apellido
         CrearElemento("apellido",apellidos.trim(), raiz, document); 
         //a�adir DEP
         CrearElemento("dep",Integer.toString(dep), raiz, document); 
         //a�adir salario
         CrearElemento("salario",Double.toString(salario), raiz, document); 
	 }	
	 /* Desplaza el cursor para posicionarse al comienzo del siguiente registro en el fichero aleatorio */
	 posicion= posicion + 36; // me posiciono para el siguiente empleado	  	  
	 if (file.getFilePointer() == file.length()) break; 

     }//fin del for que recorre el fichero aleatorio
		
     
     Source source = new DOMSource(document);
     Result result = new StreamResult(new java.io.File("Empleados.xml"));        
     Transformer transformer = TransformerFactory.newInstance().newTransformer();
     transformer.transform(source, result);
    
    }catch(Exception e){ System.err.println("Error: "+e); }
    
    file.close();  //cerrar fichero 	
 }//fin de main
 
 //Inserci�n de los datos del empleado
 /* El m�todo CrearElemento crea un nodo hijo y se le deben pasar los siguientes par�metros
  * 	datoEmple => valor del nodo hijo (nombre del dato o atributo del registro
  * 	valor => valor del dato o atributo (se pasa como String)
  * 	raiz => nodo del que cuelga el nodo hijo
  * 	document => documento XML en el que se crea el nuevo nodo
  */
 static void  CrearElemento(String datoEmple, String valor, Element raiz, Document document){ 
    Element elem = document.createElement(datoEmple); // crea el nodo hijo
    Text text = document.createTextNode(valor); // damos valor
    raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
    elem.appendChild(text); //pegamos el valor		 	
 } // fin m�todo CrearElemnto //
 
}//fin de la clase

