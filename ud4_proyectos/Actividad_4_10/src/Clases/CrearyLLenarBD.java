package Clases;
// Operaciones b�sicas sobre Neodatis
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class CrearyLLenarBD {
	
	public static void main(String[] args) {
		
		Pais p1 = new Pais(1, "España");
		Pais p2 = new Pais(2, "Francia");
		Pais p3 = new Pais(3, "Italia");
		Pais p4 = new Pais(4, "Irlanda");
		
		Jugador j1 = new Jugador("Maria", "voleibol", "Madrid", 14, p1);
		Jugador j2 = new Jugador("Miguel", "tenis", "Madrid", 15, p1);
		Jugador j3 = new Jugador("Mario", "baloncesto", "Guadalajara", 15, p2);
		Jugador j4 = new Jugador("Alicia", "tenis", "Madrid", 14, p2);
		Jugador j5 = new Jugador("Luis", "padel", "Guadalajara", 25, p3);
		Jugador j6 = new Jugador("Alberto", "padel", "Madrid", 24, p3);
		
		// Abrir BD
		ODB odb = ODBFactory.open("EQUIPOS.DB");

		// Almacenamos los objetos pais en la base de datos	
		odb.store(p1); 
		odb.store(p2);
		odb.store(p3);
		odb.store(p4);
		
		// Almacenamos los objetos jugador en la base de datos	
		
		odb.store(j1); 
		odb.store(j2);
		odb.store(j3);
		odb.store(j4);
		odb.store(j5);
		odb.store(j6);
		
		odb.close(); // Cerrar BD		
	
	}// fin main			
} // fin clase
