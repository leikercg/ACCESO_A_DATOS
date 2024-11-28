package ud3_hibernate;
import java.util.Iterator;
import java.util.Set;
import org.hibernate.ObjectNotFoundException;



import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ListadoDep {
	public static void main(String[] args) {
		SessionFactory sesion = NewHibernateUtil.getSessionFactory();
		Session session = sesion.openSession(); // Hay que crear una sesisón para realizar consultas.

		System.out.println("==============================");
		System.out.println("DATOS DEL DEPARTAMENTO 10.");
                Departamentos dep = new Departamentos();
	    try{                    
                dep = (Departamentos) session.load(Departamentos.class, (byte) 10);              
		System.out.println("Nombre Dep:" + dep.getDnombre());
		System.out.println("Localidad:" + dep.getLoc());

		System.out.println("==============================");
		System.out.println("EMPLEADOS DEL DEPARTAMENTO 10.");

		Set<Empleados> listaemple = dep.getEmpleadoses();// obtenemos empleados

		Iterator<Empleados> it = listaemple.iterator();

                
		System.out.printf("N�mero de empleados: %d %n", listaemple.size());
		while (it.hasNext()) {
			// Empleados emple = new Empleados();
			Empleados emple = it.next();
			System.out.printf("%s * %.2f %n", emple.getApellido(), emple.getSalario());
		}
		System.out.println("==============================");
		
            } catch (ObjectNotFoundException o) {
			System.out.println("NO EXISTE EL DEPARTAMENTO...");
            }               
                session.close();
		System.exit(0);
	}
}
