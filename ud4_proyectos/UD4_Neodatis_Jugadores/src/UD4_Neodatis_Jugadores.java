// Operaciones básicas sobre Neodatis
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class UD4_Neodatis_Jugadores {
	
	public static void main(String[] args) {
		// Crear varias instancias del objeto de clase Jugador
		Jugador j1 = new Jugador("Maria", "voleibol", "Madrid", 14);
		Jugador j2 = new Jugador("Miguel", "tenis", "Madrid", 15);
		Jugador j3 = new Jugador("Mario", "baloncesto", "Guadalajara", 15);
		Jugador j4 = new Jugador("Alicia", "tenis", "Madrid", 14);
		
		// Abrir BD
		ODB odb = ODBFactory.open("neodatis.test");

		// Almacenamos los onjetos jugador en la base de datos
		odb.store(j1); 
		odb.store(j2);
		odb.store(j3);
		odb.store(j4);

		// Recuperamos todos los jugadores
		// OJO. Objects no es la clase Object. Objects implementa la interface Collection
		Objects<Jugador> objetos = odb.getObjects(Jugador.class);
		System.out.printf("%d Jugadores: %n", objetos.size());
		int i = 1;
		// Recorre la colección de objetos		
		while(objetos.hasNext()){
			Jugador jug = objetos.next();	// Asigna cada elemento de la coleccion a una instancia de la clase Jugador
			System.out.printf("%d: %s, %s, %s %n", i++, jug.getNombre(), jug.getDeporte(),	jug.getCiudad(), jug.getEdad());   
		}
		odb.close(); // Cerrar BD		
	}// fin main			
} // fin clase
