package ud3_hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;



public class EjemploUniqueResult {

	public static void main(String[] args) {
		SessionFactory sesion = NewHibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		// Visualiza los datos del departamento 10
		String hql = "from Departamentos as dep where dep.deptNo = 10";
		Query q = session.createQuery(hql);
		Departamentos dep = (Departamentos) q.uniqueResult();
		System.out.printf("%d, %s, %s%n", dep.getDeptNo(), dep.getLoc(), dep.getDnombre());

		// Visualiza los datos del departamento de nombre VENTAS
		hql = "from Departamentos as dep where dep.dnombre = 'VENTAS' ";
		q = session.createQuery(hql);
		dep = (Departamentos) q.uniqueResult();
		System.out.printf("%d, %s, %s%n", dep.getDeptNo(), dep.getLoc(), dep.getDnombre());
	
                session.close();
		System.exit(0);

	}

}
