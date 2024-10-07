/* Lee la informaci�n de un fichero .dat que contiene objetos Persona
 * crea la estrucutra del fichero como DOM en memoria
 * y finalmente a partir del DOM en memoria crea un fichero XML 
 */

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class UD1_Actividad1_5 {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Persona persona;

		File fichero = new File("FichPersona3.dat");// declara el fichero
		FileInputStream filein = new FileInputStream(fichero);// crea el flujo
																// de entrada
		// conecta el flujo de bytes al flujo de datos
		ObjectInputStream dataIS = new ObjectInputStream(filein);

		// creamos el �rbol DOM
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {

			// Creación de cabecera DOM
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null, "Personas", null);
			document.setXmlVersion("1.0"); // asignamos la version de nuestro
											// XML

			// LECTURA DEL FICHERO Y CREACI�N DEL ARBOL DOM

			try {
				while (true) { // lectura del fichero
					persona = (Persona) dataIS.readObject(); // leer una Persona
					System.out.println("Nombre: " + persona.getNombre() + ", edad: " + persona.getEdad());
					Element raiz = document.createElement("persona"); // nodo
																		// persona
					document.getDocumentElement().appendChild(raiz);
					CrearElemento("nombre", persona.getNombre().trim(), raiz, document); // a�adir
																							// nombre
					CrearElemento("edad", Integer.toString(persona.getEdad()), raiz, document); // a�adir
																								// edad
				} // fin while

			} catch (EOFException eo) {
			} catch (StreamCorruptedException x) {
			}

			dataIS.close(); // cerrar stream de entrada

			// El Transforme lleva el arbol DOM al fichero de salida Personas.xml
			Source source = new DOMSource(document);
			Result result = new StreamResult(new java.io.File("Personas.xml"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);

			/*
			 * Si queremos que el resultado tambi�n salga por pantalla indicamos al
			 * Transformer que el result es la consola
			 */
			Result console = new StreamResult(System.out);
			transformer.transform(source, console);

		} catch (Exception e) {
			System.err.println("Error: " + e);
		}

	} // fin main

	// funci�n para a�adir los elementos al �rbol
	static void CrearElemento(String datoEmple, String valor, Element raiz, Document document) {
		Element elem = document.createElement(datoEmple); // creamos hijo
		Text text = document.createTextNode(valor); // damos valor
		raiz.appendChild(elem); // pegamos el elemento hijo a la raiz
		elem.appendChild(text); // pegamos el valor
	}
} // fin class