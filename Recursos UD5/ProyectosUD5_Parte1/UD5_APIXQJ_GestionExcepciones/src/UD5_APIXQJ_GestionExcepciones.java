// Gesti�n de excepciones en BD eXist con API XQJ

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import net.xqj.exist.ExistXQDataSource;

public class UD5_APIXQJ_GestionExcepciones {

	public static void main(String[] args) {
		XQConnection conn = null;
		XQDataSource server = new ExistXQDataSource();
		XQPreparedExpression consulta = null;
		XQResultSequence resultado = null;
		
		try { 	// Define las propiedades para la conexi�n
			server.setProperty("serverName", "localhost");
			server.setProperty("port", "8080");
		} catch (XQException e) {
			System.out.println("Error en el servidor. Mensaje : " + e.getMessage());
			System.out.println("Error en el servidor. Causa :  " + e.getCause());
		}
		
		try {  // Realiza la conexi�n
			conn = server.getConnection();
		} catch (XQException e) {
			System.out.println("Error en la conexi�n : " + e.getMessage());
		}

		System.out.println("--------Ejemplo Consulta Productos -----------------");
		try {  // Realiza la consulta 
			consulta = conn.prepareExpression("forr $de in /productos/produc return $de");
		} catch (XQException e) {
			System.out.println("Error en la expresi�n. Mensaje : " + e.getMessage());
			System.out.println("Error en la expresi�n. Causa : " + e.getCause());
		}

		
		try {  // Recorre el resultado de la consulta
			resultado = consulta.executeQuery();
			while (resultado.next()) {
				System.out.println("Elemento " + resultado.getItemAsString(null));
			}
		} catch (XQException e) {
			System.out.println("Error en la ejecuci�n. Mensaje: " + e.getMessage());
			System.out.println("Error en la ejecuci�n. Causa: " + e.getCause());
		}

		try {  // Cierra la conexi�n
			conn.close();
		} catch (XQException e) {
			System.out.println("Error al cerrar la conexi�n.");
		}
		
	} // fin main

}  // fin clase
