package ud3_hibernate;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;


public class BorradoDep {

	public static void main(String[] args) {
		SessionFactory sesion = NewHibernateUtil.getSessionFactory();
		Session session = sesion.openSession(); //Primero se crea la sesesion, si es solo de consulta con esto vale
		Transaction tx = session.beginTransaction(); //Se crea una transacción

		Departamentos de = (Departamentos) session.load(Departamentos.class, (byte) 10);

		try {
			session.delete(de); // elimina el objeto
			tx.commit();
			System.out.println("Departamento eliminado");
                        
		} catch (ObjectNotFoundException o) {
			System.out.println("NO EXISTE EL DEPARTAMENTO..."); //Esto maneja el load
		} catch (ConstraintViolationException c) {
			System.out.println("NO SE PUEDE ELIMINAR, TIENE EMPLEADOS..."); // Esto maneja los fallos sql
		}catch (Exception e) {
			System.out.println("ERROR NO CONTROLADO....");
			e.printStackTrace();
		}
		session.close();
		System.exit(0);
	}

}
