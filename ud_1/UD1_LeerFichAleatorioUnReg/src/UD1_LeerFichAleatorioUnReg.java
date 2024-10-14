/* Lee un registro de un fichero aleatorio.
 * El n�mero del registro se pasa como par�metro al programa.
 */

import java.io.*;

public class UD1_LeerFichAleatorioUnReg {

	public static void main(String[] args) throws IOException {
		File fichero = new File("AleatorioEmple.dat");

		// declara el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		//
		int id, dep, posicion;
		Double salario;
		char apellido[] = new char[10], aux;
		int registro = Integer.parseInt(args[0]); // recoger id de empleado a consultar

		posicion = (registro - 1) * 36; // clacula la posici�n

		if (posicion >= file.length()) // comprueba si la posici�n est� dentro de la longitud del fichero
			System.out.printf("ID: %d, NO EXISTE EMPLEADO...", registro);
		else {
			file.seek(posicion); // nos posicionamos
			id = file.readInt(); // obtengo id de empleado
			for (int i = 0; i < apellido.length; i++) {
				aux = file.readChar();// recorro uno a uno los caracteres del apellido
				apellido[i] = aux; // los voy guardando en el array
			}
			String apellidoS = new String(apellido);// convierto a String el array
			dep = file.readInt();// obtengo dep
			salario = file.readDouble(); // obtengo salario

			System.out.println("ID: " + registro + ", Apellido: " + apellidoS.trim() + ", Departamento: " + dep
					+ ", Salario: " + salario);
		}
		file.close(); // cerrar fichero
	}
}