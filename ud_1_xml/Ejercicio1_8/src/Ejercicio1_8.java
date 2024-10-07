import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Ejercicio1_8 {
	public static void main(String[] args) throws JAXBException, IOException {
		JAXBContext contexto = JAXBContext.newInstance(ListaDepartamentos.class);
		Unmarshaller organizador = contexto.createUnmarshaller();
		ListaDepartamentos listaDptos = (ListaDepartamentos) organizador.unmarshal(new File("ListaDepartamentos.xml"));

		ArrayList<Departamento> dptos = listaDptos.getDepartamentos();
		for (Departamento l : dptos) {
			System.out.println(l.getDep() + ", " + l.getNombre() + ", " + l.getLoc());
		}
	}

}
