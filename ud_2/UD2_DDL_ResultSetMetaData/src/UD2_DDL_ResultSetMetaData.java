/* Devuelve información de las columnas de una Tabla (nombre y tipo de datos de las columnas) */

import java.sql.*;

public class UD2_DDL_ResultSetMetaData {

	public static void main(String[] args) {
		try {

			// conexion a ORACLE
			/*  Class.forName ("oracle.jdbc.driver.OracleDriver");
			 	Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ejemplo", "ejemplo");
			*/

			Class.forName("com.mysql.jdbc.Driver"); // Cargar el driver
			//Establecemos la conexion con la BD
	        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ud2_xampp","alberto", "alberto"); 
             
			Statement sentencia = conexion.createStatement();
			ResultSet rs = sentencia.executeQuery("SELECT * FROM departamentos");	
			
			// Crea el objeto ResultSetMetaData para después usar sus métodos y así obtener
			// metadatos de las columnas devueltas por la consulta
			ResultSetMetaData rsmd = rs.getMetaData();			
			
			int nColumnas = rsmd.getColumnCount();
			String nula;
			System.out.printf("Número de columnas recuperadas: %d%n", nColumnas);
			for (int i = 1; i <= nColumnas; i++) {
				System.out.printf("Columna %d: %n ", i);
				System.out.printf("  Nombre: %s %n   Tipo: %s %n ", rsmd.getColumnName(i),  rsmd.getColumnTypeName(i));
				if (rsmd.isNullable(i) == 0)
					nula = "NO";
				else
					nula = "SI";
				
				System.out.printf("  Puede ser nula?: %s %n ", nula);			
				System.out.printf("  Máximo ancho de la columna: %d %n",
						 rsmd.getColumnDisplaySize(i));
			} // for
			
			sentencia.close();
			rs.close();
			conexion.close();

		} catch (ClassNotFoundException cn) {cn.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		}
		
	}// fin de main
}//fin de la clase

