/* Obtiene informaci�n de las claves primarias de una Tabla */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UD2_DDL_GetPrimaryKeys {

	public static void main(String[] args) {
		 try
		  {
			Class.forName("com.mysql.jdbc.Driver"); //Cargar el driver
			//Establecemos la conexion con la BD
	        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ud2_xampp","leiker", "leiker"); 
	               
	        //Oracle 
		    /*Class.forName ("oracle.jdbc.driver.OracleDriver");	
		    Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ejemplo", "ejemplo");
		    */
		    
			DatabaseMetaData dbmd = conexion.getMetaData();   //Creamos objeto DatabaseMetaData			
			System.out.println("CLAVE PRIMARIA TABLA DEPARTAMENTOS:");
	  		System.out.println("===================================");
			ResultSet pk = dbmd.getPrimaryKeys(null, null, "DEPARTAMENTOS");
	  		String pkDep="", separador=";";
	  		while (pk.next()) {
	  			   pkDep = pkDep + separador + pk.getString("COLUMN_NAME");//getString(4)
	  			   pkDep = pkDep + separador + pk.getString(1);
	  			   pkDep = pkDep + separador + pk.getString(2);
	  			   pkDep = pkDep + separador + pk.getString(3);
	  			   //separador="+"; // Esto sobra
	  		 }
	  		System.out.println("Clave Primaria: " + pkDep);


				
	  		 conexion.close(); //Cerrar conexion   		  	   
		  } 
	   catch (ClassNotFoundException cn) {cn.printStackTrace();} 
				   catch (SQLException e) {e.printStackTrace();}
		 
		}//fin de main
}//fin de la clase

