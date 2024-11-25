package ud3_hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ListaEmpleados20 {

	public static void main(String[] args) {
		SessionFactory sesion = NewHibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		Query q = session.createQuery("from Empleados as e where e.departamentos.deptNo = 20");
		List<Empleados> lista = q.list();
		// Obtenemos un Iterador y recorremos la lista
		Iterator<Empleados> iter = lista.iterator();
		while (iter.hasNext()) {
			Empleados emp = (Empleados) iter.next();// extraer el objeto
			System.out.printf("%s, %.2f, nombre del departamento: %s, %n", emp.getApellido(), emp.getSalario(),emp.getDepartamentos().getDnombre());
		}

		session.close();
		System.exit(0);
	}

}
