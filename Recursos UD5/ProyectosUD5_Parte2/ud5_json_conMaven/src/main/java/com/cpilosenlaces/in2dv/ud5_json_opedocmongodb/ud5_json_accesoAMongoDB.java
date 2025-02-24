package com.cpilosenlaces.in2dv.ud5_json_opedocmongodb;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Accumulators.*;


import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.bson.Document; // mongo-java-driver-322
import org.bson.conversions.Bson;


public class ud5_json_accesoAMongoDB {

	public static void main(String[] args) {

		// Logger.getLogger("org.mongodb.driver").setLevel(Level.OFF);   // Elimina los mensajes de log de Hibernate
	
		// Nos conectamos a la BD
		MongoClient cliente = new MongoClient("localhost",27017);
		MongoDatabase db = cliente.getDatabase("miBaseDatos");
		MongoCollection<Document> coleccion = db.getCollection("amigos");

		// Visualizar los datos en formato cadena
		System.out.println("\n--- Visualiza los datos en formato cadena ---");
		List<Document> consulta = coleccion.find().into(new ArrayList<Document>());
		for (int i = 0; i < consulta.size(); i++) {
			System.out.println(" - " + consulta.get(i).toString());
		}

		
		// Visualizar los datos elemento a elemento
		System.out.println("\n--- Visualiza los datos elemento a elemento ---");
		for (int i = 0; i < consulta.size(); i++) {
			Document amig = consulta.get(i);
			System.out.println(" - " + amig.getString("nombre") + " - " + amig.get("teléfono") + " - "
					+ amig.get("curso") + " - " + amig.get("nota"));
		
		}

		// insertar un documento en amigos
		Document amigo = new Document();
		amigo.put("nombre", "Pepito2");
		amigo.put("teléfono", 555);
		amigo.put("curso", "2DAM2");
		amigo.put("fecha", new Date());
		// coleccion.insertOne(amigo);

		// insertar otro documento en amigos
		Document amigo2 = new Document("nombre", "Pedro").append("teléfono", 1234).append("curso",
					new Document("curso1", "1DAM").append("curso2", "2DAM"));
		
		// coleccion.insertOne(amigo2);

		// Bson filtro = Filters.eq("_id", amigo.get("_id"));
		// amigo.append("nuevo", 333);
		// coleccion.uuainsertOne(amigo);

		// insertar varios documentos de una lista
		List<Document> listadocs = new ArrayList<Document>();
		for (int i = 0; i < 100; i++) {
			listadocs.add(new Document("Valor de i", i));
		}
		// coleccion.insertMany(listadocs);

		// Visualizar utilizando un cursor
		System.out.println(" - ---Utilizo cursor----------------------");
		MongoCursor<Document> cursor = coleccion.find().iterator();
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			System.out.println(doc.toJson());
		}
		cursor.close();

		// Búsquedas de documentos
		System.out.println(" - ---Busqueda de un documento ----------------------");
		Document doc = coleccion.find(Filters.eq("nombre", "Ana")).first();
		try {
			System.out.println("Localizado: " + doc.toJson());
		} catch (NullPointerException e) {
			System.out.println("No se encuentra.");
		}

		// Salida ordenada utilizo cursor
		System.out.println(" - ----Ordenados desc por nombre ---------------");
		MongoCursor<Document> docs = coleccion.find(eq("curso", "2DAM")).sort(descending("nombre")).iterator();
		while (docs.hasNext()) {
			Document docu = docs.next();
			System.out.println(docu.toJson());
		}
		docs.close();

		// Salida ordenada pero utilizo una lista
		List<Document> consulta3 = coleccion.find(and(eq("curso", "2DAM"), eq("nota", 8)))
				.into(new ArrayList<Document>());
		for (int i = 0; i < consulta3.size(); i++) {
			System.out.println(" - " + consulta3.get(i).toString());
		}

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

		// Utilizando proyección
		System.out.println(" - ---Nombre y nota de 1DAM -----------------");
		MongoCursor<Document> docs3 = coleccion.find(eq("curso", "1DAM")).sort(ascending("nombre"))
				.projection(include("nombre", "nota")).iterator();
		while (docs3.hasNext()) {
			Document docu = docs3.next();
			System.out.println(docu.toJson());
		}
		docs3.close();

		Document dd = coleccion.find(eq("nombre", "Ana")).projection(exclude("_id", "nota", "nombre")).first();
		System.out.println(" Doc de Ana: " + dd.toJson());

		// Utilizando agregados

		System.out.println(" - ---Utilizando Agregados -----------------");
		MongoCursor<Document> docs4 = coleccion.aggregate(Arrays.asList(match(eq("curso", "1DAM")))).iterator();
		while (docs4.hasNext()) {
			Document docu = docs4.next();
			System.out.println(docu.toJson());
		}
		docs4.close();

		MongoCursor<Document> docs5 = coleccion.aggregate(
				Arrays.asList(match(eq("curso", "1DAM")), project(fields(include("nombre", "nota"), excludeId()))))
				.iterator();
		while (docs5.hasNext()) {
			Document docu = docs5.next();
			System.out.println(docu.toJson());
		}
		docs5.close();

		// Nota media
		System.out.println(" - ---Nota media por curso -----------------");
		MongoCursor<Document> docs6 = coleccion
				.aggregate(Arrays.asList(group("$curso", sum("sumanota", "$nota"), avg("medianota", "$nota"))))
				.iterator();
		while (docs6.hasNext()) {
			Document docu = docs6.next();
			System.out.println(docu.toJson());
		}
		docs6.close();

		// Enviamos la salida a un nuevo doc
		System.out.println(" - ---Utilizamos out -----------------");
		MongoCursor<Document> docs7 = coleccion.aggregate(
				Arrays.asList(group("$curso", sum("sumanota", "$nota"), avg("medianota", "$nota")), out("mediascurso")))
				.iterator();
		while (docs7.hasNext()) {
			Document docu = docs7.next();
			System.out.println(docu.toJson());
		}
		docs7.close();
		
		cliente.close();  // Cierra el recurso de la base de datos

	
	}

	
}
