import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ud3_hibernate1.HibernateUtil;
import ud3_hibernate1.Empleados;

public class ListaEmpleados20 {

	public static void main(String[] args) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		Query q = session.createQuery("from Empleados as e where e.departamentos.deptNo = 20");
		List<Empleados> lista = q.list();
		// Obtenemos un Iterador y recorremos la lista
		Iterator<Empleados> iter = lista.iterator();
		while (iter.hasNext()) {
			Empleados emp = (Empleados) iter.next();// extraer el objeto
			System.out.printf("%s, %.2f %n", emp.getApellido(), emp.getSalario());
		}

		session.close();
		System.exit(0);
	}

}
