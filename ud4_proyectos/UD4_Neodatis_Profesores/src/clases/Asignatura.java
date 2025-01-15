package clases;

import java.util.Set;

public class Asignatura {

	private String codAsig;
	private String nombreAsig;
	private Set<Profesor> setprofesores;
	
	public Asignatura(String codAsig, String nombreAsig, Set<Profesor> setprofesores) {
		super();
		this.codAsig = codAsig;
		this.nombreAsig = nombreAsig;
		this.setprofesores = setprofesores;
	}
	
	public Asignatura(){}
	
	public String getCodAsig() {
		return codAsig;
	}
	
	public void setCodAsig(String codAsig) {
		this.codAsig = codAsig;
	}
	
	public String getNombreAsig() {
		return nombreAsig;
	}
	
	public void setNombreAsig(String nombreAsig) {
		this.nombreAsig = nombreAsig;
	}
	
	public Set<Profesor> getSetprofesores() {
		return setprofesores;
	}
	
	public void setSetprofesores(Set<Profesor> setprofesores) {
		this.setprofesores = setprofesores;
	}

}  // fin clase Asignatura
