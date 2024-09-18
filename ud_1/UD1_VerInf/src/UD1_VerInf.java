/* Muestra varios atributos de un fichero (nombre, ruta, si se puede leer, etc.)
 * Al especificar la ruta en Windows, al poner el car�cter \ hay que a�adir 
 * el car�cter de escape \ por lo que tendr� \\
 */

import java.io.*;

public class UD1_VerInf {
public static void main(String[] args) {
  System.out.println("INFORMACI�N SOBRE EL FICHERO:");
  File f = new File(".");  
  if(f.exists()){
        System.out.println("Nombre del fichero  : "+f.getName());
        System.out.println("Ruta                : "+f.getPath());
        System.out.println("Ruta absoluta       : "+f.getAbsolutePath());
        System.out.println("Se puede leer       : "+f.canRead());
        System.out.println("Se puede escribir   : "+f.canWrite());
        System.out.println("Tama�o              : "+f.length());
		System.out.println("Es un directorio    : "+f.isDirectory()); 
		System.out.println("Es un fichero       : "+f.isFile());
		System.out.println("Nombre del directorio padre: "+f.getParent());
  }
 }
}
