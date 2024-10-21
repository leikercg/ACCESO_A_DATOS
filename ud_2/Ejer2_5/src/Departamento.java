
public class Departamento {

	private Integer dept_no;
	private String dept_nombre;
	private String dept_loc;

	public Departamento() {
		super();
	}

	public Departamento(Integer dept_no, String dept_nombre, String dept_loc) {
		super();
		this.dept_no = dept_no;
		this.dept_nombre = dept_nombre;
		this.dept_loc = dept_loc;
	}

	public Integer getDept_no() {
		return dept_no;
	}

	public void setDept_no(Integer dept_no) {
		this.dept_no = dept_no;
	}

	public String getDept_nombre() {
		return dept_nombre;
	}

	public void setDept_nombre(String dept_nombre) {
		this.dept_nombre = dept_nombre;
	}

	public String getDept_loc() {
		return dept_loc;
	}

	public void setDept_loc(String dept_loc) {
		this.dept_loc = dept_loc;
	}

}
