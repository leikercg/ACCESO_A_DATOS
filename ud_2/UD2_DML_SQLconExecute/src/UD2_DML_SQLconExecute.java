/* Inserta valores en una Tabla de BD MySQL en Xampp */

import java.sql.*;

public class UD2_DML_SQLconExecute {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ud2_xampp","alberto", "alberto");
			
			String sql="SELECT * FROM departamentos";
			   Statement sentencia = conexion.createStatement();		   
			   boolean valor = sentencia.execute(sql);  	// ejecuta la sentencia SQL y devuelve valor booleano
			   		   
			   if(valor){			// actua en función del valor devuelto por el método execute
			    	ResultSet rs = sentencia.getResultSet();
			   	 while (rs.next())
			   	    System.out.printf("%d, %s, %s %n",
			   	    		rs.getInt(1), rs.getString(2), rs.getString(3));
			    	rs.close();
			   } else {
			    	int f = sentencia.getUpdateCount();
			    	System.out.printf("Filas afectadas:%d %n", f);
			   }
			   
				sentencia.close();
				conexion.close();

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}						
		
	}// fin de main
}// fin de la clase
