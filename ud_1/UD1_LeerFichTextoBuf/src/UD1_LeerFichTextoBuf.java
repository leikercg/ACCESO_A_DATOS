/* Lee líneas completas de texto de un fichero determinado
 * ubicado en el mismo directorio que el proyecto y las muestra en pantalla.
 * El método readLine() devuelve null cuando no hay línea
 * La lectura se incluye en bloque try-catch.
 */

import java.io.*;
public class UD1_LeerFichTextoBuf {
  public static void main(String[] args) {
  try{
	  //File fic = new File("FichTexto.txt");//declara fichero
      //BufferedReader fichero = new BufferedReader(new FileReader(fic)); 
	  BufferedReader fichero = new BufferedReader(new FileReader("FichTexto.txt"));
	  
      String linea; 
      while((linea = fichero.readLine())!=null) //Lee una línea de texto	 
        System.out.println(linea);     
      fichero.close(); 
	}
	catch (FileNotFoundException fn ){                      
               System.out.println("No se encuentra el fichero");}
	catch (IOException io) {
               System.out.println("Error de E/S ");}
               
  }
}
