import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion_ADerby {

public static void main(String[] args) {
		
		try {
			// Paso 2. Carga el driver JDBC
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			
			// Paso 3. Identifico el origen de datos
			String url = "jdbc:derby:examen_derby.db";
			// String url = "jdbc:derby:ud2_derby.db";
			
			// Paso 4. Crea objeto Connection
			Connection conexion = DriverManager.getConnection(url);
			
			// Paso 5. Crea objeto Statement
			Statement sentencia = conexion.createStatement();
			
			// Pasos 6 y 7. Ejecuta la consulta y recupera los datos en el ResulSet
				String sql = "select num_ped, ncliente, pnombre, importe from pedidos p join productos pr using(cod_prod)";
			ResultSet res = sentencia.executeQuery(sql);
						
			// Hace el tratamiento de los datos. En este caso los imprime recorriendo el ResultSet
			while (res.next() ) {
				System.out.printf("Código: %d, Cliente: %s, Producto: %s, Precio: %.2f %n", res.getInt(1), res.getString(2), res.getString(3), res.getFloat(4));
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
	
}
