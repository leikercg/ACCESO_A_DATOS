// Conexión a BD HSQLDB

// Paso 1. Importar las clases necesarias

import java.util.logging.*;
import java.sql.*;

public class UD2_JDBC_HSqldb {
	
	public static void main(String[] args) {
		
		try {
					
			// Paso 2. Carga el driver JDBC
			Class.forName("org.hsqldb.jdbcDriver");
			
			// Paso 3. Identifico el origen de datos
			String url = "jdbc:hsqldb:file:D:/BBDD/hsqldb/ud2_hsqldb";	// conexión en modo stand-alone (ud2_hsqldb)
			// String url = "jdbc:hsqldb:hsql://localhost/ud2_xdb"; // conexión en modo server (ud2_xdb)			
			String usuario = "SA";
			String passwd = "";
					
			// Paso 4. Crea objeto Connection
			Connection conexion = DriverManager.getConnection(url, usuario, passwd);
							
			// Paso 5. Crea objeto Statement
			Statement sentencia = conexion.createStatement();
			
			// Pasos 6 y 7. Ejecuta la consulta y recupera los datos en el ResulSet
			String sql = "SELECT * FROM departamentos";
			ResultSet res = sentencia.executeQuery(sql);
			
			// Modifico el nivel de los mensajes de LOG de hsqldb
			Logger.getLogger("hsqldb.db").setLevel(Level.WARNING);
			
			// Hace el tratamiento de los datos. En este caso los imprime recorriendo el ResultSet
			while (res.next() ) {
				System.out.printf("%d, %s, %s %n", res.getInt(1), res.getString(2), res.getString(3));
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
