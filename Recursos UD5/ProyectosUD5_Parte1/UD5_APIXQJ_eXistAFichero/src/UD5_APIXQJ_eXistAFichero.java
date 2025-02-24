// Ejemplo de guardar documento desde BD eXist a Fichero con API XQJ

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;

import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;

import javax.xml.xquery.XQResultSequence;
import net.xqj.exist.ExistXQDataSource;


public class UD5_APIXQJ_eXistAFichero {

	public static void main(String[] args) throws IOException, ParserConfigurationException {

		consultaCreaFicheroResultado("resultado.xml");
	}

	public static void consultaCreaFicheroResultado(String nombreArchivoXML) {
		File fichero = new File(nombreArchivoXML);
		XQDataSource server = new ExistXQDataSource();
		try {
			server.setProperty("serverName", "localhost");
			server.setProperty("port", "8090");
			XQConnection conn = server.getConnection();
			XQPreparedExpression consulta = conn.prepareExpression(""
						+ "let $titulo:= collection('/db/ColeccionPruebas')/EMPLEADOS/TITULO  "
						+ " return  <EMPLEADOS>{$titulo} " + " {for $em in collection('/db/ColeccionPruebas')"
						+ "/EMPLEADOS/fila_empleado[DEPT_NO=10] return $em} " + " </EMPLEADOS> ");
			XQResultSequence result = consulta.executeQuery();
			if (fichero.exists()) { // borramos el fichero si existe y se crea de nuevo
				if (fichero.delete())
					System.out.println("Archivo borrado, lo creo de nuevo.");
				else
					System.out.println("Error al borrar el archivo");
			}
			try {	// Escribe los datos en el fichero
				BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivoXML));
				bw.write("<?xml version='1.0' encoding='ISO-8859-1'?>" + "\n");
				result.next();
				// Al método getItemAsString se le pueden pasr perçametros que especifican cómo se convierte
				// https://www.w3.org/TR/xslt-xquery-serialization/#XML_PARAMS
				String cad = result.getItemAsString(null);  // Convierte resultado consulta a String
				System.out.println(" output " + cad); 		// Visualizamos en la consola
				bw.write(cad + "\n"); 						// Escribimos en el fichero
				bw.close(); 								// Cerramos el fichero
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			conn.close();
		} catch (XQException e) {
			e.printStackTrace();
		}

	}

}  // fin clase

