package tarea3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			BufferedReader ficheroLectura = new BufferedReader(new FileReader("FicheroOrigen.txt")); // Indicamos el fichero de lectura
			BufferedWriter ficheroEscritura = new BufferedWriter (new FileWriter("FicheroDestino.txt"));	// Indicamos el fichero de escritura																		

			String linea; 
			while ((linea = ficheroLectura.readLine()) != null) { // Lee liena por línea mientras no devuelva null.
				System.out.println(linea); // Imprime por consola la línea leida
				ficheroEscritura.write(linea + "\n"); // Escribe en el nuevo fichero la linea leída mas un salto de línea
			}
			ficheroEscritura.close(); // Cerramos el flujo de escritura
			ficheroLectura.close();  // Cerramos el flujo de lectura
		} catch (FileNotFoundException fn) { // Capturamos las excepciones
			System.out.println("No se encuentra el fichero");
		} catch (IOException io) {
			System.out.println("Error de E/S ");
		}
		
		
		
		  
	}

}
