// Actualiza y elimina objetos sobre BD Neodatis
// Gestiona tambi�n las posibles excepciones

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.Objects;


public class UD4_Neodatis_ActualizaVarios {
	
	public static void main(String[] args) {
		
		ODB odb = null; // Declaro la variable ODB fuera del bloque try para que se vea en todo el m�todo  
		try {	
			odb = ODBFactory.open("neodatis.test");		// Abrir BD
			IQuery query = new CriteriaQuery(Jugador.class);
			Objects <Jugador> jugadores = odb.getObjects(query);
			if (jugadores.size() == 0)
				System.out.println("NO HAY JUGADORES");
			else {
				while(jugadores.hasNext()){
					Jugador jug = jugadores.next();	// Asigna cada elemento de la coleccion a una instancia de la clase Jugador
					jug.setDeporte("Tenis");
					odb.store(jug);
				}
				odb.commit();
			}
			
			
		} catch (IndexOutOfBoundsException e) {
			System.out.println("No se ha encontrado ning�n jugador con ese nombre");
		} catch (Exception e) {
			System.out.println("Error" + e.getMessage());
		} finally {
			if (odb != null) {
				odb.close();
			}
		} // fin try-catch
	}  // fin main
}  // fin clase
