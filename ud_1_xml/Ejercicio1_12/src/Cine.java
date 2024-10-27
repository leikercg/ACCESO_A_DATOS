import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name = "cine") // Le indicamos que la clase cine es el elemento ra�z <cine>
@XmlType(propOrder= {"nombre", "peliculas"})

public class Cine {

	private String nombre;
	private ArrayList<Pelicula> peliculas = new ArrayList();
	

    @XmlElement(name="nombre")		// declara que nombre es un elemento
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	 @XmlElementWrapper(name = "peliculas") // declara que el ArrayList peliculas en el fichero XML es el wrapper <libros>
	 @XmlElement(name = "pelicula")			// declara que los elementos del ArrayList cine en el fichero XML son elementos <libro>
	public ArrayList<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(ArrayList<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	public Cine() {

	}

}
