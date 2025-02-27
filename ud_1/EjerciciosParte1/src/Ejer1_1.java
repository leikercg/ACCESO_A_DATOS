import java.io.File;

public class Ejer1_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Realiza un programa Java que utilice el método listFiles() para mostrar la
		 * lista de ficheros en un directorio cualquiera, o en el directorio actual.
		 * Realiza un programa Java que muestre los ficheros de un directorio. El nombre
		 * del directorio se pasará al programa desde los argumentos de main(). Si el
		 * directorio no existe se debe mostrar un mensaje indicándolo.
		 */

		String dir = args[0];
		File f = new File(dir);
		if (f.exists() == false) {
			System.out.println("No existe el fichero");
		} else {
			File[] listaArchivos = f.listFiles();
			System.out.printf("Ficheros en el directorio actual: %d %n", listaArchivos.length);
			for (int i = 0; i < listaArchivos.length; i++) {
				File f2 = listaArchivos[i];
				System.out.printf("Nombre: %s, es fichero?: %b, es directorio?: %b %n", f2.getName(), f2.isFile(),
						f2.isDirectory());
			}
		}

	}

}
