
/* Lee datos tipos primitivos (int, string) desde un fichero de datos.
 * La lectura de datos debe hacerse sabiendo el tipo y orden de los datos
 * que est�n escritos en el fichero.
 * Para controlar el fin de la lectura de datos utiliza
 * un bucle while dentro de un try-catch(EOFException )
 */

import java.io.*;

public class UD1_LeerFichData {
  public static void main(String[] args) throws IOException {    
	File fichero = new File("FichData2.dat");
	DataInputStream dataIS = new DataInputStream(new FileInputStream(fichero));

   String n;
   int e;

   try {
    while (true) {
        n = dataIS.readUTF(); //recupera el nombre
        e = dataIS.readInt(); //recupera la edad
        System.out.println("Nombre: " + n + 
        		", edad: " + e);        
    }
    }catch (EOFException eo) {}
	
   dataIS.close();  //cerrar stream   
  }
}
