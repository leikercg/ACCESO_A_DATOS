import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejer1_4b {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		if (args.length != 2) {
			System.out.println("Introducir el usuario a borrar");
		} else {
			int id = 0;
			double salario = 0.0;
			try {
				id = Integer.parseInt(args[0]);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Los parámetro tienen que ser numércios");
			}

			File fichero = new File("AleatorioEmple.dat");
			RandomAccessFile file = new RandomAccessFile(fichero, "rw");

			int posicion = (id - 1) * 36;

			if (posicion >= file.length()) {
				System.out.println("Registro inexistente");
				file.close();
			} else {
				file.seek(posicion);
				file.write(-1);
				file.seek(posicion);

			}

		}
	}

}
