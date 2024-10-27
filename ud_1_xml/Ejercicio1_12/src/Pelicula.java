import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "pelicula") // Le indicamos el elemento ra�z
@XmlType(propOrder = { "genero", "director", "titulo" }) //son los atributos, no las etiquetas // Indicamos el orden de las propiedades = etiquetas , escritura importa el orden

public class Pelicula {

	private String genero;
	private String titulo;
	private String director;

	@XmlAttribute(name = "genero") // declara que genero es un atributo
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	@XmlElement(name = "titulo") // declara que titulo es un elemento
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@XmlElement(name = "director") // declara que titulo es un elemento
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
}
