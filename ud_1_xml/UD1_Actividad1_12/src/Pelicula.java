
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="pelicula")
@XmlType(propOrder={"director","genero","titulo"})//En que orden se debe escribir los elementos, en lectura no afecta
//Se define el orden si son del mismo tipo, si no lo son se deben poner del mismo tipo

public class Pelicula { // Esta clase define cada uno de las peliculas del documento XML
    private String genero;
    private String titulo;
    private String director;

    public Pelicula() { // Constructor vacío
    }

    @XmlAttribute(name="genero") // Le indicamos que es atributo
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    @XmlElement(name="titulo") // Le indicamos que es elemento 
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    @XmlAttribute(name="director") // Le indicamos que es elemento 
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
}
