import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Ejercicio1_11 {

	public static void main(String[] args) throws JAXBException, IOException {
		JAXBContext contexto = JAXBContext.newInstance(Cine.class);
		Unmarshaller organizador = contexto.createUnmarshaller();
		Cine cine = (Cine) organizador.unmarshal(new File("cine.xml"));

		System.out.println("Nombre: " + cine.getNombre());
		ArrayList<Pelicula> peliculas = cine.getPeliculas();
		for (Pelicula l : peliculas) {
			System.out.println(l.getGenero() + ", " + l.getTitulo() + ", " + l.getDirector());
		}
		
	
	}

}
