

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="cine") // Le indicamos que es el  elemento raiz
@XmlType(propOrder={"nombre","peliculas"})
public class Cine {
    private String nombre;// Atributo que representa el nombre
    private ArrayList<Pelicula> peliculas = new ArrayList(); // coleccion de datos que guarde todos los libros

    public Cine() { // Constructor
    }

    @XmlElement(name="nombre") // Le indicamos 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElementWrapper(name="peliculas")
    @XmlElement(name="pelicula")
    public ArrayList<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
    
    
}
