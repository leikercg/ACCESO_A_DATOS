/* Inserta valores en una Tabla de BD MySQL en Xampp */

import java.sql.*;

public class UD2_DML_InsertarDpto {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ud2_xampp","leiker", "leiker");
						
		  /*Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","ejemplo", "ejemplo");
          */ 
			/* recuperar argumentos de main
			String dep = args[0]; // num. departamento
			String dnombre = args[1]; // nombre
			String loc = args[2]; // localidad
			*/
			
			/* asignar valores al registro a insertar */
			String dep = "50"; // num. departamento
			String dnombre = "MARKETING"; // nombre
			String loc = "ALBACETE"; // localidad
				
			//construir orden INSERT	        
	        String sql = String.format("INSERT INTO departamentos VALUES (%s, '%s', '%s')",	dep,dnombre,loc);

			System.out.println(sql);
			Statement sentencia = conexion.createStatement();
			int filas=0;
			try {
			  filas = sentencia.executeUpdate(sql.toString());
			  System.out.println("Filas afectadas: " + filas);

			} catch (SQLException e) {
				//e.printStackTrace();
				   System.out.printf("HA OCURRIDO UNA EXCEPCI�N:%n"); 
				   System.out.printf("Mensaje   : %s %n", e.getMessage()); 
				   System.out.printf("SQL estado: %s %n", e.getSQLState()); 
				   System.out.printf("C�d error : %s %n", e.getErrorCode());	    	
			}
				
			sentencia.close(); // Cerrar Statement
			conexion.close(); // Cerrar conexi�n

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// fin de main
}// fin de la clase
