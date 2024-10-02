/* Programa que produce una excepci�n
 * 
 */

public class UD1_EjemploExcepcion {
  
  public static void main(String[] args) {   
 
	int nume=10, denom=0, cociente;
	
	/* C�digo que provoca la excepci�n
	cociente=nume/denom;
    System.out.println("Resultado:" +cociente); 
    */
    
    /* Soluci�n para capturar la excepcion */
    try {
     	cociente=nume/denom;
      	System.out.println("Resultado:" +cociente); 	
    }	
	catch(ArithmeticException a) {	// Captura la excepci�n y muestra varias informaciones de la misma 		
	   System.err.println("getLocalizedMessage():" +
	      a.getLocalizedMessage());
	   System.out.println("toString():" +
	     a.toString());
	   System.err.println("getMessage():" +
	      a.getMessage());	  
	}
		
	catch (NumberFormatException n) {		
		 System.err.println("Exception");
	}
    
	catch (Exception e) {
		e.printStackTrace();
	}
    
	finally {
		System.out.println("se ejecuta siempre");
	}
	    
  } //

} //
