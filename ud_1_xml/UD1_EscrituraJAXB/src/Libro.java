/* Clase que define la estructura de cada uno de los libros del documento XML
* API JAXB => https://javaee.github.io/jaxb-v2/
* Descargar el API, descomprimir e incluir en el proyecto el fichero jaxb-ri\mod\jaxb-api.jar 
*/

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType; 

@XmlRootElement(name = "libro") // Le indicamos el elemento raíz
@XmlType(propOrder = {"isbn", "titulo", "autor"}) // Indicamos el orden de las propiedades = etiquetas

public class Libro { 
	private String isbn;
	private String titulo;
    private String autor;
    
	public Libro() {}					// Constructor sin parámetros
    
	@XmlAttribute(name = "isbn")		// declara que isbn es un atributo 
	public String getIsbn() { return isbn;}
	public void setIsbn(String isbn) {  this.isbn = isbn;  }
	
	@XmlElement(name = "titulo")		// declara que titulo es un elemento
	public String getTitulo() { return titulo; }
	public void setTitulo(String titulo) { this.titulo = titulo; }
	
	@XmlElement(name = "autor")			// declara que titulo es un elemento
    public String getAutor() { return autor;  }
    public void setAutor(String autor) { this.autor = autor; }
    
}
