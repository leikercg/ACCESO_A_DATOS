import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Ejercicio1_7 {

	public static void main(String[] args) throws JAXBException, IOException {
		// TODO Auto-generated method stub
		JAXBContext contexto = JAXBContext.newInstance(ListaDepartamentos.class); // Crea una instancia de JABXContext y le
																			// dice
		// la
		// clase que define la estructura del documento XML
		Marshaller organizador = contexto.createMarshaller(); // Objeto que permite crear el fichero XML

// Definimos la informaci�n que va a incluir el fichero
		ListaDepartamentos lista = new ListaDepartamentos();
		Departamento departamento;

// A�ade varios objetos Libro a la lista de departamentos
		ArrayList<Departamento> listadepartamentos = new ArrayList<Departamento>(); // Crea instancia para incluir la lista de departamentos
		
		
		String nombres [] = {"INFORMATICA", "MARKETING", "CONTABILIDAD","VENTAS", "COMPRAS", "PERSONAL", "RECURSOS","TRANSPORTE","MANTENIMIENTO"};
		int numeros[] = {10, 15, 20, 25, 30, 35, 40, 45, 50};		
		String localidades[] = {"MADRID", "SEVILLA", "LEON", "TOLEDO", "GUADALAJARA", "CUENCA", "OVIEDO", "BILBAO", "VALENCIA"};
		
		
		for (int i = 0; i < localidades.length; i++) {
			departamento= new Departamento(numeros[i],nombres[i],localidades[i]);
			listadepartamentos.add(departamento);
		}
		
		
		lista.setDepartamentos(listadepartamentos); // Rellena la instancia lista con el array de departamentos

// Le dice al organizador (marshaller) como que tiene que trabajar, en este caso con nueva linea e indentaci�n
		organizador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
// Le dice al organizador que cree el fichero a partir de la instancia libreria
		organizador.marshal(lista, new FileWriter("ListaDepartamentos.xml"));
		System.out.println("Se ha creado el archivo XML y su contenido se muestra tambi�n a continuaci�n:");
		System.out.println();
// Le dice al organizador que lleve a la salida (consola) el contenido de la instancia libreria
		organizador.marshal(lista, System.out);
	}

}
