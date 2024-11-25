package tarea2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class LecturaAlumno {

	public static void main(String[] args) throws JAXBException, IOException {
		JAXBContext contexto = JAXBContext.newInstance(Grado.class);
		Unmarshaller organizador = contexto.createUnmarshaller();
		Grado grado = (Grado) organizador.unmarshal(new File("listadoAlumnos.xml"));

		System.out.println("Listado Alumnos " + grado.getNombre());
		ArrayList<Alumno> alumnos = grado.getAlumnos();
		for (Alumno l : alumnos) {
			System.out.println("Nombre:"+l.getNombre() + " Num.Teléfono:" + l.getTelefono() + " ¿Repite curso? " + l.getTipo());
		}
	}

}
