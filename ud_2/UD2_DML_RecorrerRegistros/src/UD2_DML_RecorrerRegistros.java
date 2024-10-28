/* Lee valores en una Tabla de BD MySQL en Xampp */

import java.sql.*;

public class UD2_DML_RecorrerRegistros {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ud2_xampp", "leiker", "leiker");
			// Preparamos la consulta
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT emp_no, fecha_alt from empleados where dept_no = 20";
			ResultSet resul = sentencia.executeQuery(sql);

			resul.last(); // Nos situamos en el �ltimo registro
			System.out.println("N�MERO DE FILAS: " + resul.getRow());

			resul.beforeFirst(); // Nos situamos antes del primer registro

			// Recorremos el resultado para visualizar cada fila
			while (resul.next())
				System.out.printf("Fila %d: %s, %s  %n", resul.getRow(), resul.getString(1), resul.getString(2)); //Amite tambien el nombre de la columna

			resul.close(); // Cerrar ResultSet
			sentencia.close(); // Cerrar Statement
			conexion.close(); // Cerrar conexi�n

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// fin de main
}// fin de la clase
