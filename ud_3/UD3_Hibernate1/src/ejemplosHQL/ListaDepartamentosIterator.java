import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ud3_hibernate1.Departamentos;
import ud3_hibernate1.HibernateUtil;

public class ListaDepartamentosIterator {
	public static void main(String[] args) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
                
		Query q = session.createQuery("from Departamentos");
		q.setFetchSize(10);
		Iterator iter = q.iterate();
		while (iter.hasNext())
		{
		   //extraer el objeto
		   Departamentos   depar = (Departamentos) iter.next(); 
		   System.out.printf("%d, %s%n", depar.getDeptNo(), depar.getDnombre());		   
		}
		session.close();
		System.exit(0);
	}
}
