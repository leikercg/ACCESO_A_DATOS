package Clases;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class Actividad_4_11 {

	public static void main(String[] args) {
		ODB odb = ODBFactory.open("EQUIPOS.DB");// Abrir BD
	 
		// deporte = tenis
		ICriterion criterio = Where.equal("deporte", "tenis");
		IQuery query = new CriteriaQuery(Jugador.class, criterio);
		query.orderByAsc("nombre,edad");
		// aplico la query
		Objects<Jugador> jugadores = odb.getObjects(query); // Recupera la colección de objetos
		System.out.println("Jugadores que practican tenis: " + jugadores.size());
		while (jugadores.hasNext()) {
			Jugador j = (Jugador) jugadores.next();
			System.out.println("\t" + j.getNombre() + ", " + j.getDeporte() + ", " + j.getEdad() 
				+ ", " + j.getPais().getNombrePais());
		}
		odb.close(); // Cerrar BD
	}
}
