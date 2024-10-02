/* Escritura de fichero XML con JAXB
 * API JAXB => https://javaee.github.io/jaxb-v2/
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class UD1_EscrituraJAXB {
	
	public static void main(String[] args) throws JAXBException, IOException {
		
		JAXBContext contexto = JAXBContext.newInstance(Libreria.class); // Crea una instancia de JABXContext y le dice la
																		// clase que define la estructura del documento XML
		Marshaller organizador = contexto.createMarshaller();	// Objeto que permite crear el fichero XML
		
		// Definimos la información que va a incluir el fichero
		Libreria libreria = new Libreria();
		libreria.setNombre("Libreria_1");
		
		// Añade varios objetos Libro a la Libreria
		ArrayList<Libro> libros = new ArrayList<Libro>();  // Crea instancia para incluir la lista de libros
		Libro libro1 = new Libro();		// Crea instancia libro
		libro1.setIsbn("12345678"); 	// Asigna valor al Isbn
		libro1.setTitulo("Titulo1"); 	// Asigna valor al Titulo
		libro1.setAutor("Autor1");		// Asigna valor al Autor
		libros.add(libro1);				// Añade el libro a la lista de libros
		Libro libro2 = new Libro();
		libro2.setIsbn("87654321");
		libro2.setTitulo("Titulo2");
		libro2.setAutor("Autor2");
		libros.add(libro2);
		libreria.setLibros(libros);  // Rellena la instancia libreria con el array de libros
		
		// Le dice al organizador (marshaller) como que tiene que trabajar, en este caso con nueva linea e indentación
		organizador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		// Le dice al organizador que cree el fichero a partir de la instancia libreria
		organizador.marshal(libreria, new FileWriter("LibreriaEscritura.xml"));
		System.out.println("Se ha creado el archivo XML y su contenido se muestra también a continuación:");
		System.out.println();
		// Le dice al organizador que lleve a la salida (consola) el contenido de la instancia libreria
		organizador.marshal(libreria, System.out);
	}
}
