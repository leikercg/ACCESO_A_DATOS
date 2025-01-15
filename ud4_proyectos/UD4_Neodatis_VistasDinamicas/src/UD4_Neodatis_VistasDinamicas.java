// Consultas sobre BD NeoDatis con varios objetos que están relacionados por algún atributo
// Consultas equivalentes a JOINS SQL, que en NeoDatis se realizan con la API Object values

import java.math.BigDecimal;
import java.math.BigInteger;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

import clases.Jugador;


public class UD4_Neodatis_VistasDinamicas {

	public static void main(String[] args) {
		// Se llama a varios métodos, cada uno de los cuáles realiza una consulta con varios objetos (Vistas Dinámicas)
		JugadoresPaises();
		System.out.println("-----------------------------------------------------");
		JugadoresPaisesSpain();
		System.out.println("-----------------------------------------------------");
		
		contadorymediaporpais();
		System.out.println("-----------------------------------------------------");
		visualizarmediadeedad();
		System.out.println("-----------------------------------------------------");
	
	}  // fin método main
	

	// Consulta equivalente SQL: SELECT nombre, edad, nombrepais FROM Jugadores j, Paises p WHERE j.id = p.id
	private static void JugadoresPaises() {	
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		Values valores = odb.getValues(new ValuesCriteriaQuery(Jugador.class, Where.isNotNull("pais.nombrepais"))
							.field("nombre").field("edad").field("pais"));	
		while (valores.hasNext()) {
			ObjectValues objectValues = (ObjectValues) valores.next();
			System.out.printf("Nombre: %s, Edad: %d, Pais: %s %n", 
							objectValues.getByAlias("nombre"), objectValues.getByIndex(1), objectValues.getByIndex(2));
		}
		odb.close();
	}  // Fin método JugadoresPaises()
	
	// Consulta equivaleSQL: SELECT nombre, ciudad FROM Jugadores j, Paises p WHERE (j.id = p.id AND nombrepais= 'ESPAÑA' AND edad= 15)
	private static void JugadoresPaisesSpain() {  
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		Values valores = odb.getValues(new ValuesCriteriaQuery(Jugador.class, 
				new And().add(Where.equal("pais.nombrepais","ESPAÑA"))
						 .add(Where.equal("edad",15)))
				.field("nombre")
				.field("ciudad"));

		while (valores.hasNext()) {
			ObjectValues objectValues = (ObjectValues) valores.next();
			System.out.printf("Nombre: %s, Ciudad: %s %n", objectValues.getByAlias("nombre"), objectValues.getByIndex(1));
		}
		odb.close();
	}  // Fin método JugadoresPaisesSpain()
	
	// Consulta equivalente SQL: SELECT nombrepais, MAX(edad), AVG(edad) FROM Jugadores j, Paises p WHERE j.id = p.id GROUP BY nombrepais
	private static void contadorymediaporpais() {
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		System.out.println("Numero de jugadores por país, " + " max de edad y media de edad: ");
		Values groupby = odb.getValues(new ValuesCriteriaQuery(Jugador.class, Where.isNotNull("pais.nombrepais"))
				 	.field("pais.nombrepais").count("nombre")
		            .max("edad").sum("edad").groupBy("pais.nombrepais") );
		
		if (groupby.size() == 0)
			System.out.println( "La consulta no devuelve datos. ");
		else  { 
			while(groupby.hasNext()) {		// Obtiene la media directamente dividiendo la suma de edades entre la cantidad de jugadores 
		    ObjectValues objetos= (ObjectValues) groupby.next();     
		    float media = ((BigDecimal) objetos.getByIndex(3)).floatValue() / ((BigInteger) objetos.getByIndex(1)).floatValue();
		    
		    System.out.printf("Pais: %-8s Num jugadores: %d,  Edad Máxima: %.0f, Suma de Edad: %.0f, Edad media: %.2f %n",
		    		objetos.getByAlias("pais.nombrepais"), objetos.getByIndex(1), objetos.getByIndex(2), objetos.getByIndex(3), media );     
		    }	
		  }
		  odb.close();
		}  //  fin método contadorymediaporpais()

	// Consulta equivalente SQL: SELECT AVG(edad) FROM Jugadores
	private static void visualizarmediadeedad() {
		ODB odb = ODBFactory.open("EQUIPOS.DB");
		Values val;
		ObjectValues ov;
		try {								// Al llamar a la función AVG debe gestionar la excepción ArithmeticException
			val = odb.getValues(new ValuesCriteriaQuery(Jugador.class).avg("edad"));
			ov = val.nextValues();
			System.out.printf("AVG-La media de edad es: %f %n", ov.getByIndex(0));
			
		} catch (ArithmeticException e) {	// Si la media (AVG) debe redondearse salta ArithmeticException 
			System.out.println("Excepción => " + e.getMessage());
			Values val2 = odb.getValues(new ValuesCriteriaQuery(Jugador.class).sum("edad").count("edad"));
			ObjectValues ov2 = val2.nextValues();
			BigDecimal sumaedad = (BigDecimal) ov2.getByIndex(0);
			BigInteger cuenta = (BigInteger) ov2.getByIndex(1);
			float media = sumaedad.floatValue() / cuenta.floatValue();
			System.out.printf("La media de edad es: %.4f Contador = %d  " + "suma = %.2f %n", media, cuenta, sumaedad);
		}
		odb.close();
	}  // fin método visualizarmediadeedad()
	
}  // fin clase
