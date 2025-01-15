// Conexi�n a servidor Oracle 11g XE

// Paso 1. Importar las clases necesarias
import java.sql.*;

public class UD4_JDBC_Oracle11XE_MOR {
	
	public static void main(String[] args) {
		
		try {
			// Paso 2. Carga el driver JDBC
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Paso 3. Identifico el origen de datos
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String usuario = "leiker";
			String passwd = "leiker";
			
			// Paso 4. Crea objeto Connection
			Connection conexion = DriverManager.getConnection(url,usuario,passwd);
			
			// Paso 5. Crea objeto Statement
			Statement sentencia = conexion.createStatement();
			
			// Pasos 6 y 7. Ejecuta la consulta y recupera los datos en el ResulSet
			String sql = "SELECT  p.NumPed FROM Pedido_objtab p ORDER BY VALUE(p)";
			ResultSet res = sentencia.executeQuery(sql);
			
			// Consulta 1
			System.out.println("-- CONSULTA ORDENA PEDIDOS --");
			System.out.println("-----------------------------");
			while (res.next() ) {
				System.out.printf("%d %n", res.getInt(1));
			}
			System.out.println("");
			
			// Consulta 2
			sql = "	SELECT  DEREF(p.numcli), p.direccionenvio_obj, p.numped,p.fechaped, p.listalineas_tan"
					+ " FROM   Pedido_objtab p"
					+ " WHERE  p.NumPed = 2001";
			System.out.println(sql);
			res = sentencia.executeQuery(sql);
			System.out.println("-- CLIENTE Y LINEAS PEDIDO --");
			System.out.println("-----------------------------");
			while (res.next() ) {
				// Para representar la fecha completa con printf uso %tY-%<tm-%<td
				System.out.printf("%d %tY-%<tm-%<td %n", res.getInt("NumPed"), res.getDate("FechaPed"));
				// Trato de recuperar la direccionenvio que es un objeto
				java.sql.Struct jdbcStruct = (java.sql.Struct) res.getObject(2);
				Object[] atributos = jdbcStruct.getAttributes();
				System.out.printf("Atributo 0: %s %n", atributos[0].toString());
				System.out.printf("Atributo 1: %s %n", atributos[1].toString());
				System.out.printf("Atributo 2: %s %n", atributos[2].toString());
				System.out.printf("Atributo 3: %s %n", atributos[3].toString());
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
