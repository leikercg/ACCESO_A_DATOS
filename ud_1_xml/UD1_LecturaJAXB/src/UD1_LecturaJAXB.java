/* Lectura de fichero XML con JAXB
 * API JAXB => https://javaee.github.io/jaxb-v2/
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class UD1_LecturaJAXB {

	public static void main(String[] args) throws JAXBException, IOException {
		JAXBContext contexto = JAXBContext.newInstance(Libreria.class);
		Unmarshaller organizador = contexto.createUnmarshaller();
		Libreria libreria = (Libreria) organizador.unmarshal(new File("milibreria.xml"));

		System.out.println("Nombre: " + libreria.getNombre());
		ArrayList<Libro> libros = libreria.getLibros();
		for (Libro l : libros) {
			System.out.println(l.getIsbn() + ", " + l.getTitulo() + ", " + l.getAutor());
		}
	}

}
