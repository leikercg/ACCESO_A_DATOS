package Clases;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;


public class Actividad_4_13 {

	public static void main(String[] args) {
		
		Actividad_4_13("Italia");
	}	

	private static void Actividad_4_13(String pais) {
		
		ODB odb = ODBFactory.open("EQUIPOS.DB");// Abrir BD		
		ICriterion criterio = Where.equal("nombrePais", pais);
		IQuery query = new CriteriaQuery(Pais.class, criterio);
		Objects<Pais> pa = odb.getObjects(query); // Recupera la colecci�n de objetos
		if (pa.size() == 0) {
			System.out.println("No existe el pais " + pais);
		} else {
			Values cuenta = odb.getValues(new ValuesCriteriaQuery(Jugador.class, Where.equal("pais.nombrePais", pais)).count("nombre"));
			if (cuenta.size() == 0) {
				System.out.println("El pais " + pais + " no tiene jugadores");
			}else {
				try {
					Values consulta = odb.getValues(new ValuesCriteriaQuery(Jugador.class, Where.equal("pais.nombrePais", pais))
							.field("ciudad").count("nombre").avg("edad").groupBy("ciudad"));
					
					while (consulta.hasNext()) {
						ObjectValues objetos= (ObjectValues) consulta.nextValues();
						BigInteger numero = (BigInteger) objetos.getByIndex(1);
						BigDecimal mediaEdad = (BigDecimal) objetos.getByIndex(2);
						System.out.printf("Ciudad: %s, nº jugadores: %d, edad media: %.2f %n", 
								objetos.getByAlias("ciudad"), numero, mediaEdad.floatValue());
					}
				} catch (ArithmeticException e) {
					Values consulta1 = odb.getValues(new ValuesCriteriaQuery(Jugador.class, Where.equal("pais.nombrePais", pais))
							.field("ciudad").sum("edad").count("nombre").groupBy("ciudad"));
					
					while (consulta1.hasNext()) {
						ObjectValues objetos1 = (ObjectValues) consulta1.nextValues();
						BigDecimal sumaEdad = (BigDecimal) objetos1.getByIndex(1);
						BigInteger numero = (BigInteger) objetos1.getByIndex(2);
						float media = sumaEdad.floatValue()/numero.floatValue();
						System.out.printf("Ciudad: %s, nº jugadores: %d, edad media: %.2f %n", 
								objetos1.getByAlias("ciudad"), numero, media);
					}		
				}
			} 
		}	
			
		odb.close();
	} // fin método Actividad_4_12A
		
		
	
} // fin de la clase
