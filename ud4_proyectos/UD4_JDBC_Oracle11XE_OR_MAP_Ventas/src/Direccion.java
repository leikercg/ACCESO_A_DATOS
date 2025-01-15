import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class Direccion implements SQLData{
	
	String calle;
	String poblacion;
	int codPostal;
	String provincia;
	
	private String tipoSQL = "TIP_DIRECCION";
	
	public Direccion() {
		
	}
	
	public Direccion(String calle, String poblacion, int codPostal, String provincia) {
		super();
		this.calle = calle;
		this.poblacion = poblacion;
		this.codPostal = codPostal;
		this.provincia = provincia;
	}
	
	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public int getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(int codPostal) {
		this.codPostal = codPostal;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		// TODO Auto-generated method stub
		return tipoSQL;
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		tipoSQL = typeName;
		setCalle(stream.readString());
		setPoblacion(stream.readString());
		setCodPostal(stream.readInt());
		setProvincia(stream.readString());	
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(calle);
		stream.writeString(poblacion);
		stream.writeInt(codPostal);
		stream.writeString(provincia);
	}
	
}  // Fin clase Direccion
