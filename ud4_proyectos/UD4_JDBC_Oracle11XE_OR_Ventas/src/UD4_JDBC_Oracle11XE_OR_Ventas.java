// Conexión a servidor Oracle 11g XE y tablas de objetos

// Paso 1. Importar las clases necesarias
import java.sql.*;

public class UD4_JDBC_Oracle11XE_OR_Ventas {
	
	public static void main(String[] args) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String usuario = "ud4dam";
			String passwd = "Ad1rectory";
			Connection conexion = DriverManager.getConnection(url,usuario,passwd);
			
			Statement sentencia = conexion.createStatement();
			
			// Consulta Pedidos de venta con su importe total, que se obtiene llamando al método TOTAL_VENTA()
			String sql = "SELECT  IDVENTA, DEREF(IDCLIENTE).NOMBRE NOMBRE, "
					+ "DEREF(IDCLIENTE).IDCLIENTE IDCLIENTE, T.TOTAL_VENTA() TOTAL "
					+ "FROM TABLA_VENTAS T";
			ResultSet res = sentencia.executeQuery(sql);
			while (res.next() ) {
				System.out.printf("%d %s %d %d %n", res.getInt(1), res.getString(2), res.getInt(3), res.getInt(4));
			}
			System.out.println("");
			
			
			
			// Consulta el nombre del cliente y su dirección de una determinada venta
			sql = "	SELECT  DEREF(P.IDCLIENTE).NOMBRE, DEREF(P.IDCLIENTE).DIREC"
					+ " FROM   TABLA_VENTAS P"
					+ " WHERE  P.IDVENTA = 1";
			res = sentencia.executeQuery(sql);
			System.out.println("-- CLIENTE Y DIRECCION --");
			System.out.println("-----------------------------");
			while (res.next() ) {
				//Recupero los atributos de la dirección que es objeto
				java.sql.Struct jdbcStruct = (java.sql.Struct) res.getObject(2);
				Object[] atributos = jdbcStruct.getAttributes();
				String calle = atributos[0].toString();
				String poblacion = atributos[1].toString();
				String codPostal = atributos[2].toString();
				String provincia = atributos[3].toString();
				
				System.out.printf("Cliente: %s , Dirección: %s, %s, %s, %s %n", 
						res.getString(1), calle, poblacion, codPostal, provincia);
			}
			
			System.out.println("");
									
			res.close();		// Paso 8. Libera objeto ResultSet
			sentencia.close();	// Paso 9. Libera objeto Statement
			conexion.close();	// Paso 10. Libera objeto Connection
			
		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // fin del main
} // fin de la clase
