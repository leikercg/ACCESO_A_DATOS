
import java.io.*;
public class UD1_Actividad1_3_Consulta {
  public static void main(String[] args) throws IOException {

   if(args.length < 1) {
	   System.out.println("Introduce el empleado a consultar");
	   System.exit(1);
   }
  
   int  id,dep, posicion;    
   Double salario;  
   char apellido[] = new char[10], aux;
   int registro=0;
   
   try {		   
     registro = Integer.parseInt(args[0]); //recoger id de empleado a consultar      
   } catch (NumberFormatException n) {
	   System.out.println("Numero de empleado incorrecto");
	   System.exit(2);
   }
   
   //declara el fichero de acceso aleatorio
   File fichero = new File("AleatorioEmple.dat");  
   RandomAccessFile file = new RandomAccessFile(fichero, "r");
  
   posicion = (registro -1 ) * 36;
   if(posicion >= file.length() )     
      System.out.println("ID: " + registro + ","
      		+ " NO EXISTE EMPLEADO ID DEMASIADO GRANDE...");
    else{	
	   file.seek(posicion); //nos posicionamos 
	   id=file.readInt(); // obtengo id de empleado	  
	   
	   if(id != registro){
		   System.out.println("ID: " + registro + ","
		      		+ " NO EXISTE EMPLEADO - HUECO...");  
		   System.exit(3);
	   }
	  
	    for (int i = 0; i < apellido.length; i++) {
			 aux = file.readChar();//recorro uno a uno los caracteres del apellido 
			 apellido[i] = aux;    //los voy guardando en el array
	   }  	
	    
	  String apellidoS= new String(apellido);//convierto a String el array
	
	  dep=file.readInt();//obtengo dep
	  salario=file.readDouble();  //obtengo salario
		  
	  System.out.println("ID: " + registro + 
			  ", Apellido: "+      apellidoS.trim() + 
	         ", Departamento: "+dep + ", Salario: " + salario);        
    }  
   file.close();  //cerrar fichero 
   }
}//finnnn consulta
