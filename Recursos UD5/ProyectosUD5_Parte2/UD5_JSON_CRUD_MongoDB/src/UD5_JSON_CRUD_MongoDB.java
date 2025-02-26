// Operaciones CRUD sobre MongoDB

import com.mongodb.BasicDBList;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document; // mongo-java-driver-322
import org.bson.conversions.Bson;


public class UD5_JSON_CRUD_MongoDB {

	public static void main(String[] args) {
		
		Logger.getLogger("org.mongodb.driver").setLevel(Level.OFF); // Para no mostrar tanta información


		// Nos conectamos a la BD
		MongoClient cliente = new MongoClient("localhost",27017);
		MongoDatabase db = cliente.getDatabase("RepasoBD");
		MongoCollection<Document> coleccion = db.getCollection("personas");


/*
		// Inserto un documento que incluye otro documento y un array
		BasicDBList listaPrimas = new BasicDBList();
		listaPrimas.add(25);
		listaPrimas.add(50);
		listaPrimas.add(75);
		
		Document doc1 = new Document("nombre", "Pedro Jiménez")
				.append("direccion", new Document("población", "Albacete").append("calle", "La Feria 23"))
				.append("salario", 1500)
				.append("primas", listaPrimas)
				.append("edad", 40);
		
		coleccion.insertOne(doc1);
		
		
		  */
		// Actualizo un valor del documento estableciendo el filtro sobre una clave de un documento dentro del documento
		Bson filtro1 = Filters.eq("direccion.población", "Albacete");
		Bson valor1 = Updates.set("edad", 50);
        UpdateResult result1 = coleccion.updateMany(filtro1, valor1);
        System.out.println("Número de documentos actualizados: " + result1.getModifiedCount());       
        
        
      
        // Añado un valor en un Array del documento estableciendo el filtro sobre una clave del documento y una clave de un documento dentro del documento
     	Bson filtro2 =  Filters.and(Filters.eq("direccion.población", "Toledo"), Filters.gt("salario", 1500));
     	Bson datoArray = Updates.addToSet("primas", 90); // Añadir un elemento a un array.
        UpdateResult result2 = coleccion.updateMany(filtro2, datoArray);
        System.out.println("Número de documentos actualizados: " + result2.getModifiedCount());


        
        // Elimino un valor en un Array del documento estableciendo el filtro sobre una clave del documento
     	Bson filtro3 =  Filters.eq("nombre", "Santiago Gómez");
     	Bson datoElimina = Updates.pull("primas", 90); // elimina el valor concreto, no el primero ni el ultimo, pop y push
        UpdateResult result3 = coleccion.updateMany(filtro3, datoElimina);
        System.out.println("Número de documentos actualizados: " + result3.getModifiedCount());
		
        
		// Elimino un documento, es decir una registro
     	Bson filtro4 =  Filters.and(Filters.eq("direccion.población", "Toledo"), Filters.gte("edad", 55));
		DeleteResult result = coleccion.deleteMany(filtro4); // many para borrar varios, delete para borrar el primero que se encuentre
        System.out.println("Número de documentos eliminados: " +result.getDeletedCount());
        
        
       

		cliente.close();  // Cierra el recurso de la base de datos
		
	}  // fin main

}  // fin clase


