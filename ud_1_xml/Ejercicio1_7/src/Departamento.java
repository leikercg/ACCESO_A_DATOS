import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name = "departamento") // Le indicamos el elemento ra�z
@XmlType(propOrder = {"dep", "nombre", "loc"}) // Indicamos el orden de las propiedades = etiquetas



public class Departamento {

	private int dep;
	private String nombre;
	private String loc;

	public Departamento() {
		super();
	}

	@XmlAttribute(name = "dep") // declara que isbn es un atributo
	public int getDep() {
		return dep;
	}

	public void setDep(int dep) {
		this.dep = dep;
	}

	@XmlElement(name = "nombre") // declara que nombre es un elemento
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlElement(name = "loc") // declara que titulo es un elemento
	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public Departamento(int dep, String nombre, String loc) {
		super();
		this.dep = dep;
		this.nombre = nombre;
		this.loc = loc;
	}

}
