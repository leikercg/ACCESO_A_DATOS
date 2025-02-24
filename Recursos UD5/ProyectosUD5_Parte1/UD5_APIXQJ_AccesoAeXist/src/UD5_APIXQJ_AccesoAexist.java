// Acceso a eXist con API XQJ

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import net.xqj.exist.ExistXQDataSource;

import java.io.IOException;

public class UD5_APIXQJ_AccesoAexist {

	public static void main(String[] args) throws IOException, ParserConfigurationException {
		
		try {
			// Establece la conexión con la BD
			XQDataSource server = new ExistXQDataSource();
			server.setProperty("serverName", "localhost");
			server.setProperty("port", "8090");
			server.setProperty("user", "admin");
			server.setProperty("password", "Ad1rectory");
			XQConnection conn = server.getConnection();

			System.out.println("------- Consulta documentos productos.xml ------");
			
			// Define la consulta con sintaxis XQuery
			XQPreparedExpression consulta = conn.prepareExpression("for $de in doc('/ColeccionPruebas/productos.xml')/productos/produc return $de");
			// Ejecuta la consulta y obtiene el resultado
			XQResultSequence resultado = consulta.executeQuery();
			// Recorre los elementos resultado
			while (resultado.next()) {
				System.out.println("Elemento getItem " + resultado.getSequenceAsString(null));
			}
			conn.close();
			
		} catch (XQException ex) {
			System.out.println("Error en las propiedades del server.");
			System.out.println("Error al operar.");
			ex.printStackTrace();
		}
	}

}  // fin clase
