package Clases;

public class Pais {
	
	private int id;
	private String nombrePais;
	
	public Pais(int id, String nombrePais) {
		super();
		this.id = id;
		this.nombrePais = nombrePais;
	}

	public Pais() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	
	public String toString(){
		return nombrePais;
	}
}
