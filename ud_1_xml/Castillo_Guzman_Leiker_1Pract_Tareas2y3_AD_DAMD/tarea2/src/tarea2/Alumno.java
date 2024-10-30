package tarea2;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "alumno") // Le indicamos el elemento ra�z
@XmlType(propOrder = { "tipo", "nombre", "telefono" }) // Indicamos el orden de las propiedades = etiquetas

public class Alumno {

	private String tipo;
	private String nombre;
	private String telefono;

	public Alumno() {
		super();
	}
	
	

	public Alumno(String tipo, String nombre, String telefono) {
		super();
		this.tipo = tipo;
		this.nombre = nombre;
		this.telefono = telefono;
	}



	@XmlAttribute(name = "tipo") // declara que tipo es un atributo, si no lo encuetra saldrá null
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@XmlElement(name = "nombre") // declara que nombre es un elemento
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	@XmlElement(name = "telefono") // declara que telefono es un elemento
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	

}
