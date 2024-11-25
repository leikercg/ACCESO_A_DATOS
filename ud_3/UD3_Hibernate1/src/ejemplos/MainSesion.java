import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class MainSesion {

	public static void main(String[] args) {		
		// Inicializa el entorno Hibernate 
		Configuration cfg = new Configuration().configure();
		// Crea el ejemplar de sesion factory
		SessionFactory sessionFactory = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());
		// Obtiene un objeto session
		Session session = sessionFactory.openSession();
				
/* otra forma
Configuration configuration = new Configuration().configure();
StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

Session session = sessionFactory.openSession();
*/
		session.close();
		System.exit(0);		
	}
}
