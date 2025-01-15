// Aplicación que visualiza desde una aplicación Cliente los datos en una BBDD Neodatis
// que se está ejecutando en modo Servidor (puerto = 8000, nombre asignado al fichero de BBDD = base1)

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class UD4_Neodatis_VerBDenServidor {
	public static void main(String[] args) {
		ODB odb = null;
		try {
			odb = ODBFactory.openClient("localhost", 8000, "base1");
			Objects<Jugadores> objects = odb.getObjects(Jugadores.class);
			System.out.printf("%d Jugadores: %n", objects.size());
			int i = 1;
			// visualizar los objetos
			while (objects.hasNext()) {
				Jugadores jug = objects.next();
				System.out.printf("%d: %s, %s, %s %n", i++, 
						jug.getNombre(), jug.getDeporte(), jug.getCiudad(),	jug.getEdad());
			}

		} finally {
			if (odb != null) 
				odb.close();
			
		}  // fin finally		
	}  // fin main
}  // fin clase UD4_Neodatis_VerBDenServidor
