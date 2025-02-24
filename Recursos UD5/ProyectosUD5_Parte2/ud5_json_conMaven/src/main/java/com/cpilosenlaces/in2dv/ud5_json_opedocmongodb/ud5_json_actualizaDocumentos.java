package com.cpilosenlaces.in2dv.ud5_json_opedocmongodb;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.exists;
import static com.mongodb.client.model.Updates.inc;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.unset;

import java.util.Iterator;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class ud5_json_actualizaDocumentos {
	
	public static void main(String[] args) {
	
		//Logger.getLogger("org.mongodb.driver").severe("");
		//Logger.getLogger("org.hibernate").setLevel(Level.OFF);  // Elimina los mensajes de log de Hibernate
			
		
		// Nos conectamos a la BD
		// MongoClient cliente = new MongoClient("localhost",27017);
		MongoClient cliente = new MongoClient();
		MongoDatabase db = cliente.getDatabase("miBaseDatos");
		MongoCollection<Document> coleccion = db.getCollection("amigos");
			
		
		// Actualizar documentos
				// Subir nota a uno
				coleccion.updateOne(eq("nombre", "Ana"), set("nota", 5));

				// Subir la nota a varios
				UpdateResult updateResult = coleccion.updateMany(eq("curso", "1DAM"), inc("nota", 3));
				System.out.println("Se han modificado: " + updateResult.getModifiedCount());
				System.out.println("Se han seleccionado: " + updateResult.getMatchedCount());

				// Subir la nota a todos
				UpdateResult updateResult2 = coleccion.updateMany(exists("_id"), inc("nota", 2));
				System.out.println("Se han modificado: " + updateResult2.getModifiedCount());

				coleccion.updateOne(eq("nombre", "Marleni"), set("población", "Toledo"));

				coleccion.updateMany(eq("curso", "1DAM"), unset("población"));

				// Borro un documento
				DeleteResult del = coleccion.deleteOne(eq("nombre", "Ana"));
				System.out.println("Se han borrado: " + del.getDeletedCount());
				// Borro todos
				del = coleccion.deleteMany(exists("_id"));
				System.out.println("Se han borrado: " + del.getDeletedCount());

				// Crear Colección:
				MongoClient cliente2 = new MongoClient();
				MongoDatabase db2 = cliente2.getDatabase("miBaseDatos");
				db2.createCollection("nuevacoleccion2");
				MongoCollection<Document> cnueva = db2.getCollection("nuevacoleccion2");
				// La borro
				cnueva.drop();

				// Listar las colecciones de la BD
				System.out.println("Listado de colecciones: ");
				MongoIterable<String> colecciones = db.listCollectionNames();
				Iterator col = colecciones.iterator();
				while (col.hasNext())
					System.out.println(col.next());

				// También se pueden listar así:
				for (String name : db.listCollectionNames()) {
					System.out.println(name);
				}

				//Crear base de datos
				MongoClient clienn= new MongoClient();
				MongoDatabase dbn = clienn.getDatabase("nueva");
				MongoCollection<Document> clnue = dbn.getCollection("colecnueva");
				Document doc1 = new Document("nombre", "Pedro").append("teléfono", 1234).append("curso","2DAM");
				clnue.insertOne(doc1);

				for (String name: clienn.listDatabaseNames()) {
				    System.out.println(name);
				}
				
				/*
				 * // Cambiamos los datos de amigo Document amigo2 = new Document();
				 * amigo2 = amigo; amigo2.replace("teléfono", 999); System.out.println(
				 * " -Amigo 2:  " + amigo2.toString()); System.out.println(
				 * " - ----------------------------------------");
				 * coleccion.replaceOne(Filters.eq("_id", amigo.get("_id")), amigo2);
				 * //coleccion.r coleccion.replaceOne(Filters.eq("_id",
				 * amigo.get("_id")), amigo2); consulta = coleccion.find().into(new
				 * ArrayList<Document>()); for (int i = 0; i < consulta.size(); i++) {
				 * System.out.println(" - " + consulta.get(i).toString()); }
				 * System.out.println(" - ----------------------------------------");
				 * amigo2.replace("teléfono", 7777); Bson filtro = Filters.eq("_id",
				 * amigo.get("_id")); coleccion.replaceOne(filtro, amigo2); consulta =
				 * coleccion.find().into(new ArrayList<Document>()); for (int i = 0; i <
				 * consulta.size(); i++) { System.out.println(" - " +
				 * consulta.get(i).toString()); }
				 * 
				 */
				
		

	}  // fin main

}  // fin clase