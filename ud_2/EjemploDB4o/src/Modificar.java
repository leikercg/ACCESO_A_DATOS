import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Modificar {
	static String BDPer = "DBPersonas.yap";
	
	public static void main(String[] args) {
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDPer);

		ObjectSet<Persona> result =
		           db.queryByExample(new Persona("Juan",null));
		if(result.size() == 0) 
		        System.out.println("No existe Juan…");
		else {	 
		        Persona existe = (Persona) result.next();    
			  existe.setCiudad("Toledo");	   
			  db.store(existe); //ciudad modificada
			  //consultar los datos
			  result = db.queryByExample(new Persona("Juan",null));
			  existe = (Persona) result.next();
			  System.out.printf("Nombre:%s, Nueva Ciudad: %s %n",
		                          existe.getNombre(), existe.getCiudad());
		}    

		db.close();
	}

}
