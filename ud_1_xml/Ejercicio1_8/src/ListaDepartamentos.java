import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "listadepartameentosssssssssssss") // Le indicamos el elemento ra�z
@XmlType(propOrder = { "departamentos" }) // Indicamos el orden de las propiedades = etiquetas

public class ListaDepartamentos {
	private ArrayList<Departamento> departamentos = new ArrayList<Departamento>(); // Colecci�n para guardar todos los
																					// objetos
	// departamentos

	public ListaDepartamentos() {
	}

	@XmlElementWrapper(name = "departamentos")
	@XmlElement(name = "departamento")

	public ArrayList<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(ArrayList<Departamento> libros) {
		this.departamentos = libros;
	}

}
