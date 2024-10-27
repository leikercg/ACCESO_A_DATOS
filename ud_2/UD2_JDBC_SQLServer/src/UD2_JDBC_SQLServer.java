/* Conexi�n a BD SQLServer.
 * Se debe a�adir driver JDBC seg�n el JRE, por ejemplo => mssql-jdbc-9.4.0.jre8.jar 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UD2_JDBC_SQLServer {

	public static void main(String[] args) {
		
		try {
			
			// Paso 2. Carga el driver JDBC y probar si JDBC est� bien instalado
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			// Paso 3. Identifico el origen de datos
			String url = "jdbc:sqlserver:DESKTOP-MDAC0QE\\SQLEXPRESS";
			String usuario = "sa";
			String passwd = "alumno";
			
			// Paso 4. Crea objeto Connection
			Connection conexion = DriverManager.getConnection(url,usuario,passwd);
			
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
