/* Lee valores en una Tabla de BD MySQL en Xampp */

import java.sql.*;

public class UD2_DML_RecorrerRegistros {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ud2_xampp","alberto", "alberto");
			// Preparamos la consulta
			Statement sentencia = conexion.createStatement();
		     String sql = "SELECT * FROM departamentos";
			ResultSet resul = sentencia.executeQuery(sql);

			resul.last(); //Nos situamos en el último registro
			System.out.println ("NÚMERO DE FILAS: " + resul.getRow());

			resul.beforeFirst(); //Nos situamos antes del primer registro	  
					  
			//Recorremos el resultado para visualizar cada fila
			while (resul.next()) 
			    System.out.printf("Fila %d: %d, %s, %s %n",  
			    		 resul.getRow(),
			             resul.getInt(1), resul.getString(2), resul.getString(3) );


			resul.close();     // Cerrar ResultSet
			sentencia.close(); // Cerrar Statement
			conexion.close();  // Cerrar conexión

		      } catch (ClassNotFoundException cn) {
			   cn.printStackTrace();
		      } catch (SQLException e) {
			   e.printStackTrace();
		      }
				  
	}// fin de main
}// fin de la clase
