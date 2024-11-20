import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connexion_Xampp {

	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/examen";
			String usuario = "usuario";
			String passwd = "usuario";
			Connection conexion = DriverManager.getConnection(url, usuario, passwd);

			System.out.println("--- CONSULTA DE PRODUCTOS --");
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * FROM productos";
			ResultSet res = sentencia.executeQuery(sql);
			while (res.next()) {
				System.out.printf("%d, %s, %.2f %n", res.getInt(1), res.getString(2), res.getFloat(3));
			}

			System.out.println("--- COMPROBACIÓN DE PRODUCTO --");
			if (comprobarPKProducto(conexion, 11)) {
				System.out.println("El producto existe");
			} else {
				System.out.println("El producto no existe");
			}

			/*
			 * System.out.println("--- INSERCIÓN DE PRODUCTO --"); if
			 * (insertarProducto(conexion, 60, " Otro producto")) {
			 * System.out.println("El producto se ha insertado correctamente"); } else {
			 * System.out.println("El producto no se ha podido inserta"); }
			 */

			System.out.println("--- INSERCIÓN DE PRODUCTO DESDE FICHERO --");

			// Leo los datos desde el fichero externo.

			String linea;
			String[] datos;
			try {
				BufferedReader fichero = new BufferedReader(new FileReader("nuevosProductos.txt"));
				while ((linea = fichero.readLine()) != null) { // Lee una l�nea de texto
					datos = linea.split("#");

					if (insertarProductoCompleto(conexion, Integer.parseInt(datos[0]), datos[1],
							Float.parseFloat(datos[2]))) {
						System.out.println("El producto se ha insertado correctamente");
					} else {
						System.out.println("El producto no se ha podido inserta");
					}
				}
				fichero.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO: handle exception
			}

			res.close(); // Paso 8. Libera objeto ResultSet
			sentencia.close(); // Paso 9. Libera objeto Statement
			conexion.close(); // Paso 10. Libera objeto Connection

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // fin del main

	// Método qe devuelve true si el cod_pk ya existe
	private static boolean comprobarPKProducto(Connection conexion, int cod_pk) {
		boolean resultado = false;
		String sql = "SELECT cod_prod FROM productos WHERE cod_prod = " + cod_pk;
		System.out.println(sql);
		try {
			Statement sentencia = conexion.createStatement();
			ResultSet res = sentencia.executeQuery(sql);
			while (res.next()) {
				resultado = true;
			}
			sentencia.close(); // Cerrar Statement
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	} // fin método comprobarPKProducto

	private static boolean insertarProducto(Connection conexion, int cod_pk, String n_producto) {
		boolean resultado = false;
		String precio = "12.45";

		if (!comprobarPKProducto(conexion, cod_pk)) {
			System.out.println("El producto con cod_prod = " + cod_pk + " no existe y voy a insertarlo");
			int filas = 0;
			try {
				String sql = "INSERT INTO productos VALUES ( ?, ?, ? )";
				PreparedStatement sentencia = conexion.prepareStatement(sql);
				sentencia.setInt(1, cod_pk);
				sentencia.setString(2, n_producto);
				sentencia.setFloat(3, Float.parseFloat(precio));
				filas = sentencia.executeUpdate();
				if (filas == 1) {
					resultado = true;
				}
				System.out.println("Filas afectadas: " + filas);
				sentencia.close(); // Cerrar Statement
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	} // fin método comprobarPKProducto

	// Inserta un producto de manera completa
	private static boolean insertarProductoCompleto(Connection conexion, int cod_pk, String n_producto, float precio) {
		boolean resultado = false;

		if (!comprobarPKProducto(conexion, cod_pk)) {
			System.out.println("El producto con cod_prod = " + cod_pk + " no existe y voy a insertarlo");
			int filas = 0;
			try {
				String sql = "INSERT INTO productos VALUES ( ?, ?, ? )";
				PreparedStatement sentencia = conexion.prepareStatement(sql);
				sentencia.setInt(1, cod_pk);
				sentencia.setString(2, n_producto);
				sentencia.setFloat(3, precio);
				filas = sentencia.executeUpdate();
				if (filas == 1) {
					resultado = true;
				}
				System.out.println("Filas afectadas: " + filas);
				sentencia.close(); // Cerrar Statement
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	} // fin método comprobarPKProducto

} // fin de la clase
