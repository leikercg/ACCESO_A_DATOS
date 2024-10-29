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
/*
 * 
 * import java.io.*;

public class UD1_EscribirFichBytes {

    public static void main(String[] args) throws IOException {
        File fichero = new File("FichBytes.dat"); // declara fichero

        // Crear flujo de salida hacia el fichero
        FileOutputStream fileout = new FileOutputStream(fichero);

        // Crear flujo de entrada para leer los datos después
        FileInputStream filein = new FileInputStream(fichero);

        // Texto a escribir en el archivo
        String texto = "Esto es un ejemplo de texto";

        // Convertir el texto a bytes y escribirlo en el flujo de salida
        byte[] textoBytes = texto.getBytes();
        fileout.write(textoBytes); // Escribe los bytes del texto
        fileout.close(); // Cerrar stream de salida

        // Visualizar los datos del fichero (para verificar el contenido)
        int i;
        while ((i = filein.read()) != -1) { // Lee datos del flujo de entrada
            System.out.print((char) i); // Imprime cada byte como carácter
        }
        filein.close(); // Cerrar stream de entrada
    }
}
*/
