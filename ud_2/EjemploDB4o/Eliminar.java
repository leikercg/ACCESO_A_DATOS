import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Eliminar {

	final static String BDPer = "DBPersonas.yap";

	public static void main(String[] args) {

		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDPer);

		ObjectSet<Persona> result = db.queryByExample(new Persona("Juan", null));

		if (result.size() == 0)
			System.out.println("No existe Juan…");
		else {
			System.out.printf("Registros a borrar: %d %n", result.size());

			while (result.hasNext()) {
				Persona p = result.next();
				db.delete(p);
				System.out.println("Borrado....");
			}
		}

		db.close();
	}

}
