package Clases;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class Actividad_4_12 {

	public static void main(String[] args) {
		
		Actividad_4_12A("Italia", "padel");
		Actividad_4_12B("Italia");

	}	

	private static void Actividad_4_12A(String pais, String depor) {
		
		ODB odb = ODBFactory.open("EQUIPOS.DB");// Abrir BD
		
		ICriterion criterio = new And().add(Where.equal("pais.nombrePais", pais))
									   .add(Where.equal("deporte", depor));
		IQuery query = new CriteriaQuery(Jugador.class, criterio);
		Objects<Jugador> jugadores = odb.getObjects(query); // Recupera la colecci�n de objetos
		if (jugadores.size() == 0) {
			System.out.println("No hay jugadores de ese país que practiquen ese deporte");
		} else {
			while (jugadores.hasNext()) {
				Jugador j = (Jugador) jugadores.next();
				System.out.println("\t" + j.getNombre() + ", " + j.getDeporte() + ", " + j.getEdad());
			}
		}	
		odb.close();
	} // fin método Actividad_4_12A
		
	private static void Actividad_4_12B(String pais) {
		
		ODB odb = ODBFactory.open("EQUIPOS.DB");// Abrir BD		
		
		ICriterion criterio = Where.equal("nombrePais", pais);
		IQuery query = new CriteriaQuery(Pais.class, criterio);
		Objects<Pais> paises = odb.getObjects(query);
		if (paises.size() == 0) {
			System.out.println("No existe el pais " + pais);
		} else {
			Pais paisaborrar = (Pais) paises.next();
			// Comprobar si hay jugadores en ese pais
			IQuery queryJugador = new CriteriaQuery(Jugador.class, Where.equal("pais.nombrePais", pais));
			Objects<Jugador> jugadores = odb.getObjects(queryJugador);
			if (jugadores.size() == 0) {
				System.out.println("No hay jugadores de ese país " + pais);
			} else {
				while (jugadores.hasNext()) {
					Jugador j = (Jugador) jugadores.next();
					// Actualizo el atributo pais a NULL y lo actualizo en la BD
					j.setPais(null);
					odb.store(j);
				}
				odb.commit();
			}
			// Elimino el pais de la clase Pais
			odb.delete(paisaborrar);
			odb.commit();		
		}
		odb.close();
		
	} // fin método Actividad_4_12B
	
	
} // fin de la clase
