package ud3_hibernate_copy;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/*import ud3_hibernate.Departamentos; // Cambiamos a hibernate sin el 1, lo comentamos por que está en el paquete de hibernate
import ud3_hibernate.NewHibernateUtil;*/

public class CargaObjetos {
	public static void main(String[] args) {
		SessionFactory sesion = NewHibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		// Visualizo los datos del departamento 20
		Departamentos dep = new Departamentos();
		try {
			//dep = (Departamentos) session.load(Departamentos.class, (byte) 28); //no existe este departamento
                        dep = (Departamentos) session.load(Departamentos.class, (byte) 10);
                        //dep = (Departamentos) session.load("ud3_hibernate.Departamentos", (byte) 10);

			
			System.out.printf("Nombre Dep: %s%n", dep.getDnombre());
			System.out.printf("Localidad: %s%n", dep.getLoc());
		} catch (ObjectNotFoundException o) {
			System.out.println("NO EXISTE EL DEPARTAMENTO!!");
		}

		session.close();
		System.exit(0);

	}

}
