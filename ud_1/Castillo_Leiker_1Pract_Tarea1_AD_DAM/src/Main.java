import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// Estructura bytes: 4 (int) + 60 (char) + 40 (char) + 8 (char)
		String opcion;
		Scanner t = new Scanner(System.in);
		do {
			System.out.println("¿Qué deseas hacer?");
			System.out.println("Introduce 1 para leer.");
			System.out.println("Introduce 2 para modificar.");
			System.out.println("Se recomienda ejecutar 1, 2 y 1 para ver la secuencia en orden correcto.");
			System.out.println("Introduce cualquier otro valor para salir.");
			System.out.println("==================================================================================");

			opcion = t.next(); // Leer la opción ingresada por el usuario

			if (opcion.equals("1")) { // Si ha elegido leer

				File fichero = new File("MisDiscosFavoritos.dat"); // Se declara el fichero
				RandomAccessFile file;
				int id;
				char titulo[] = new char[30];
				char autor[] = new char[20];
				char año[] = new char[4], aux;

				int posicion = 0;
				try {
					file = new RandomAccessFile(fichero, "r");
					for (;;) {
						file.seek(posicion); // Nos posicionamos en la posición inicial
						id = file.readInt(); // Obtengo id del disco

						// recorro uno a uno los caracteres del titulo
						for (int i = 0; i < titulo.length; i++) {
							aux = file.readChar();
							titulo[i] = aux; // los voy guardando en el array
						}
						String tituloString = new String(titulo); // convierto a String el array

						// recorro uno a uno los caracteres del autor
						for (int i = 0; i < autor.length; i++) {
							aux = file.readChar();
							autor[i] = aux; // los voy guardando en el array
						}
						String autorString = new String(autor); // convierto a String el array

						// recorro uno a uno los caracteres del año
						for (int i = 0; i < año.length; i++) {
							aux = file.readChar();
							año[i] = aux; // los voy guardando en el array
						}
						String añoString = new String(año); // convierto a String el array

						if (id > 0) {
							System.out.printf("ID: %d, título: %s, autor: %s, fecha: %s \n", id, tituloString.trim(),
									autorString.trim(), añoString.trim());
						}
						// me posiciono para el siguiente disco, cada disco ocupa 112
						posicion = (int) file.getFilePointer();
						// Si he recorrido todos los bytes salgo del for
						if (file.getFilePointer() == file.length()) {
							break;
						}
					}

					System.out.println(
							"==================================================================================");
					file.close(); // cerrar fichero

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.err.println("Algo salió mal, quizas no exista el documento");
				}

			} else if (opcion.equals("2")) { // Si ha elegido modificar

				File fichero = new File("MisDiscosFavoritos.dat");
				RandomAccessFile file;
				try {
					file = new RandomAccessFile(fichero, "rw");
					StringBuffer buffer = null; // bufer para almacenar apellido
					String nuevoTitulo = "In Our Nature";

					int id = 3;
					long posicion = (id - 1) * 112 + 4; // +4 para colocarnos en el titulo
					file.seek(posicion); // nos posicionamos en el titulo

					buffer = new StringBuffer(nuevoTitulo);
					buffer.setLength(30); // 10 caracteres para el apellido
					file.writeChars(buffer.toString());// insertar apellido

					file.close(); // cerrar fichero

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.err.println("Algo salió mal, quizas no exista el documento");

				} // Se declara de lectura y escritura

			}

		} while (opcion.equals("1") || opcion.equals("2")); // Repetir mientras se pulse 1 o 2.

		t.close(); // Cerramos flujo de teclado
		System.out.println("Fin del programa.");
	}
}
