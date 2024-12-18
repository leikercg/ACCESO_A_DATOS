import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class main {

	public static void main(String[] args) {

		try {
			// Paso 2. Carga el driver JDBC
			Class.forName("org.sqlite.JDBC");

			// Paso 3. Identifico el origen de datos
			String url = "jdbc:sqlite:turismo.db";

			// Paso 4. Crea objeto Connection
			Connection conexion = DriverManager.getConnection(url);

			// Paso 5. Crea objeto Statement
			Statement sentencia = conexion.createStatement();

			// Pasos 6 y 7. Ejecuta la consulta y recupera los datos en el ResulSet
			String sql = "select * from ciudades";
			ResultSet res = sentencia.executeQuery(sql);

			// Hace el tratamiento de los datos. En este caso los imprime recorriendo el
			// ResultSet de las ciudades

			System.out.println("========== CIUDADES ===========");
			while (res.next()) {
				System.out.printf("ID: %d, Ciudad: %s, Provincia: %s %n", res.getInt(1), res.getString(2),
						res.getString(3));
			}

			// Pasos 6 y 7. Ejecuta la consulta y recupera los datos en el ResulSet
			sql = "select * from monumentos";
			ResultSet res1 = sentencia.executeQuery(sql);
			
			System.out.println();

			// Repetimos los mismos pasos para los monumentos
			// Hace el tratamiento de los datos. En este caso los imprime recorriendo el
			// ResultSet de los monumentos

			System.out.println("========== MONUMENTOS ===========");
			while (res1.next()) {
				System.out.printf("ID: %d, Nombre: %s, Ciudad: %s %n", res.getInt(1), res.getString(2),
						res.getString(3));
			}

			res.close(); // Paso 8. Libera objeto ResultSet
			sentencia.close(); // Paso 9. Libera objeto Statement
			conexion.close(); // Paso 10. Libera objeto Connection

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // fin del main
}
