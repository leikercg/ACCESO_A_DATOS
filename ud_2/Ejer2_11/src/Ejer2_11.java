import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

public class Ejer2_11 {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ud2_xampp", "leiker", "leiker");

			String dep = args[0]; // Obtengo el valor del departamento.
			// Preparamos la consulta

			if (ComprobarNumDpto(conexion, Integer.parseInt(dep))) {

				String sql = "SELECT apellido, salario, oficio from empleados where dept_no = ?";
				System.out.println(dep);
				System.out.println(sql);

				// Creamos la instancia de PreparedStatement
				PreparedStatement sentencia = conexion.prepareStatement(sql);

				// Asignamos el valor del parámetro
				sentencia.setInt(1, Integer.parseInt(dep));

				// Resultado de la sentencia, OJO, quitar el parámetro, de la statement
				// ResultSet resul = sentencia.executeQuery(sql);
				ResultSet resul = sentencia.executeQuery();

				// Recorremos el bucle
				while (resul.next()) {
					System.out.printf("Fila %d: %s, %f, %s  %n", resul.getRow(), resul.getString(1), resul.getFloat(2),
							resul.getString("oficio")); // Amite tambien el nombre de la columna
				}

				resul.close(); // Cerrar ResultSet
				sentencia.close(); // Cerrar Statement

				Statement sentencia1 = conexion.createStatement();
				String sql1 = "select AVG (salario) as salario_medio, count(*) as num_emple, dnombre "
						+ "from empleados e join departamentos d on e.dept_no = d.dept_no where d.dept_no = 10 GROUP by dnombre;";
				ResultSet resul1 = sentencia1.executeQuery(sql1);

				DecimalFormat formato = new DecimalFormat("##,##0.00"); // OJO las comillas

				
				while (resul1.next()) {
					String valorFormateado = formato.format(resul1.getFloat(1));
					System.out.println("Salario medio: "+ valorFormateado);
					System.out.println("Número de empleados: "+ resul1.getInt("num_emple") );
					System.out.println("Nombre del departamento: "+ resul1.getString("dnombre"));
				}

			} else {
				System.out.println("Número de departamento no válido");
			}
			conexion.close();

		} catch (ClassNotFoundException e) {
			// TODO: handle exception
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

	// Comprobar que existe el departamento

	private static boolean ComprobarNumDpto(Connection conexion, int dpto) {
		boolean existe = false;

		try {
			String sql = "Select * from departamentos where dept_no = ?";

			PreparedStatement sentencia = conexion.prepareStatement(sql);

			sentencia.setInt(1, dpto);

			ResultSet resul = sentencia.executeQuery();

			while (resul.next()) {
				existe = true;
			}

		} catch (SQLException e) {
			// TODO: handle exception
		}
		return existe;
	}

}
