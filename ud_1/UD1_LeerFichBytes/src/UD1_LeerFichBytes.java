

import java.io.*;

public class UD1_LeerFichBytes {
  public static void main(String[] args) throws IOException {
   File fichero = new File("FichBytes.dat");
   FileInputStream fic = new FileInputStream(fichero);    
   int i;
	
   while ((i = fic.read()) != -1)
	    System.out.println(i);
	
    fic.close();    
  }
}