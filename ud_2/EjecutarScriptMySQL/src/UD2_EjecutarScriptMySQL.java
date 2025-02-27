import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UD2_EjecutarScriptMySQL {

	public static void main(String[] args) {
		ejecutarScriptMySQL();
	}

	public static void ejecutarScriptMySQL() {
		File scriptFile = new File("./script/scriptmysql.sql");
		System.out.println("--------------------------------------------");
		System.out.println("\n\nFichero de consulta : " + scriptFile.getName());
		System.out.println("Convirtiendo el fichero a cadena...");

		BufferedReader entrada = null;
		try {
			entrada = new BufferedReader(new FileReader(scriptFile));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR NO ENCUENTRA el fichero: " + e.getMessage());
			e.printStackTrace();
		}
		String linea = null;
		StringBuilder stringBuilder = new StringBuilder();
		String salto = System.getProperty("line.separator");
		try {
			while ((linea = entrada.readLine()) != null) {
				stringBuilder.append(linea);
				stringBuilder.append(salto);
			}
		} catch (IOException e) {
			System.out.println("ERROR de E/S, al operar con el fichero: " + e.getMessage());
			e.printStackTrace();
		}
		String consulta = stringBuilder.toString();

		System.out.println(consulta);

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR En el Driver: " + e.getMessage());
			e.printStackTrace();
		}
		try {
			//Conexi�n con opci�n allowMultiQueries=true"
			Connection connmysql = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/ud2_xampp?allowMultiQueries=true", "alberto", "alberto");
			Statement sents = connmysql.createStatement();
			int res = sents.executeUpdate(consulta);
			System.out.println("Script creado con �xito, res = " + res);
			connmysql.close();
			sents.close();
		} catch (SQLException e) {
			System.out.println("ERROR AL EJECUTAR EL SCRIPT: " + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
