package clases;

public class Pais {
	// Atributos
	private int id;
	private String nombrepais;
	
	// Constructores
	public Pais() {}
	
	public Pais(int id, String nombrepais) {
			this.id = id;
			this.nombrepais = nombrepais;
	}
	// Métodos de acceso
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombrepais() {
		return nombrepais;
	}
	public void setNombre(String nombrepais) {
		this.nombrepais = nombrepais;
	}	
	// Método toString()
	public String toString() {
		return nombrepais;
	}	
} // fin clase Pais