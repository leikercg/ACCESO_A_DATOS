import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejer1_2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*
		 * Crea un fichero de texto con algún editor de textos y después realiza un
		 * programa Java que visualice su contenido. Cambia el programa Java para que el
		 * nombre del fichero se acepte al ejecutar el programa desde la línea de
		 * comandos.
		 */
		
		System.out.println("Indica la ruta del fichero:");
		
		Scanner t = new Scanner(System.in);
		String ruta= t.next();
		t.close();
		System.out.println("Ruta recibida");
		
		  File fichero = new File(ruta); //declarar fichero
		    FileReader fic;
			try {
				fic = new FileReader(fichero);
				 int i;
				    while ((i = fic.read()) != -1) //se va leyendo un car�cter
				      System.out.print( (char) i);   // (char) convierte el valor entero i a car�cter
				    
				    /* Se puede leer varios caracteres creando un array de caracteres
				    char b[]= new char[5]; 
				    while ((i = fic.read(b)) != -1)	    
						System.out.println(b); 
				    */
				       
				    fic.close(); //cerrar fichero  
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //crear el flujo de entrada  
		    
		        
		
	}

}