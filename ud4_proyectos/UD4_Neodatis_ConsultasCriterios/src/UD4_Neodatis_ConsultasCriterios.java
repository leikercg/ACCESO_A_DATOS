// Consultas sobre BD NeoDatis

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class UD4_Neodatis_ConsultasCriterios {
	
	public static void main(String[] args) {
		ODB odb = ODBFactory.open("neodatis.test");// Abrir BD
		
		// Define el criterio
		ICriterion criterio = Where.equal("deporte", "tenis");
		
		// Crea la consulta inidcando la clase y el criterio
		IQuery query = new CriteriaQuery(Jugador.class, criterio);
		
		//Ordena de forma ascendente por nombre y edad
		query.orderByAsc("nombre,edad"); // ordena ascendentemente por nombre y edad

		Objects<Jugador> jugadores = odb.getObjects(query); // Recupera la colecci�n de objetos
		
		System.out.println(jugadores.size() + " Jugadores:");
		while (jugadores.hasNext()) {
			Jugador j = (Jugador) jugadores.next();
			System.out.println("\t" + j.getNombre() + ", " + j.getDeporte() + ", " + j.getEdad());
		}
		
		/* Tambi�n se puede definir el criterio directamente en la clase CriteriaQuery
		* Adem�s, si en lugar de la colecci�n de objetos queremos s�lo el primer objeto
		* podemos usar el m�todo getFirst que devuelve la excepci�n IndexOutOfBoundsException
		* si no se encuentra ning�n objeto con ese criterio. 
		*/
		
		try {
			query = new CriteriaQuery(Jugador.class, Where.equal("deporte", "futbol"));
			Jugador j1 = (Jugador) odb.getObjects(query).getFirst(); // Recupera s�lo el primer objeto
		} catch (IndexOutOfBoundsException e) {
			System.out.println("NO SE HA ENCONTRADO NINGUN OBJETO QUE CUMPLA EL CRITERIO");
		}
			
		odb.close(); // Cerrar BD
	}  // fin main
}  // fin clase
