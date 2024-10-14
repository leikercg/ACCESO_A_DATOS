
import java.util.Map;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.Connection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class UD2_JR_InformeEmpleados {

	public static void main(String[] args) {
		String reportSource = "./plantilla/ud2_EjemploInforme.jrxml";   // Especifica el informe a usar
		String reportHTML = "./informes/Informe.html";					// Ruta relativa donde deja el documento HTML   (dentro de la carpeta 'informes' del propio proyecto)
		String reportPDF = "./informes/Informe.pdf";					// Ruta relativa donde deja el documento PDF	(dentro de la carpeta 'informes' del propio proyecto)			
		String reportXML = "./informes/Informe.xml";					// Ruta relativa donde deja el documento XML	(dentro de la carpeta 'informes' del propio proyecto)

		// Paso de parámetros al informe a través de HashMap
		Map<String, Object> params = new HashMap<String, Object>();
		/* 
		params.put("titulo", "RESUMEN DATOS DE DEPARTAMENTOS.");
		params.put("autor", "ARM");
		params.put("fecha", (new java.util.Date()).toString());
		 */
		
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
			Class.forName("com.mysql.jdbc.Driver");
			// Identifico el origen de datos
			String url = "jdbc:mysql://localhost/ud2_xampp";
			String usuario = "alberto";
			String passwd = "alberto";
			Connection conn = (Connection) DriverManager.getConnection(url, usuario, passwd);
			JasperPrint MiInforme = JasperFillManager.fillReport(jasperReport, params, conn);
			// Visualizar en pantalla
			JasperViewer.viewReport(MiInforme);
			// Convertir a HTML
			JasperExportManager.exportReportToHtmlFile(MiInforme, reportHTML);
			// Convertir a PDF
			JasperExportManager.exportReportToPdfFile(MiInforme, reportPDF);
			// Convertir a XML, false es para indicar que no hay imágenes
			// (isEmbeddingImages)
			JasperExportManager.exportReportToXmlFile(MiInforme, reportXML, false);
			System.out.println("ARCHIVOS CREADOS");
		} catch (CommunicationsException c) {
			System.out.println(" Error de comunicación con la BD. No está arrancada.");
		} catch (ClassNotFoundException e) {
			System.out.println(" Error driver. ");
		} catch (SQLException e) {
			System.out.println(" Error al ejecutar sentencia SQL ");
		} catch (JRException ex) {
			System.out.println(" Error Jasper.");
			ex.printStackTrace();
		}
	}

}
