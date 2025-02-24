// Ejemplos de consultas de datos con API XQJ sobre eXist

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.exist.ExistXQDataSource;

import java.io.IOException;


public class UD5_APIXQJ_ConsultaDatosEneXist {

	public static void main(String[] args) throws IOException, ParserConfigurationException {
	//	consultarProductos();
	//  cuentaProductos();		// Consulta con función de agrupación de elementos (count)
	//	numProductosxZona();	// Consulta que asocia (JOIN) datos de dos documentos

	}

	public static void consultarProductos() {
		try {
			// Conexión
			XQDataSource server = new ExistXQDataSource();
			server.setProperty("serverName", "localhost");
			server.setProperty("port", "8090");
			server.setProperty("user", "admin");
			server.setProperty("password", "Ad1rectory");
			XQConnection conn = server.getConnection();
			// Consulta
			XQPreparedExpression consulta;
			XQResultSequence resultado;
			System.out.println("------- Consulta documentos productos.xml ------");
			consulta = conn.prepareExpression("for $de in collection('/db/BDProductosXML')/productos/produc return $de");
			resultado = consulta.executeQuery();
			// Recorrer resultado
			while (resultado.next()) {
				System.out.println("Elemento getItem " + resultado.getSequenceAsString(null));				
			}
			conn.close();
		} catch (XQException ex) {
			System.out.println("Error en las propiedades del server.");
			System.out.println("Error al operar.");
			ex.printStackTrace();
		}
	}  // fin consultarProductos

	public static void cuentaProductos() {
		try {
			// Conexión
			XQDataSource server = new ExistXQDataSource();
			server.setProperty("serverName", "localhost");
			server.setProperty("port", "8090");
			server.setProperty("user", "admin");
			server.setProperty("password", "Ad1rectory");
			XQConnection conn = server.getConnection();
			// Consulta y muestra el resultado
			XQPreparedExpression consulta = conn.prepareExpression(" count(collection('/db/BDProductosXML')/productos/produc[precio>50] )");
			XQResultSequence resultado = consulta.executeQuery();
			resultado.next();
			System.out.println("--------------------------------------------");
			System.out.println("Número de productos con precio > de 50: " + resultado.getInt());
			conn.close();
		} catch (XQException ex) {
			System.out.println("Error en las propiedades del server.");
			System.out.println("Error al operar.");
			Logger.getLogger(UD5_APIXQJ_ConsultaDatosEneXist.class.getName()).log(Level.SEVERE, null, ex);
		}
	}  // fin cuentaProductos
	
	public static void numProductosxZona() {
		try {
			XQDataSource server = new ExistXQDataSource();
			server.setProperty("serverName", "localhost");
			server.setProperty("port", "8090");
			server.setProperty("user", "admin");
			server.setProperty("password", "Ad1rectory");
			XQConnection conn = server.getConnection();
   		    XQPreparedExpression consulta = conn.prepareExpression(""
   		    				+ "for $zo in collection('/db/BDProductosXML')/zonas/zona "
							+ "let $cu:= count(collection('/db/BDProductosXML')/"
							+ "productos/produc[cod_zona=$zo/cod_zona]) "
							+ " return  concat($zo/nombre,', productos: ', $cu, '\n') ");
			XQResultSequence resultado = consulta.executeQuery();
			System.out.println("-------------------------------------");
			System.out.println("Número de productos por cada zona ");
			while (resultado.next()) {
				System.out.println(resultado.getSequenceAsString(null));
			}
			conn.close();
		} catch (XQException ex) {
			System.out.println("Error en las propiedades del server.");
			System.out.println("Error al operar.");
			Logger.getLogger(UD5_APIXQJ_ConsultaDatosEneXist.class.getName()).log(Level.SEVERE, null, ex);
		}
	}  // fin numProductosxZona
	
	
}  // fin clase
