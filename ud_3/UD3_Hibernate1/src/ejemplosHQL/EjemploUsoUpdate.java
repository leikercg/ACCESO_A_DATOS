import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import ud3_hibernate1.HibernateUtil;

public class EjemploUsoUpdate {

	public static void main(String[] args) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		
		// Modificamos el salario de GIL
		String hqlModif = "update Empleados set salario = :nuevoSal where apellido = :ape";
		Query q1 = session.createQuery(hqlModif);
		q1.setParameter("nuevoSal", (float) 2500.34);
		q1.setString("ape", "GIjL");
		int filasModif = q1.executeUpdate();		
		System.out.printf("FILAS MODIFICADAS: %d%n", filasModif); 
				
		// Eliminamos los empleados del departamento 20
		String hqlDel = "delete Empleados e where e.departamentos.deptNo = :dep";
		Query q = session.createQuery(hqlDel);
		q.setInteger("dep", 20);
		int filasDel = q.executeUpdate();		
		System.out.printf("FILAS ELIMINADAS: %d%n", filasDel); 

		//tx.rollback(); // Deshace la transacci�n  		
		tx.commit(); // Valida la transacci�n

                session.close();
		System.exit(0);
	}
}
