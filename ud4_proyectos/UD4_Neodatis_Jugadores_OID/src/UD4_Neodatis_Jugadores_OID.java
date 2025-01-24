// Recuperar objetos desde BD Neodatis por su OID
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;
import org.neodatis.odb.core.oid.OIDFactory;

public class UD4_Neodatis_Jugadores_OID {
	
	public static void main(String[] args) {
		
		ODB odb = ODBFactory.open("neodatis.test"); // Abrir BD
		
		// Acceso al objeto a partir del identificador
		OID oid = OIDFactory.buildObjectOID(3); // Obtener los datos de un OID espec�fico por su n�mero 
		Jugador jug = (Jugador) odb.getObjectFromId(oid);  // Recupero el objeto que corresponde a un determinado OID
		System.out.printf("%s, %s, %s, %d %n %n", jug.getNombre(), jug.getDeporte(), jug.getCiudad(), jug.getEdad());
		
		// Obtener el OID de un determinado objeto e informaci�n de ese objeto
		OID oidjug = odb.getObjectId(jug);							// En este caso, la instancia de jugador tiene el OID 3
		int tipojug = odb.store(jug).getType(); 					// Tenemos las clases Pais y Jugador. Jugador es tipo 2
		System.out.printf("OID: %s, Tipo OID: %d %n", oidjug.toString(), tipojug);
		
		odb.close(); // Cerrar BD
	}  // fin main
}  // fin clase

