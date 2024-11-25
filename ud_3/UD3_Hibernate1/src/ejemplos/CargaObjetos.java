import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import ud3_hibernate1.Departamentos;
import ud3_hibernate1.HibernateUtil;

public class CargaObjetos {
	public static void main(String[] args) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		// Visualizo los datos del departamento 20
		Departamentos dep = new Departamentos();
		try {
			//dep = (Departamentos) session.load(Departamentos.class, (byte) 28);
			dep = (Departamentos) session.load("ud3_hibernate1.Departamentos", (byte) 20);
			
			System.out.printf("Nombre Dep: %s%n", dep.getDnombre());
			System.out.printf("Localidad: %s%n", dep.getLoc());
		} catch (ObjectNotFoundException o) {
			System.out.println("NO EXISTE EL DEPARTAMENTO!!");
		}

		session.close();
		System.exit(0);

	}

}
