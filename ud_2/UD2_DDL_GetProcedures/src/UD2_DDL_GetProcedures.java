/* Devuelve una lista de los procedimientos almacenados en la BD */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UD2_DDL_GetProcedures {

	public static void main(String[] args) {
		try {
			// conexion a ORACLE
			/*
			  Class.forName ("oracle.jdbc.driver.OracleDriver"); Connection
			  conexion = DriverManager.getConnection
			  ("jdbc:oracle:thin:@localhost:1521:XE", "ejemplo", "ejemplo");
			 */

			Class.forName("com.mysql.jdbc.Driver"); // Cargar el driver
			//Establecemos la conexion con la BD
	        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ud2_xampp","alberto", "alberto"); 

			DatabaseMetaData dbmd = conexion.getMetaData();// Creamos objeto DatabaseMetaData
			ResultSet proc = dbmd.getProcedures(null, null, null);
			while (proc.next()) {
				String proc_name = proc.getString("PROCEDURE_NAME");
				String proc_type = proc.getString("PROCEDURE_TYPE");
				System.out.printf("Nombre Procedimiento: %s - Tipo: %s %n", proc_name, proc_type);
			}

		} catch (ClassNotFoundException cn) {cn.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		}
	
	}// fin de main
}//fin de la clase

