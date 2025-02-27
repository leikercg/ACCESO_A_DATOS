/* Clase que representa el elemento Libreria
* API JAXB => https://javaee.github.io/jaxb-v2/
*/

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
 
@XmlRootElement(name = "libreria") // Le indicamos que la clase Libreria es el elemento ra�z <libreria>
@XmlType(propOrder= {"nombre", "libros"}) // Si le cambiamos el orden a esto se escribe en orden difetente.

public class Libreria {
    private String nombre;
    private ArrayList<Libro> libros = new ArrayList<Libro>(); // Colecci�n para guardar todos los objetos Libro
   
    public Libreria(){}  			// Constructor sin argumentos
    
  
    @XmlElementWrapper(name = "libros") // declara que el ArrayList libros en el fichero XML es el wrapper <libros>
    @XmlElement(name = "libro")			// declara que los elementos del ArrayList libros en el fichero XML son elementos <libro>
    public ArrayList<Libro> getLibros() { return libros; }
    public void setLibros(ArrayList<Libro> libros) { this.libros = libros; }
    
    @XmlElement(name="nombre")		// declara que nombre es un elemento
    public String getNombre() { return nombre; }  
    public void setNombre(String nombre) { this.nombre = nombre; }
 }
