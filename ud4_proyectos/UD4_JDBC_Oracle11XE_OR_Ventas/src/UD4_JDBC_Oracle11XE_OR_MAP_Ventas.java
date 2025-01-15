// Conexión a servidor Oracle 11g XE y tablas de objetos

// Paso 1. Importar las clases necesarias
import java.sql.*;
import java.util.Map;

public class UD4_JDBC_Oracle11XE_OR_MAP_Ventas {
	
	public static void main(String[] args) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String usuario = "ud4dam";
			String passwd = "Ad1rectory";
			Connection conexion = DriverManager.getConnection(url,usuario,passwd);
			Statement sentencia = conexion.createStatement();
			
			// Creo una correspondencia entre el tipo de la BD y la clase del proyecto
			Map<String, Class<?>> miMap = conexion.getTypeMap();
			miMap.put("TIP_DIRECCION", Class.forName("Direccion"));
											
			// Consulta el nombre del cliente y su dirección de una determinada venta
			String sql = "	SELECT  DEREF(P.IDCLIENTE).NOMBRE, DEREF(P.IDCLIENTE).DIREC"
					+ " FROM   TABLA_VENTAS P"
					+ " WHERE  P.IDVENTA = 1";
			ResultSet res = sentencia.executeQuery(sql);
			System.out.println("-- CLIENTE Y DIRECCION --");
			System.out.println("-----------------------------");
			while (res.next() ) {
				//Recupero los atributos de la dirección que es objeto
				Direccion dir = (Direccion)res.getObject(2);
				String calle = dir.getCalle();
				String poblacion = dir.getPoblacion();
				int codPostal = dir.getCodPostal();
				String provincia = dir.getProvincia();
				
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
