// Acceso a MongoDB desde Java	

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Accumulators.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document; // mongo-java-driver-322
import org.bson.conversions.Bson;


public class UD5_JSON_accesoAMongoDB {

	public static void main(String[] args) {

		Logger.getLogger("org.mongodb.driver").setLevel(Level.OFF);
		
		// Nos conectamos a la BD
		MongoClient cliente = new MongoClient("localhost",27017);
		MongoDatabase db = cliente.getDatabase("miBaseDatos");
		MongoCollection<Document> coleccion = db.getCollection("amigos");

		
		// Visualizar los datos en formato cadena
	/*	System.out.println("\n--- Visualiza los datos en formato cadena ---");
		List<Document> consulta = coleccion.find().into(new ArrayList<Document>()); //recoge una coleccion
		for (int i = 0; i < consulta.size(); i++) {
			System.out.println(" - " + consulta.get(i).toString());
			
			Document doc = consulta.get(i); // bajamos un escalán más, recupera un documento
			System.out.println("precio: "+ doc.get("pvp").toString()); 
			System.out.println("precio: "+ doc.getString("nombre")); 
			System.out.println("precio: "+ doc.get("editorial"));
			System.out.println("precio: "+ doc.get("temas").toString()); 


		}
		
		// Visualizar los datos en formato JSON
				System.out.println("\n--- Visualiza los datos en formato JSON ---------------------------------");
				MongoCursor<Document> consulta1 = coleccion.find().iterator();
				
				try {
					while(consulta1.hasNext()) {
						System.out.println("consulta: "+consulta1.next().toJson());
					}
				}catch (Exception e) {
					// TODO: handle exception
				}
		*/		

		/*
		// Visualizar los datos elemento a elemento
		System.out.println("\n--- Visualiza los datos elemento a elemento ---");
		for (int i = 0; i < consulta.size(); i++) {
			Document amig = consulta.get(i);
			System.out.println(" - " + amig.getString("nombre") + " - " + amig.get("tel�fono") + " - "
					+ amig.get("curso") + " - " + amig.get("nota"));
		
		}

*/
		
		/*
		// insertar un documento en amigos
		Document amigo = new Document();
		amigo.put("nombre", "Pepito");
		amigo.put("teléfono", 555);
		amigo.put("curso", "2DAM2");
		amigo.put("fecha", new Date());
		coleccion.insertOne(amigo);

		// insertar otro documento en amigos
		Document amigo2 = new Document("nombre", "Pedro").append("teléfono", 1234).append("curso",
					new Document("curso1", "1DAM").append("curso2", "2DAM"));
		coleccion.insertOne(amigo2);
*/
		
		// B�squedas de documentos
		System.out.println(" - ---Busqueda de un documento ----------------------");
		
		Bson filtro = Filters.eq("nombre", "Pedro");
		
		//Document doc = coleccion.find(Filters.eq("nombre", "Pedro")).first();
		Document doc = coleccion.find(filtro).first(); // Lo mismo que arriba
		
		try {
			System.out.println("Localizado: " + doc.toJson());
		} catch (NullPointerException e) {
			System.out.println("No se encuentra.");
		}

		
		// Salida ordenada utilizando cursor
		System.out.println(" - ----Ordenados desc por nombre ---------------");
		MongoCursor<Document> docs = coleccion.find(eq("curso", "2DAM")).sort(descending("nombre")).iterator();
		while (docs.hasNext()) {
			Document docu = docs.next();
			System.out.println(docu.toJson());
		}
		docs.close();

		// Salida ordenada pero utilizando una lista
		System.out.println(" - ----Búsqueda por OR  ---------------");
		List<Document> consulta3 = coleccion.find(or(eq("curso", "2DAM"), eq("nota", 6)))
				.into(new ArrayList<Document>());
		for (int i = 0; i < consulta3.size(); i++) {
			System.out.println(" - " + consulta3.get(i).toString());
		}
/*
		// Obtener los Bson de un documento
		System.out.println(" - --- Objetos Bson -----------------");
		MongoCursor<Document> cursor2 = coleccion.find().iterator();
		while (cursor2.hasNext()) {
			Document doc2 = cursor2.next();
			Bson id = eq("_id", doc2.get("_id"));
			Bson nombre = eq("nombre", doc2.get("nombre"));
			Bson curso = eq("curso", doc2.get("curso"));
			System.out.println("Id: " + id + ". Nombre: " + nombre.toString() + ". Curso : " + curso.toString());
		}

		
		// Consulta con funciones de agregado. Obtener Nota media
		System.out.println(" - ---Nota media por curso -----------------");
		MongoCursor<Document> docs6 = coleccion
				.aggregate(Arrays.asList(group("$curso", sum("sumanota", "$nota"), avg("medianota", "$nota"))))
				.iterator();
		while (docs6.hasNext()) {
			Document docu = docs6.next();
			System.out.println(docu.toJson());
		}
		docs6.close();
*/
	
		cliente.close();  // Cierra el recurso de la base de datos
		
	}  // fin main

}  // fin clase


