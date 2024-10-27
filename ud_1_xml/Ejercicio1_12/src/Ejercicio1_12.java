import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Ejercicio1_12 {

public static void main(String[] args) throws JAXBException, IOException {
		
		JAXBContext contexto = JAXBContext.newInstance(Cine.class); // Crea una instancia de JABXContext y le dice la
																		// clase que define la estructura del documento XML
		Marshaller organizador = contexto.createMarshaller();	// Objeto que permite crear el fichero XML
		
		// Definimos la informaci�n que va a incluir el fichero
		Cine cine = new Cine();
		cine.setNombre("Cinepolis");
		
		// A�ade varios objetos Libro a la Libreria
		ArrayList<Pelicula> Películas = new ArrayList<Pelicula>();  // Crea instancia para incluir la lista de libros
		Pelicula pelicula1 = new Pelicula();		// Crea instancia libro
		pelicula1.setGenero("Miedo"); 	// Asigna valor al Isbn
		pelicula1.setTitulo("Titulo1"); 	// Asigna valor al Titulo
		pelicula1.setDirector("Director1");		// Asigna valor al Autor
		Películas.add(pelicula1);	
		
		cine.setPeliculas(Películas);
		
		
		// Le dice al organizador (marshaller) como que tiene que trabajar, en este caso con nueva linea e indentaci�n
		organizador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		// Le dice al organizador que cree el fichero a partir de la instancia libreria
		organizador.marshal(cine, new FileWriter("cineReordenado.xml"));
		System.out.println("Se ha creado el archivo XML y su contenido se muestra tambi�n a continuaci�n:");
		System.out.println();
		// Le dice al organizador que lleve a la salida (consola) el contenido de la instancia libreria
		organizador.marshal(cine, System.out);
	}

}
