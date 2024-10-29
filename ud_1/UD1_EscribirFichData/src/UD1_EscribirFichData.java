/*/* Escribe en un fichero de datos tipos primitivos (int, string)
 * que est�n en un array de elementos del tipo correspondiente.
 */

import java.io.*;

public class UD1_EscribirFichData {
  public static void main(String[] args) throws IOException {   
 
	  File fichero = new File("FichData.dat");
	  DataOutputStream dataOS = new DataOutputStream(new FileOutputStream(fichero));
	  //	  DataOutputStream dataOS = new DataOutputStream(new FileOutputStream(fichero, true)); PARA AÑADIR EN VEZ DE BORRAR


 
   String nombres[] = {"Ana","Luis Miguel","Alicia","Pedro","Manuel","Andr�s",
                       "Julio","Antonio","Mar�a Jes�s"};
					   
   int edades[] = {14,15,13,15,16,12,16,14,13};
	
   for (int i=0;i<edades.length; i++){
      dataOS.writeUTF(nombres[i]); //inserta nombre
      dataOS.writeInt(edades[i]);  //inserta edad
   }     
   dataOS.close();  //cerrar stream 
  }
}

/*FIJAR TAMAÑO DE NOMBRE
 * 
 * import java.io.*;

public class UD1_EscribirFichData {
    public static void main(String[] args) throws IOException {

        File fichero = new File("FichData.dat");
        DataOutputStream dataOS = new DataOutputStream(new FileOutputStream(fichero));

        String nombres[] = {"Ana", "Luis Miguel", "Alicia", "Pedro", "Manuel", "Andrés",
                            "Julio", "Antonio", "María Jesús"};
        int edades[] = {14, 15, 13, 15, 16, 12, 16, 14, 13};

        for (int i = 0; i < edades.length; i++) {
            // Crear un StringBuffer para ajustar la longitud del nombre a 10 caracteres
            StringBuffer nombreBuffer = new StringBuffer(nombres[i]);
            
            // Asegurar que el nombre tenga exactamente 10 caracteres
            if (nombreBuffer.length() < 10) {
                // Añadir espacios si el nombre es menor de 10 caracteres
                nombreBuffer.setLength(10);
            } else if (nombreBuffer.length() > 10) {
                // Truncar si el nombre es mayor de 10 caracteres
                nombreBuffer.setLength(10);
            }

            dataOS.writeUTF(nombreBuffer.toString()); // Escribe el nombre ajustado a 10 caracteres
            dataOS.writeInt(edades[i]);  // Escribe la edad
        }

        dataOS.close();  // Cerrar el stream
    }
}
*/

