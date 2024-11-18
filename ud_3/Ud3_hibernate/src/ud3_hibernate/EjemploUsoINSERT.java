package ud3_hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class EjemploUsoINSERT {
	public static void main(String[] args) {
		SessionFactory sesion = NewHibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();

		// Insertamos los departamentos de la tabla NUEVOS, la tabla tiene
		// que estar mapeada en nuestro proyecto
		String hqlInsert = "insert into Departamentos (deptNo, dnombre, loc) " +
			             " select n.deptNo, n.dnombre, n.loc from Nuevos n";
		Query cons=session.createQuery( hqlInsert );
		int filascreadas = cons.executeUpdate();	
                tx.commit(); // valida la transacción
                System.out.printf("FILAS INSERTADAS: %d%n",filascreadas);
                
		session.close();
		System.exit(0);
	}
}
