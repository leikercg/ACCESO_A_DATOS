import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;

public class Ejer1_3b {
	public static void main(String[] args) throws IOException {
		File fichero = new File("AleatorioEmple.dat");
		// declara el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");

		StringBuffer buffer = null; // bufer para almacenar apellido
		String apellido = args[1];
		Double salario = null;
		int dep = 0;
		int id = 0;

		try { // Comprueba el id
			id = Integer.parseInt(args[0]);
		} catch (Exception e) {
			System.out.println("Número de id incorrecto");
		}

		try { // Comprueba el departamento
			dep = Integer.parseInt(args[2]);
		} catch (Exception e) {
			System.out.println("Número de departamento incorrecto");
		}

		try { // Comprueba el departamento
			salario = Double.parseDouble(args[3]);
		} catch (Exception e) {
			System.out.println("Número de salario incorrecto");
		}

		boolean existe = false;
		long i = 0;

		for (;;) {
			file.seek(i);
			int newid = file.readInt();
			if (newid == id)
				existe = true;
			i = file.getFilePointer() + 20 + 4 + 8;
			if (file.length() == i) {
				break;
			}
		}
		if (existe) {
			System.out.println("El ID ya existe");
		} else {
			long posicion = (id - 1) * 36;
			file.seek(posicion); // nos posicionamos

			file.writeInt(id); // uso id para identificar empleado
			buffer = new StringBuffer(apellido);
			buffer.setLength(10); // 10 caracteres para el apellido
			file.writeChars(buffer.toString());// insertar apellido
			file.writeInt(dep); // insertar departamento
			file.writeDouble(salario);// insertar salario
		}
		file.close(); // cerrar fichero

	}

}
