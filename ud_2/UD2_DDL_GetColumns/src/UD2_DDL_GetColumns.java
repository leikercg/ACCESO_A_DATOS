/* Obtiene información de las columnas de una Tabla */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UD2_DDL_GetColumns {

	public static void main(String[] args) {
		  try
		  {
			Class.forName("com.mysql.jdbc.Driver"); //Cargar el driver
			//Establecemos la conexion con la BD
	        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ud2_xampp","alberto", "alberto");
	  
			DatabaseMetaData dbmd = conexion.getMetaData();//Creamos objeto DatabaseMetaData
	  		System.out.println("COLUMNAS TABLA DEPARTAMENTOS:");
	  		System.out.println("===================================");
	  		ResultSet columnas = dbmd.getColumns(null, null, "departamentos", null);
	  		while (columnas.next()) {			   
	  		  String nombCol = columnas.getString("COLUMN_NAME"); //getString(4)
	  		  String tipoCol = columnas.getString("TYPE_NAME");   //getString(6)
	  		  String tamCol = columnas.getString("COLUMN_SIZE");  //getString(7)
	  		  String nula  = columnas.getString("IS_NULLABLE");   //getString(18)
	  					   			   
	  		  System.out.printf("Columna: %s, Tipo: %s, Tamaño: %s, ¿Puede ser Nula:? %s %n", nombCol, tipoCol, tamCol, nula);
	  		}
	
	  		conexion.close(); //Cerrar conexion   		  	   
		  } /* Obtiene información de la base de datos y de las tablas y vistas */
		  catch (ClassNotFoundException cn) {cn.printStackTrace();} 
		  catch (SQLException e) {e.printStackTrace();}
		  
		}//fin de main
}//fin de la clase

