
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

public class UD1_LeerPersonas {
  public static void main(String[] args) throws IOException {   	
	
    XStream xstream = new XStream();
    xstream.allowTypes(new Class[] {ListaPersonas.class});
    xstream.allowTypes(new Class[] {Persona.class});
    
    xstream.alias("ListaPersonasMunicipio", ListaPersonas.class);		
    xstream.alias("DatosPersona", Persona.class);	
    xstream.addImplicitCollection(ListaPersonas.class, "lista");
	
    ListaPersonas listadoTodas = (ListaPersonas) xstream.fromXML(new FileInputStream("Personas.xml"));			
    System.out.println("Numero de Personas: " + 
         listadoTodas.getListaPersonas().size());
			       
    List<Persona> listaPersonas = new ArrayList<Persona>();
	listaPersonas = listadoTodas.getListaPersonas();
			        
    Iterator<Persona> iterador = listaPersonas.listIterator(); 
    while( iterador.hasNext() ) {
	    Persona p = (Persona) iterador.next(); 
	    System.out.printf("Nombre: %s, edad: %d %n", p.getNombre(), p.getEdad());        
    }    
    System.out.println("Fin de listado .....");
 } //fin main
}//fin LeerPersonas
