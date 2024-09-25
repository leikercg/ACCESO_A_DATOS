import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejer1_3 {

	// TODO Auto-generated method stub
	public static void main(String[] args) throws IOException {

		File fichero = new File("AleatorioEmple.dat");

		// declara el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		//
		int id, dep, posicion;
		Double salario;
		char apellido[] = new char[10], aux;
		int registro=0;
		try{
			 registro = Integer.parseInt(args[0]);
		}catch (NumberFormatException n) {
			// TODO: handle exception
			System.out.println("Número de empleado incorrecto");
		}

		posicion = (registro - 1)*36; // para situarnos al principio

		for (;;) { // recorro el fichero. El posicionamiento empieza en 0 y hay que ir sumando 36
			// el metodo seek(long posici�n) coloca el puntero del fichero en una posici�n
			// determinada desde el principio
			file.seek(posicion); // nos posicionamos en posicion
			id = file.readInt(); // obtengo id de empleado

			// recorro uno a uno los caracteres del apellido
			for (int i = 0; i < apellido.length; i++) {
				aux = file.readChar();
				apellido[i] = aux; // los voy guardando en el array
			}

			// convierto a String el array
			String apellidos = new String(apellido);
			dep = file.readInt(); // obtengo dep
			salario = file.readDouble(); // obtengo salario

			if (id > 0)
				System.out.printf("ID: %s, Apellido: %s, Departamento: %d, Salario: %.2f %n", id, apellidos.trim(), dep,
						salario);

			// me posiciono para el sig empleado, cada empleado ocupa 36 bytes
			posicion = (int) file.getFilePointer(); // o Usar el tamaño del registro

			// Si he recorrido todos los bytes salgo del for
			// el m�todo getFilePointer() devuelve la posici�n actual del puntero del
			// fichero
			// el m�todo length() devuelve el tama�o del fichero en bytes. Marca el ficnal
			// del fichero
			// if (file.getFilePointer() == 108) break; // Leer solo los 3 primeros datos
			if (file.getFilePointer() == file.length())
				break; // getFilePointer() devuelve la posici�n actual del puntero

		} // fin bucle for

		file.close(); // cerrar fichero
	}
}
