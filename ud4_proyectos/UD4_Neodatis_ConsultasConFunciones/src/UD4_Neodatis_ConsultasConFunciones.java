// Consultas con funciones sobre BD NeoDatis
// API Object Values
import java.math.BigDecimal;
import java.math.BigInteger;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Values;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class UD4_Neodatis_ConsultasConFunciones {
	
	public static void main(String[] args) {
		ODB odb = ODBFactory.open("neodatis.test");  // Abro BD
		
		// Indica que quiere recuperar los atributos nombre y ciudad para todos los objetos de la clase Jugador
		Values valores = odb.getValues(new ValuesCriteriaQuery(Jugador.class).field("nombre").field("ciudad"));
		while (valores.hasNext()) {
			ObjectValues valoresObjeto = (ObjectValues) valores.next();
			// Accedo a los valores de los atributos de cada uno de los objetos por su Alias o su Indice
			System.out.printf("%s, Ciudad : %s %n", valoresObjeto.getByAlias("nombre"), valoresObjeto.getByIndex(1));
		}

		// -- OPERACIONES CON FUNCIONES DE GRUPO
		// SUMA – Obtiene la suma de las edades, equivalente a SQL: SELECT SUM(edad) FROM Jugadores
		Values val1 = odb.getValues(new ValuesCriteriaQuery(Jugador.class).sum("edad"));
		ObjectValues ov = val1.nextValues();
		BigDecimal value = (BigDecimal) ov.getByAlias("edad");
		System.out.printf("Suma de edad : %d %n", value.longValue());
		System.out.println("--------------------------------------------");
		System.out.printf("Suma de edad : %.2f %n", ov.getByAlias("edad"));

		// CUENTA – Obtiene el número de jugadores, equivalente a SQL: SELECT COUNT(nombre) FROM Jugadores
		Values val2 = odb.getValues(new ValuesCriteriaQuery(Jugador.class).count("nombre"));
		ObjectValues ov2 = val2.nextValues();
		BigInteger value2 = (BigInteger) ov2.getByAlias("nombre");
		System.out.printf("Numero de jugadores : %d %n", value2.intValue());
		System.out.println("--------------------------------------------");
		
		// MEDIA – Obtiene la edad media de los jugadores, equivalente a SQL: SELECT AVG(edad) FROM Jugadores
		try {
			Values val3 = odb.getValues(new ValuesCriteriaQuery(Jugador.class).avg("edad"));
			ObjectValues ov3 = val3.nextValues();
			BigDecimal value3 = (BigDecimal) ov3.getByAlias("edad");
			System.out.printf("Edad media : %.2f %n", value3.floatValue());
			System.out.println("--------------------------------------------");
		
		} catch (ArithmeticException e) {	// Si la media (AVG) debe redondearse salta ArithmeticException 
			System.out.println("Excepción => " + e.getMessage());
			Values val3 = odb.getValues(new ValuesCriteriaQuery(Jugador.class).sum("edad").count("edad"));
			ObjectValues ov3 = val3.nextValues();
			BigDecimal sumaedad = (BigDecimal) ov3.getByIndex(0);
			BigInteger cuenta = (BigInteger) ov3.getByIndex(1);
			float media  = sumaedad.floatValue() / cuenta.floatValue();
			System.out.printf("La media de edad es: %.2f Contador = %d  " + "suma = %.2f %n", media, cuenta, sumaedad);
		}
		
		// MAXIMO Y MINIMO – Obtiene la edad máxima y la edad mínima,
		// equivalente a SQL: SELECT MAX(edad) edad_max , MIN(edad) edad_min FROM Jugadores
		Values val4 = odb.getValues(new ValuesCriteriaQuery(Jugador.class).max("edad", "edad_max"));
		ObjectValues ov4 = val4.nextValues();
		BigDecimal maxima = (BigDecimal) ov4.getByAlias("edad_max");
		Values val5 = odb.getValues(new ValuesCriteriaQuery(Jugador.class).min("edad", "edad_min"));
		ObjectValues ov5 = val5.nextValues();
		BigDecimal minima = (BigDecimal) ov5.getByAlias("edad_min");
		System.out.printf("Edad máxima: %d, Edad mínima: %d %n", maxima.intValue(), minima.intValue());

		// Uso del GROUP BY - Se cuenta el número de jugadores para cada ciudad
		// equivalente a SQL: SELECT ciudad, COUNT(nombre) FROM Jugadores GROUP BY ciudad
		Values groupby = odb.getValues(new ValuesCriteriaQuery(Jugador.class).field("ciudad").count("nombre").groupBy("ciudad"));
		while (groupby.hasNext()) {
			ObjectValues objetos = (ObjectValues) groupby.next();
			System.out.printf("%s, %d%n", objetos.getByAlias("ciudad"), objetos.getByIndex(1));
		}
		odb.close();
	}  // fin main
}  // fin clase
