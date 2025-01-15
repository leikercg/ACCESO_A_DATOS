// Aplicación que inserta un objeto en en una BBDD Neodatis desde una aplicación CLiente
// La BD se está ejecutando en modo Servidor (puerto = 8000, nombre asignado al fichero de BBDD = base1)

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class UD4_Neodatos_InsertarObjetoenServidor {
	public static void main(String[] args) {
		ODB odb = null;
		try{
		odb = ODBFactory.openClient("localhost", 8000, "base1");
			Jugadores j4 = new Jugadores("Andrea", "padel", "Guadalajara", 14);
			odb.store(j4);
			odb.commit();
			System.out.println("Jugador Insertado...");
		} finally {
			if (odb != null) {
				odb.close();				
			}
		}
	}// --main
}
