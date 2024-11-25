package ud3_hibernate;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ClasesNoasociadas {

	public static void main(String[] args) {
		SessionFactory sesion = NewHibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		{
			String hql = "from Empleados e, Departamentos d where e.departamentos.deptNo = d.deptNo order by apellido";
			Query cons = session.createQuery(hql);
			Iterator q = cons.iterate();
			while (q.hasNext()) {
				Object[] par = (Object[]) q.next();
				Empleados em = (Empleados) par[0];
				Departamentos de = (Departamentos) par[1];
				System.out.printf("%s, %.2f, %s, %s %n", em.getApellido(), em.getSalario(), de.getDnombre(),
						de.getLoc());
			}
			System.out.println("===============================================");
		}
		{
                        // MOSTRAR SALARIO MEDIO DE LOS EMPLEADOS
                        String hql = "select avg(em.salario) from Empleados as em";
			Query cons = session.createQuery(hql);
			Double suma = (Double) cons.uniqueResult();
			System.out.printf("Salario medio: %.2f%n", suma);
		}
		// Mostrar el salario medio y el numero de empleados
		System.out.println("===============================================");
		String hql = "select avg(salario), count(empNo) from Empleados ";
		Query cons = session.createQuery(hql);
		Object[] resultado = (Object[]) cons.uniqueResult();
		System.out.printf("Salario medio: %.2f%n", resultado[0]);
		System.out.printf("Número de empleados: %d%n", resultado[1]);
                
		// Mostrar el salario medio y el numero de empleados por departamento
		hql = "select e.departamentos.deptNo, avg(salario), " + " count(empNo) from Empleados e "
				+ " group by e.departamentos.deptNo ";

		cons = session.createQuery(hql);
		Iterator iter = cons.iterate();
		while (iter.hasNext()) {
			Object[] par = (Object[]) iter.next();
			Byte depar = (Byte) par[0];
			Double media = (Double) par[1];
			Long cuenta = (Long) par[2];
			System.out.printf("Dep: %d, Media: %.2f, Nº emp: %d %n", depar, media, cuenta);
		}
		System.out.println("===============================================");

		
		session.close();
		System.exit(0);

	}

}
