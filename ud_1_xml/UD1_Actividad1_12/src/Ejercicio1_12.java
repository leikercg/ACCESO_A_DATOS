
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class Ejercicio1_12 {
    public static void main(String[] args) throws JAXBException, IOException{
       
        JAXBContext context = JAXBContext.newInstance(Cine.class); // Necesito instancia de JAXBContext y 
                                                                        // le indico la clase que define el documento XML
        Marshaller marshaller = context.createMarshaller();// Objeto que me permite escribir un XML
               
// Ahora vamos a definir la informacion que queremos meter en el fichero
        Cine cine = new Cine();//Creamos objeto Libreria
        cine.setNombre("CineReordenado");
        
// A�ado a la Libreria objetos libro
        ArrayList<Pelicula> peliculas = new ArrayList();
        Pelicula pelicula = new Pelicula();
        pelicula.setGenero("Ciencia Ficcion");
        pelicula.setTitulo("La llegada");
        pelicula.setDirector("Denis Villeneuve");
        peliculas.add(pelicula);
        Pelicula pelicula2 = new Pelicula();
        pelicula2.setGenero("Animacion");
        pelicula2.setTitulo("Mi vecino Totoro");
        pelicula2.setDirector("Hayao Miyazaki");
        peliculas.add(pelicula2);
        Pelicula pelicula3 = new Pelicula();
        pelicula3.setGenero("Ciencia Ficcion");
        pelicula3.setTitulo("Interstellar");
        pelicula3.setDirector("Christopher Nolan");
        peliculas.add(pelicula3);
        
        cine.setPeliculas(peliculas); // Relleno la libreria con el array de libros
        
        //Volcamos esta informaci�n en memoria sobre un fichero XML
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        //marshaller.marshal(libreria, System.out);
        marshaller.marshal(cine, new FileWriter("CineReordenado.xml"));
    }
}
