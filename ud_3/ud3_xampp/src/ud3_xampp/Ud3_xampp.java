/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ud3_xampp;

import java.util.Iterator;
import java.util.Set;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Alumno
 */
public class Ud3_xampp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    SessionFactory sesion = NewHibernateUtil.getSessionFactory();
		Session session = sesion.openSession(); // Hay que crear una sesisón para realizar consultas.
                Transaction tx = session.beginTransaction(); // Hay que crear una transaccion para modificar elementos.
                
                
                
		System.out.println("==============================");
		System.out.println("DATOS DEL DEPARTAMENTO 10.");
                Monumentos mon = new Monumentos();
	    try{                
                // muestro datos por pantalla
                mon = (Monumentos) session.load(Monumentos.class, (byte) 10);    // Load se maneja con la excecepcion, y get con Null          
		System.out.println("Nombre de monumento:" + mon.getNombre());
		System.out.println("Localidad:" + mon.getLocalidad());
                
                // Modificar el monumento en memoria.
                mon.setNombre("TORRE DEL ORO");
                
                // Hacer efectivo el cambio en memoria y persistente en la basa de datos
                session.update(mon); // modifica el objeto
                tx.commit();

            } catch (ObjectNotFoundException o) {// Maneja el objeto si no lo encuenra, en la consulta
			System.out.println("NO EXISTE EL Monumento...");
            }  catch (ConstraintViolationException c) { // Maneja las modificaciones, borrados e inserciones.
			System.out.println("NO SE PUEDE MODIFCIAR UN MONUMENTO QUE NO EXISTE.....");
            } catch (Exception e) { // Añadimos la excepción general si nos interesa
			System.out.println("ERROR NO CONTROLADO....");
			e.printStackTrace();
            }              
                session.close(); // Es necesario borrar la sesion
		System.exit(0); // Finaliza el programa
	}
    
}
