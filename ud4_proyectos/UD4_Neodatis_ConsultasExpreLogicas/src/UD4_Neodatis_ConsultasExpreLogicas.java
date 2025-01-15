// Consultas con expresiones lógicas sobre BD NeoDatis

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Not;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.Or;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class UD4_Neodatis_ConsultasExpreLogicas {
	
	public static void main(String[] args) {
		ODB odb = ODBFactory.open("neodatis.test");// Abrir BD
		 
		// Edad igual a 14
		ICriterion criterio = Where.equal("edad", 14);
		IQuery query = new CriteriaQuery(Jugador.class, criterio);
		Objects<Jugador> jugadores = odb.getObjects(query); // Recupera la colección de objetos
		System.out.println("Jugadores edad igual a 14: " + jugadores.size());
		while (jugadores.hasNext()) {
			Jugador j = (Jugador) jugadores.next();
			System.out.println("\t" + j.getNombre() + ", " + j.getDeporte() + ", " + j.getEdad());
		}
		
		// Edad menor que 14
		criterio = Where.lt("edad", 14);
		query = new CriteriaQuery(Jugador.class, criterio);
		jugadores = odb.getObjects(query); // Recupera la colección de objetos
		System.out.println("Jugadores edad menor a 14: " + jugadores.size());
		while (jugadores.hasNext()) {
			Jugador j = (Jugador) jugadores.next();
			System.out.println("\t" + j.getNombre() + ", " + j.getDeporte() + ", " + j.getEdad());
		}
		
		// Edad menor o igual que 14
			criterio = Where.le("edad", 14);
			query = new CriteriaQuery(Jugador.class, criterio);
			jugadores = odb.getObjects(query); // Recupera la colección de objetos
			System.out.println("Jugadores edad menor e igual a 14: " + jugadores.size());
			while (jugadores.hasNext()) {
				Jugador j = (Jugador) jugadores.next();
				System.out.println("\t" + j.getNombre() + ", " + j.getDeporte() + ", " + j.getEdad());
		}

		// Nombre empieza por la letra M
		criterio = Where.like("nombre", "M%");
		query = new CriteriaQuery(Jugador.class, criterio);
		jugadores = odb.getObjects(query); // Recupera la colección de objetos
		System.out.println("Jugadores con nombre que empiza por M: " + jugadores.size());
			while (jugadores.hasNext()) {
			Jugador j = (Jugador) jugadores.next();
			System.out.println("\t" + j.getNombre() + ", " + j.getDeporte() + ", " + j.getEdad());
		}
			
		// Ciudad Madrid y edad 15
		criterio = new And().add(Where.equal("ciudad", "Madrid"))
							.add(Where.equal("edad", 15));
		query = new CriteriaQuery(Jugador.class, criterio);
		jugadores = odb.getObjects(query); // Recupera la colección de objetos
		System.out.println("Jugadores de Madrid y edad 15: " + jugadores.size());
			while (jugadores.hasNext()) {
			Jugador j = (Jugador) jugadores.next();
			System.out.println("\t" + j.getNombre() + ", " + j.getDeporte() + ", " + j.getEdad());
		}
			
		// Ciudad Madrid o edad mayor o igual que 15
		criterio = new Or().add(Where.equal("ciudad", "Madrid"))
						   .add(Where.ge("edad", 15));
		query = new CriteriaQuery(Jugador.class, criterio);
		jugadores = odb.getObjects(query); // Recupera la colección de objetos
		System.out.println("Jugadores de Madrid y edad mayor o igual que 15: " + jugadores.size());
			while (jugadores.hasNext()) {
			Jugador j = (Jugador) jugadores.next();
			System.out.println("\t" + j.getNombre() + ", " + j.getDeporte() + ", " + j.getEdad());
		}	
		
		// Nombre no empieza por letra M
		criterio = Where.not(Where.like("nombre", "M%"));		// Negación con método not de Where
		query = new CriteriaQuery(Jugador.class, criterio);
		jugadores = odb.getObjects(query); // Recupera la colección de objetos
		System.out.println("Jugadores con nombre que no empieza por M: " + jugadores.size());
			while (jugadores.hasNext()) {
			Jugador j = (Jugador) jugadores.next();
			System.out.println("\t" + j.getNombre() + ", " + j.getDeporte() + ", " + j.getEdad());
		}
			
		// Nombre no empieza por letra A
		criterio = new Not(Where.like("nombre", "A%"));			// Negación con la clase Not
		query = new CriteriaQuery(Jugador.class, criterio);
		jugadores = odb.getObjects(query); // Recupera la colección de objetos
		System.out.println("Jugadores con nombre que no empieza por A: " + jugadores.size());
		while (jugadores.hasNext()) {
		Jugador j = (Jugador) jugadores.next();
			System.out.println("\t" + j.getNombre() + ", " + j.getDeporte() + ", " + j.getEdad());
		}
		
		odb.close(); // Cerrar BD
	}  // fin main
}  // fin clase
