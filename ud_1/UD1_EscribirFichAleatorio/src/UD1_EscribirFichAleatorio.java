/* Abre el fichero en modo "rw" e inserta registros con datos de empleado (total 36 bytes):
	- Identificador (entero = 4 bytes)
	- Apellido (10 caracteres UNICODE, 2 bytes x carácter)
	- Nº Departamento (entero = 4 bytes)
	- Salario (double = 8 bytes)
   Los datos se escriben desde un array con elementos de cada uno de los tipos y
   el identificador es el indice del array + 1.
   En el caso del apellido para que todos ocupen 10 caracteres utiliza un buffer.
*/

import java.io.*;

public class UD1_EscribirFichAleatorio {
  
public static void main(String[] args) throws IOException {      
   
	File fichero = new File("AleatorioEmple.dat");
   
   //declara el fichero de acceso aleatorio
   RandomAccessFile file = new RandomAccessFile(fichero, "rw");
   
   //arrays con los datos
   String apellido[] = {"FERNANDEZ","GIL","LOPEZ","RAMOS","SEVILLA","CASILLA", "REY"};//apellidos 
   int dep[] = {10, 20, 10, 10, 30, 30, 20};       //departamentos
   Double salario[] = {1000.45, 2400.60, 3000.0, 1500.56, 2200.0, 1435.87, 2000.0};//salarios
   
   StringBuffer buffer = null;//buffer para almacenar apellido de forma que todos ocupen 10 chars
   int n = apellido.length;//numero de elementos del array
   
   for (int i=0;i<n; i++){ //recorro los arrays          	  
	 file.writeInt(i+1); //uso i+1 para identificar empleado
     buffer = new StringBuffer( apellido[i] );      
     buffer.setLength(10); //10 caracteres para el apellido
     file.writeChars(buffer.toString());//insertar apellido
	 file.writeInt(dep[i]);       //insertar departamento
	 file.writeDouble(salario[i]);//insertar salario
   }     
   file.close();  //cerrar fichero 
   }
}
