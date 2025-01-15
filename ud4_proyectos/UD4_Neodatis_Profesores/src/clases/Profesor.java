package clases;

import java.sql.Date;

public class Profesor {

	private Integer codProf;
	private String nombreApe;
	private String nombreEspe;
	private Date fechaNac;
	private String sexo;
	private Centro centros;
	
	public Profesor(Integer codProf, String nombreApe, String nombreEspe, Date fechaNac, String sexo, Centro centros) {
		super();
		this.codProf = codProf;
		this.nombreApe = nombreApe;
		this.nombreEspe = nombreEspe;
		this.fechaNac = fechaNac;
		this.sexo = sexo;
		this.centros = centros;
	}	

	public Profesor(){}

	public Integer getCodProf() { return codProf; }
	public void setCodProf(Integer codProf) { this.codProf = codProf; }

	public String getNombreApe() { return nombreApe; }
	public void setNombreApe(String nombreApe) { this.nombreApe = nombreApe; }

	public String getNombreEspe() { return nombreEspe; }
	public void setNombreEspe(String nombreEspe) { this.nombreEspe = nombreEspe; }

	public Date getFechaNac() {	return fechaNac; }
	public void setFechaNac(Date fechaNac) { this.fechaNac = fechaNac; }

	public String getSexo() { return sexo; }
	public void setSexo(String sexo) { this.sexo = sexo; }

	public Centro getCentros() { return centros; }
	public void seCentros(Centro centros) { this.centros = centros; }
		
}  // fin clase Profesor
