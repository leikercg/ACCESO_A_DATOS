/* Ejecuta un procedimiento almacenado de BD MySQL en Xampp */

import java.sql.*;

public class UD2_ProcSubida {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ud2_xampp","leiker", "leiker");

			 //conexion a ORACLE		  
		     /*Class.forName ("oracle.jdbc.driver.OracleDriver");	
			  Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ejemplo", "ejemplo");  
           */
			  			
			// recuperar parametros de main
			String dep = args[0];   //"10"; // departamento
			String subida = args[1];//"1000"; // subida
			
			// construir orden DE LLAMADA
			String sql = "{ call subida_sueldo (?, ?) } ";

			// Preparamos la llamada
			CallableStatement llamada = conexion.prepareCall(sql);
			// Damos valor a los argumentos
			llamada.setInt(1, Integer.parseInt(dep)); 		// primer argumento - departamento
			llamada.setFloat(2, Float.parseFloat(subida)); 	// segundo argumento - subida
															
			llamada.executeUpdate(); 					// ejecutar el procedimiento
			System.out.println("Subida realizada....");
			llamada.close();
			conexion.close();
			
		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		  

	}// fin de main
}// fin de la clase
