
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ud3_hibernate1.Departamentos;
import ud3_hibernate1.Empleados;
import ud3_hibernate1.HibernateUtil;

public class ConsultasParametros {

	public static void main(String[] args) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Empleados where empNo = :numemple";
		Query q1 = session.createQuery(hql);
		q1.setParameter("numemple", (short) 7369);
		Empleados emple = (Empleados) q1.uniqueResult();
		System.out.printf("%s, %s %n", emple.getApellido(), emple.getOficio());

		Query q2 = session.createQuery("from Empleados emp where emp.departamentos.deptNo = :ndep and emp.oficio = :ofi");
		q2.setParameter("ndep", (byte) 10);
		q2.setParameter("ofi", "DIRECTOR");
		List<Empleados> lista = q2.list();
		Iterator<Empleados> iter = lista.iterator();
		while (iter.hasNext()) {
			// extraer el objeto
			Empleados emp = (Empleados) iter.next();
			System.out.printf("%d, %s%n", emp.getEmpNo(), emp.getApellido());
		}

		String hql3 = "from Empleados emp where emp.departamentos.deptNo = ? and emp.oficio = ?";
		Query q3 = session.createQuery(hql3);
		q3.setParameter(0, (byte) 10);
		q3.setParameter(1, "DIRECTOR");
		List<Empleados> lista2 = q3.list();
		Iterator<Empleados> iter2 = lista2.iterator();
		while (iter2.hasNext()) {
			// extraer el objeto
			Empleados emp = (Empleados) iter2.next();
			System.out.printf("%d, %s%n", emp.getEmpNo(), emp.getApellido());
		}

		String hql4 = "from Empleados emp where emp.departamentos.deptNo = :ndep and emp.oficio = :ofi";
		Query q4 = session.createQuery(hql4);
		q4.setInteger("ndep", (byte) 10);
		q4.setString("ofi", "DIRECTOR");
		List<Empleados> lista3 = q4.list();
		Iterator<Empleados> iter3 = lista3.iterator();
		while (iter3.hasNext()) {
			// extraer el objeto
			Empleados emp = (Empleados) iter3.next();
			System.out.printf("%d, %s%n", emp.getEmpNo(), emp.getApellido());
		}

		// from Empleados emp where emp.departamentos.deptNo in (10,20)
		System.out.println("Empleados con departamento 10, 20 ");
		List<Byte> numeros = new ArrayList<Byte>();
		numeros.add((byte) 10);
		numeros.add((byte) 20);
		String hql5 = "from Empleados emp where emp.departamentos.deptNo in (:listadep) "
				+ "order by emp.departamentos.deptNo ";
		Query q5 = session.createQuery(hql5);
		q5.setParameterList("listadep", numeros);

		List<Empleados> lista4 = q5.list();
		Iterator<Empleados> iter4 = lista4.iterator();
		while (iter4.hasNext()) {
			// extraer el objeto
			Empleados emp = (Empleados) iter4.next();
			System.out.printf("%d, %d, %s%n", emp.getDepartamentos().getDeptNo(), emp.getEmpNo(), emp.getApellido());
		}
                
		// Parámetro de tipo Date
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		String strFecha = "1991-12-03";
		Date fecha = null;
		try {
			fecha = formatoDelTexto.parse(strFecha);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		String hql6 = "from Empleados where fechaAlt = :fechalta ";

		Query q6 = session.createQuery(hql6);
		q6.setDate("fechalta", fecha);
		System.out.println("Empleados cuya fecha de alta es 1991-12-03");
		List<Empleados> lista5 = q6.list();
		Iterator<Empleados> iter5 = lista5.iterator();
		while (iter5.hasNext()) {
			// extraer el objeto
			Empleados emp = (Empleados) iter5.next();
			System.out.printf("%d, %d, %s%n", emp.getDepartamentos().getDeptNo(), emp.getEmpNo(), emp.getApellido());
		}

		session.close();
		System.exit(0);

	}

}
