package clases;

import java.util.Set;

public class Centro {
	private Integer codCentro;
	private String nomCentro;
	private Profesor director;
	private String direccion;
	private String localidad;
	private String provincia;
	private Set<Profesor> setprofesores;
	
	public Centro(Integer codCentro, String nomCentro, Profesor director, String direccion, String localidad,
			String provincia, Set<Profesor> setprofesores) {
		super();
		this.codCentro = codCentro;
		this.nomCentro = nomCentro;
		this.director = director;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.setprofesores = setprofesores;
	}
	
	public Centro(){}

	public Integer getCodCentro() { return codCentro; }
	public void setCodCentro(Integer codCentro) { this.codCentro = codCentro; }

	public String getNomCentro() { return nomCentro; }
	public void setNomCentro(String nomCentro) { this.nomCentro = nomCentro; }

	public Profesor getDirector() { return director; }
	public void setDirector(Profesor director) { this.director = director; }

	public String getDireccion() { return direccion; }
	public void setDireccion(String direccion) { this.direccion = direccion; }

	public String getLocalidad() { return localidad; }
	public void setLocalidad(String localidad) { this.localidad = localidad; }

	public String getProvincia() { return provincia; }
	public void setProvincia(String provincia) { this.provincia = provincia; }

	public Set<Profesor> getSetprofesores() { return setprofesores;	}
	public void setSetprofesores(Set<Profesor> setprofesores) { this.setprofesores = setprofesores;	}
	
}  // fin clase Centro
