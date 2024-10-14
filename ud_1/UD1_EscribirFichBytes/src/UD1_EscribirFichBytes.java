/* Escribe bytes en un fichero (si no existe lo crea el constructor) y despu�s los visualiza. 
Al elegir el constructor del flujo de salida se puede optar por adjuntar datos al fichero ya existente (append = true)
El m�todo read() devuelve -1 cuando no hay dato a leer.
*/

import java.io.*;

public class UD1_EscribirFichBytes {

	public static void main(String[] args) throws IOException {
		File fichero = new File("FichBytes.dat");// declara fichero

		// crea flujo de salida hacia el fichero
		FileOutputStream fileout = new FileOutputStream(fichero);
		// FileOutputStream fileout = new FileOutputStream(fichero, true); // Adjuntar
		// al fichero de salida

		// crea flujo de entrada
		FileInputStream filein = new FileInputStream(fichero);

		int i;
		for (i = 1; i < 100; i++)
			fileout.write(i); // escribe datos en el flujo de salida
		fileout.close(); // cerrar stream de salida

		// visualizar los datos del fichero
		while ((i = filein.read()) != -1) // lee datos del flujo de entrada
			System.out.println(i);
		filein.close(); // cerrar stream de entrada
	}
}
