package ud3_hibernate;

import java.util.Iterator;
import java.util.List;

import java.util.logging.Level; // Para usar el logger
import java.util.logging.Logger; // Para usar el logger

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;




public class ListaDepartamentosIterator {
	public static void main(String[] args) {
               Logger.getLogger("org.hibernate").setLevel(Level.OFF); // Eliminamos los logs de la 
		SessionFactory sesion = NewHibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
                
                
                
		Query q = session.createQuery("from Departamentos"); // Creamos la query
		q.setFetchSize(10);// List se trae todos, pero el iterator se trae de 10 en 10 
		Iterator iter = q.iterate(); // Y los mete en un iterador.
		while (iter.hasNext()) //Se recorre con un iterador
		{
		   //extraer el objeto
		   Departamentos   depar = (Departamentos) iter.next(); 
		   System.out.printf("%d, %s%n", depar.getDeptNo(), depar.getDnombre());		   
		}
		session.close();
		System.exit(0);
	}
}
