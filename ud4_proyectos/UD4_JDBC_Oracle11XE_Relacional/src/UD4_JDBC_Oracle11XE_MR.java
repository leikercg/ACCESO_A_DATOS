//Conexi�n a servidor Oracle 11g XE.
//Me conecto a la tabla articulos_mr
//que est� en el namespace del usuario PLSQL

//Paso 1. Importar las clases necesarias

import java.sql.*;

public class UD4_JDBC_Oracle11XE_MR {
	
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
			String sql = "SELECT * FROM articulos_mr";
			ResultSet res = sentencia.executeQuery(sql);
			// Hace el tratamiento de los datos. En este caso los imprime recorriendo el ResultSet
			while (res.next() ) {
				System.out.printf("%d, %.2f, %d %n", res.getInt("NumArt"), res.getFloat("Precio"), res.getInt("Iva"));
			}
			
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

