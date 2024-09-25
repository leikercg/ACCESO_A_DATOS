import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.RandomAccess;

public class Ejer1_4 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		if (args.length != 2) {
			System.out.println("Por favor inserta los argumentos");
		} else {
			int id =0;
			double salario = 0.0;
			try {
				id = Integer.parseInt(args[0]);
				salario = Double.parseDouble(args[1]);

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Los parámetros tienen que ser numércios");
			}

			if (id <= 0 || salario <= 0) {
				System.out.println("Número de id no válido o salario no válido");
			} else {
				File fichero = new File("AleatorioEmple.dat");
				RandomAccessFile file = new RandomAccessFile(fichero, "rw");

				int posicion = (id - 1)* 36;

				char ape[] = new char[10];
				char aux;
				int x; // leer el id

				file.seek(posicion);

				try {
					x = file.readInt(); // Leer el id del empleado
					if (x == id) {
						for (int i = 0; i < ape.length; i++) {
							aux = file.readChar();
							ape[i] = aux; // los voy guardando en el array
						}
						String apellido = new String(ape);// convierto a String el array
						int dep = file.readInt(); // obtengo dep
						double salarioActual = file.readDouble(); // obtengo salario actual;

						double salarioNuevo = salarioActual + salario;
						posicion = (int) file.getFilePointer();
						posicion = posicion + 4 + 20 + 4;
						file.seek(posicion);

						file.writeDouble(salarioNuevo);

						// Muestro los datos por pantalla
						System.out.println("Apellido: " + apellido.trim());
						System.out.println("Salario Antigo: " + salario);
						System.out.println("Salario Nuevo: " + salarioNuevo);
					} else {
						System.out.println("El registro a modificar no existe");
						
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("El registro a modificar no existe");
				}
			}
		}
	}
}
