/* Inserta valores en una Tabla de BD MySQL en Xampp con una sentencia preparada
 * Los valores a insertar se pasan al programa como parámatros.*/

import java.sql.*;

public class UD2_DML_InsertarDptoPreparedStatement {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
				// Establecemos la conexion con la BD
				Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ud2_xampp","alberto", "alberto");
											
				// recuperar argumentos de main
				String dep = args[0]; // num. departamento
				String dnombre = args[1]; // nombre
				String loc = args[2]; // localidad
				
				// construir orden INSERT con sentencia preparada
				String sql = "INSERT INTO departamentos VALUES "
						+ "( ?, ?, ? )";
  			    
				System.out.println(sql);  			    
				PreparedStatement sentencia = conexion.prepareStatement(sql);				
				sentencia.setInt(1, Integer.parseInt(dep));
				sentencia.setString(2, dnombre);
				sentencia.setString(3, loc);
				
				int filas;//
				try {
				  filas = sentencia.executeUpdate();				// Ejecuta el INSERT INTO
				  System.out.println("Filas afectadas: " + filas);
				
				} catch (SQLException e) {
					System.out.println("HA OCURRIDO UNA EXCEPCIÓN:"); 
				    System.out.println("Mensaje:    "+ e.getMessage()); 
				    System.out.println("SQL estado: "+ e.getSQLState()); 
			    	System.out.println("Cód error:  "+ e.getErrorCode());  
				}
				
				

				sentencia.close(); // Cerrar Statement
				conexion.close(); // Cerrar conexión

			} catch (ClassNotFoundException cn) {
				cn.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}// fin de main
}// fin de la clase
