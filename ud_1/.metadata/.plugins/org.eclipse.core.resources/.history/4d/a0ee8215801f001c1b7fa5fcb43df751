
import java.io.*;

public class UD1_Actividad1_3_Insercion {
	public static void main(String[] args) throws IOException {     
		
		if(args.length!=4){
			System.out.println("ERROR. SON NECESARIOS 4 ARGUMENTOS");
			System.exit(0);
		}
		
		File fichero = new File("AleatorioEmple.dat");
		
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");
		   
		StringBuffer buffer = null;	
		   
		String apellido=args[1];
		int id = 0,dep = 0;
		Double salario = null; 
		
		try{
			id = Integer.parseInt(args[0]);   
		}catch(java.lang.NumberFormatException tipoerroneo){
			System.out.println("ERROR. TIPO DE ARGUMENTO DE ID INCORRECTO");
			System.exit(1);
		} 
		try{
			dep = Integer.parseInt(args[2]);   
		}catch(java.lang.NumberFormatException tipoerroneo){
			System.out.println("ERROR. TIPO DE ARGUMENTO DE DEP INCORRECTO");
			System.exit(1);
		}   
		try{
			salario = Double.parseDouble(args[3]);   
		}catch(java.lang.NumberFormatException tipoerroneo){
			System.out.println("ERROR. TIPO DE ARGUMENTO DE SALARIO INCORRECTO");
			System.exit(1);
		} 
		
		boolean flag = false;
		long posicion = (id -1 ) * 36; 
		long i=0;
		
		for(;;){ //comprobar si ya existe o no
			file.seek(i); 
			int newid = file.readInt();   	
			if(newid==id)
			    	flag=true;
			i= file.getFilePointer()+20+4+8; 
			
			if (i==file.length())
		    	break;
		   
		}
		
		if(flag)     
			System.out.println("Error, el registro ya existe");
		else{
			file.seek(posicion);
			file.writeInt(id); 
			buffer = new StringBuffer( apellido);      
			buffer.setLength(10); 
			file.writeChars(buffer.toString());
			file.writeInt(dep);      
			file.writeDouble(salario);
			System.out.println("Datos insertados");
		} 
		
			
		file.close();  
	}
}

