package Clases;
// Operaciones b�sicas sobre Neodatis
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.oid.OIDFactory;


public class VerDatosObjetos {
	
	public static void main(String[] args) {
		
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		// Recuperamos todos los PAISES
		Objects<Pais> paises = odb.getObjects(Pais.class);
		System.out.printf("%d Paises: %n", paises.size());
		// Recorre la colecci�n de objetos paises	
		while(paises.hasNext()){
			Pais p = paises.next();
			System.out.printf("%d: %s %n", p.getId(), p.getNombrePais());
		}
		
		System.out.println("--------------");
		
		Objects<Jugador> jugadores = odb.getObjects(Jugador.class);
		System.out.printf("%d Jugadores: %n", paises.size());
		// Recorre la colecci�n de objetos jugadores		
		while(jugadores.hasNext()){
			Jugador j = jugadores.next();
			System.out.printf("%s %s %s %d %s %n", 
					j.getNombre(), j.getDeporte(), j.getCiudad(), j.getEdad(), j.getPais().getNombrePais());
		}
		
		System.out.println("--------------");
				
		// Ver un pais por su OID
		OID oid = OIDFactory.buildObjectOID(3); // Obtener los datos de un OID 
		Pais pp = (Pais) odb.getObjectFromId(oid);  // Recupero el objeto que corresponde a un determinado OID
		System.out.printf("%d, %s %n", pp.getId(), pp.getNombrePais());
		
		// Obtener el OID de un determinado objeto e informaci�n de ese objeto
		OID oidpp = odb.getObjectId(pp);							// En este caso, la instancia de jugador tiene el OID 3
		int tipopp = odb.store(pp).getType(); 	
		// Tenemos las clases Pais y Jugador. Jugador es tipo 2
		System.out.printf("OID: %s, Tipo OID: %d %n", oidpp.toString(), tipopp);
		
		
		
		
		odb.close(); // Cerrar BD		
	}// fin main			
} // fin clase
