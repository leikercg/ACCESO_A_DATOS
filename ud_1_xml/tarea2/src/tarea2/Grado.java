package tarea2;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "listadoAlumnos") // Le indicamos que la clase grado es el elemento raíz pero con el nombre
										// listado_Alumnos<listado_Alumnos>
@XmlType(propOrder = { "nombre", "alumnos" })

public class Grado {

	private String nombre;
	private ArrayList<Alumno> alumnos = new ArrayList<>();

	public Grado() {
	} // Constructor sin argumentos
	
	

	public Grado(String nombre, ArrayList<Alumno> alumnos) {
		super();
		this.nombre = nombre;
		this.alumnos = alumnos;
	}



	@XmlElement(name = "nombre") // declara que nombre es un elemento
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlElementWrapper(name = "alumnos") // declara que el ArrayList alumnos en el fichero XML es el wrapper <alumnos>
	@XmlElement(name = "alumno") // declara que los elementos del ArrayList alumnos en el fichero XML son elementos <alumno>
	public ArrayList<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(ArrayList<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

}
