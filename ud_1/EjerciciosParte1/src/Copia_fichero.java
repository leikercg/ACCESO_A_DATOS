import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Copia_fichero {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Copiaun fichero secuencial de texto y le añade el número de línea

		try {
			// File fic = new File("FichTexto.txt");//declara fichero
			// BufferedReader fichero = new BufferedReader(new FileReader(fic));
			BufferedReader fichero = new BufferedReader(new FileReader("FichTexto1.txt"));
			BufferedWriter ficheroEscribe = new BufferedWriter(new FileWriter("FichTextoCopia.txt"));
			String linea;

			int i = 0;
			while ((linea = fichero.readLine()) != null) { // Lee una l�nea de texto
				i++;
				ficheroEscribe.write(linea + i); // escribe una l�nea
				ficheroEscribe.newLine(); // escribe un salto de l�nea
				System.out.println(linea);

			}

			System.out.println(linea);
			fichero.close();
			ficheroEscribe.close();

		} catch (FileNotFoundException fn) {
			System.out.println("No se encuentra el fichero");
		} catch (IOException io) {
			System.out.println("Error de E/S ");
		}

	}

}
