// Actualiza y elimina objetos sobre BD Neodatis
// Gestiona tambi�n las posibles excepciones

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class UD4_Neodatis_ActualizayElimina {
	
	public static void main(String[] args) {
		
		ODB odb = null; // Declaro la variable ODB fuera del bloque try para que se vea en todo el m�todo  
		try {	
			odb = ODBFactory.open("neodatis.test");		// Abrir BD
			
			// -- Actualizar el deporte en el jugador de nombre Maria --
			IQuery query = new CriteriaQuery(Jugador.class, Where.equal("nombre", "Maria"));
			// Recupera el primer objeto que cumple la condici�n
			Jugador j = (Jugador) odb.getObjects(query).getFirst();
			// Actualizo el atributo
			j.setDeporte("pinpong");
			odb.store(j);  // Almaceno de nuevo el objeto en la BD
			
			// -- Eliminar el jugador de nombre Miguel --
			query = new CriteriaQuery(Jugador.class, Where.equal("nombre", "Mario"));
			// Recupera el primer objeto que cumple la condici�n
			j = (Jugador) odb.getObjects(query).getFirst();
			odb.delete(j);  // Elimino el objeto en a BD
			odb.commit();  // Valido los cambios
			
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
