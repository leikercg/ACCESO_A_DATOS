package ud3_hibernate1;

public class Nuevos implements java.io.Serializable {

	private byte deptNo;
	private String dnombre;
	private String loc;

	public Nuevos() {
	}

	public Nuevos(byte deptNo) {
		this.deptNo = deptNo;
	}

	public Nuevos(byte deptNo, String dnombre, String loc) {
		this.deptNo = deptNo;
		this.dnombre = dnombre;
		this.loc = loc;
	}

	public byte getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(byte deptNo) {
		this.deptNo = deptNo;
	}

	public String getDnombre() {
		return this.dnombre;
	}

	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}

	public String getLoc() {
		return this.loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

}
