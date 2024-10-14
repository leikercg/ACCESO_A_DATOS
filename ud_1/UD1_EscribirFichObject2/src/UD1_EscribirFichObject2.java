/* Define el objeto Persona que implementa la interfaz Serializable y
 * el programa escribe en un fichero de datos los atributos correspondientes al objeto Persona.
 * Cuando  adjuntamos objetos a un fichero existente aparece un problema java.io.StreamCorruptedException
 * porque se a�ade una cabecera al fichero.
 * Para que no se escriba la cabecera es necesario sobreescribir (extends) la clase ObjectOutputStream
 * que en mi caso lo he hecho creando la clase MiObjectOutputStream 
 * En el programa, Si el fichero no existe se crea un objeto ObjectOutputStream que crea cabecera,
 * pero si el fichero ya existe, se llama a la clase miObjectOutputStream que no escribe cabecera.
 */

import java.io.*;


class MiObjectOutputStream extends ObjectOutputStream{
    
	MiObjectOutputStream() throws IOException {
        super();
    }
 
    MiObjectOutputStream(OutputStream o) throws IOException{
        super(o);
    }
 
    public void writeStreamHeader() throws IOException
    {
        return;
    }
}	

//@@@@@@@@@@@@ PARA SOBREESCRIBIR MUCHAS VECES SIN REESCRIBIR LA CABECERA AL LEERLO, EN CASO DE NO EXISTIR LO CREA

public class UD1_EscribirFichObject2 {
	
	public static void main(String[] args) throws IOException {   
   
   Persona persona;//defino variable persona
   
   File fichero = new File("FichPersona2.dat");//declara el fichero
   ObjectOutputStream dataOS; //
   // Comprueba si existe o no el fichero para llamar a uno u otro constructor ObjectOutputStream
   if (!fichero.exists()) {
	   FileOutputStream fileout;
	   fileout = new FileOutputStream(fichero); // Al crear el flujo de salida crea el archivo
	   //conecta el flujo de bytes al flujo de datos
	   dataOS = new ObjectOutputStream(fileout); // Este constructor a�ade cabecera al fichero de objetos
	   System.out.println("EL FICHERO NO EXISTE"); 
   } else {
	   //conecta el flujo de bytes al flujo de datos pero sin a�adir cabecera al fichero de objetos
	   //porque el archivo ya ten�a cabecera
	   System.out.println("EL FICHERO YA EXISTE"); 
	   dataOS = new MiObjectOutputStream (new FileOutputStream(fichero, true));//crea el flujo de salida
   }
    
   
   //String nombres[] = {"Ana","Luis Miguel","Alicia","Pedro","Manuel","Andr�s",
   //                    "Julio","Antonio","Mar�a Jes�s","Ana2","Luis Miguel2","Alicia2","Pedro2","Manuel2","Andr�s2"};

   String nombres[] = {"Nombre1", "Nombre2", "Nombre3"};
   
   int edades[] = {14,15,13};
   System.out.println("GRABO LOS DATOS DE PERSONA.");      
   for (int i=0;i<edades.length; i++){ //recorro los arrays    
      persona= new Persona(nombres[i],edades[i]); //creo la persona	  
	  dataOS.writeObject(persona); //escribo la persona en el fichero
	  System.out.println("GRABO LOS DATOS DE PERSONA.");  
   }     
   dataOS.close();  //cerrar stream de salida    
   }
}