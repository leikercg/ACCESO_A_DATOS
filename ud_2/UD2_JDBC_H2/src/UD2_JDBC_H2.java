// Conexi�n a BD H2

// Paso 1. Importar las clases necesarias

import java.sql.*;

public class UD2_JDBC_H2 {
	
	public static void main(String[] args) {
		
		try {
			// Paso 2. Carga el driver JDBC
			Class.forName("org.h2.Driver");
			
			// Paso 3. Identifico el origen de datos
			String url = "jdbc:h2:~/test";  // Equivalente a url = "jdbc:h2:file:C:/Users/Jos�Alberto/test";
			String usuario = "sa";
			String passwd = "";
			
			// Paso 4. Crea objeto Connection
			Connection conexion = DriverManager.getConnection(url, usuario, passwd);
			
			// Paso 5. Crea objeto Statement
			Statement sentencia = conexion.createStatement();
			
			// Pasos 6 y 7. Ejecuta la consulta y recupera los datos en el ResulSet
			String sql = "SELECT * FROM departamentos";
			ResultSet res = sentencia.executeQuery(sql);
			
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
