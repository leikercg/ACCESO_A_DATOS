// Leer el fichero de texto para hacer las inserciones que procedan en la tabla compras de la base de datos
// Las inserciones en la tabla compras se deben hacer con una SENTENCIA PREPARADA.

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class castillo1ev {

	public static void main(String[] args) {
		Connection conexion;
		BufferedReader ficheroLeer;

		try {
			// Realiza la conexión con la base de datos
			// Carga el driver JDBC
			Class.forName("com.mysql.jdbc.Driver");

			// Identifico el origen de datos
			String url = "jdbc:mysql://localhost/compras1ev";
			String usuario = "castillo";
			String passwd = "1ev";
			// Me conecto a la base de datos
			conexion = DriverManager.getConnection(url, usuario, passwd);

			// Asignamos el bufferreader
			ficheroLeer = new BufferedReader(new FileReader("nuevasCompras.txt"));
			insertaEnCompras(conexion, ficheroLeer);

			ficheroLeer.close();
			conexion.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error:\n" + e.getMessage() + "***");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // main

	// Métodos para realizar las diferentes operaciones

	///// Lee el archivo de texto y va haciendo las inserciones en COMPRAS
	///// /////////////
	private static void insertaEnCompras(Connection conexion, BufferedReader ficheroLeer)
			throws IOException, SQLException {

		// Asigna el buffer para leer el fichero
		String linea; // Para recoger cada línea
		String[] datos; // Para partir la línea de nuevas compras en 4 datos

		while ((linea = ficheroLeer.readLine()) != null) { // Lee una l�nea de texto
			datos = linea.split(",");
			int idCompra = Integer.parseInt(datos[0]);
			int idProveedor = Integer.parseInt(datos[1]);
			int idArticulo = Integer.parseInt(datos[2]);
			int cantidad = Integer.parseInt(datos[3]);
			System.out.println("Id de la compra" +idCompra);
			// Comprobamos el id de la compra
			
			boolean idCompraValido = ComprobarIdCompra(conexion, idCompra);
			boolean idProveedorValido = ComprobarIdProveedor(conexion, idProveedor);
			boolean idArticuloValido = ComprobarIdArticulo(conexion, idArticulo);
			boolean cantidadEsValida=false;// marcamos como falso la cantidad
			if (cantidad>0) {
				cantidadEsValida=true; //Si es mayor que 0 será valdia
			}
			if(idCompraValido==true && idArticuloValido==true && cantidadEsValida==true) {
				String sqlPrepared = "INSERT INTO compras VALUES( ?, ?, ?,?,? )";
  			    
				    
				PreparedStatement sentencia = conexion.prepareStatement(sqlPrepared);				
				sentencia.setInt(1, idCompra);
				java.sql.Date fechaHoySQL = new java.sql.Date(new java.util.Date().getTime());
				sentencia.setDate(2, fechaHoySQL);
				sentencia.setInt(3, idProveedor);
				sentencia.setInt(4, idArticulo);
				sentencia.setInt(5, cantidad);
				
				int filas;//
				try {
					  filas = sentencia.executeUpdate();				// Ejecuta el INSERT INTO
					  System.out.println("Filas afectadas: " + filas);
					
					} catch (SQLException e) {
						System.out.println("HA OCURRIDO UNA EXCEPCI�N:"); 
					    System.out.println("Mensaje:    "+ e.getMessage()); 
					    System.out.println("SQL estado: "+ e.getSQLState()); 
				    	System.out.println("C�d error:  "+ e.getErrorCode());  
					}
				sentencia.close(); // Cerrar Statement
			}
			System.out.println();

		}
	

	}// insertaEnCompras

	// COMPRUEBA SI LA COMPRA EXISTE - SI NO EXISTE DEVUELVE FALSE ///////////////
	private static boolean ComprobarIdCompra(Connection conexion, int idc) throws SQLException {

		// Consultamos las id de compras existentes
		Statement sentencia = conexion.createStatement();
		String sql = "SELECT idCompra FROM compras";

		ResultSet res = sentencia.executeQuery(sql);

		// Hace el tratamiento de los datos. En este caso los imprime recorriendo el
		// ResultSet
		while (res.next()) {
			int idCompraBBDD = res.getInt(1);

			if (idCompraBBDD == idc) { // Si exite uno igual devolver false
				System.out.println("Id de compra ya existente, no se puede agregar");
				return false;
				
			}
		}

		res.close(); // Paso 8. Libera objeto ResultSet
		sentencia.close(); // Paso 9. Libera objeto Statement

		return true;
	}// ComprobarIdCompra

	// COMPRUEBA SI EL PROVEEDOR EXISTE - SI NO EXISTE DEVUELVE FALSE
	// ///////////////
	private static boolean ComprobarIdProveedor(Connection conexion, int idp) throws SQLException {

		boolean existeProv = false; // Lo usamos para verificar si existe el proveedor
		// Consultamos las id de proveedores existentes
		Statement sentencia = conexion.createStatement();
		String sql = "SELECT idProv FROM proveedores";

		ResultSet res = sentencia.executeQuery(sql);

		// Hace el tratamiento de los datos. En este caso los imprime recorriendo el
		// ResultSet
		while (res.next()) {
			int idProveedorBBDD = res.getInt(1);

			if (idProveedorBBDD == idp) { // Si hay una coincidencia
				existeProv = true;
			}
		}

		if (existeProv) { // si existe devuelve true
			return true;
		} else { // si no existe se agrega el proveedor
			System.out.println("Hay que dar de alta al proveedor");
			// DAtos del nuevo proveedor
			String nombreProv = "nombreProveedor" + "" + idp;
			String direccionProv = "direccionProveedor" + "" + idp;
			String poblacionProv = "poblacionProveedor" + "" + idp;
			String telefonoProv = "telefonoProveedor" + "" + idp;
			String nif = "12312312Z";

			String sqlUpdate = String.format(
					"INSERT INTO proveedores (idprov, nombre, direccion, poblacion, telef, nif) VALUES(%d,'%s','%s','%s','%s','%s')",
					idp, nombreProv, direccionProv, poblacionProv, telefonoProv, nif);



			Statement sentenciaUpdate = conexion.createStatement();
			int filas = sentenciaUpdate.executeUpdate(sqlUpdate);
			System.out.printf("Empleados modificados: %d %n", filas);

			sentencia.close(); // Cerrar Statement

		}

		sentencia.close(); // Paso 9. Libera objeto Statement
		return true;

	}// ComprobarIdProveedor

	// COMPRUEBA SI EL ARTICULO EXISTE - SI NO EXISTE DEVUELVE FALSE ///////////////
	private static boolean ComprobarIdArticulo(Connection conexion, int ida) throws SQLException {
		boolean existeArticulo = false; // Lo usamos para verificar si existe el proveedor
		// Consultamos las id de articulos existentes
		Statement sentencia = conexion.createStatement();
		String sql = "SELECT idArt FROM articulos where idArt="+ida;

		ResultSet res = sentencia.executeQuery(sql);

		// Hace el tratamiento de los datos. En este caso los imprime recorriendo el
		// ResultSet
		
		while (res.next()) {
			int idArticuloBBDD = res.getInt(1);
			if (idArticuloBBDD == ida) { // Si hay una coincidencia
				existeArticulo = true;
			} else {// si no esta en la base de datos marcamos false
				existeArticulo = false;
			}
		}
		if(existeArticulo==false) {
			System.out.println("El articulo no existe, no se puede ingresar la compra");
		}

		res.close(); // Paso 8. Libera objeto ResultSet
		sentencia.close(); // Paso 9. Libera objeto Statement
		return existeArticulo;

	}// ComprobarIdArticulo

	// Da de alta al proveedor idProveedor ///////////////////// ///////////////
	private static void altaDeProveedor(Connection conexion, int idProveedor) throws SQLException {

	}// altaDeProveedor

} // class Exam1ev_2024
