/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate_examen;

import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Alumno
 */
public class Hibernate_examen {

    /**
     * @param args the command line arguments
     */
public static void main(String[] args) {
                Logger.getLogger("org.hibernate").setLevel(Level.OFF); // Eliminamos los logs de la 

		SessionFactory sesion = NewHibernateUtil.getSessionFactory();
		Session session = sesion.openSession(); // Hay que crear una sesisón para realizar consultas.
                
                Transaction tx = session.beginTransaction();// NECESARIO PARA EL INSER, UPDATE Y DELETE

		System.out.println("==============================");
		System.out.println("DATOS DEL Producto 10.");
                Productos prod = new Productos();
	    try{                    
                prod = (Productos) session.load(Productos.class, (int) 10);              
		System.out.println("Nombre del producto:" + prod.getPnombre());
		System.out.println("Precio:" + prod.getPrecio());

		System.out.println("==============================");
		System.out.println("Pedidos DEL Producto 10.");

		Set<Pedidos> listaProd = prod.getPedidoses();// obtenemos pedidos 

		Iterator<Pedidos> it = listaProd.iterator();

                
		System.out.printf("N�mero de pedidos: %d %n", listaProd.size());
		while (it.hasNext()) {
			// Empleados emple = new Empleados();
			Pedidos pedido = it.next();
			System.out.printf("%d * %s %.2f %n", pedido.getNumPed(),pedido.getNcliente(), pedido.getImporte());
		}
		System.out.println("==============================");
                
                listaproductos(session,30);// Usamos el metodo creado	
                
                System.out.println();
                
                System.out.println("============= CLASES NO ASOCIADAS=================");
                String hql = "from Pedidos pe, Productos pr where pe.productos.codProd = pr.codProd order by pnombre";
			Query cons = session.createQuery(hql);
			Iterator q = cons.iterate();
			while (q.hasNext()) {
				Object[] par = (Object[]) q.next();
				 Pedidos pedidos = (Pedidos) par[0]; // Primera parte de la consulta
				Productos productos = (Productos) par[1]; // Segunda parte de la consulta
				System.out.printf("%d, %s, %.2f, %d, %s %n", pedidos.getNumPed(),pedidos.getNcliente(),pedidos.getImporte(),pedidos.getProductos().getCodProd(),productos.getPnombre());
			}//NOTA: la clase pedidos no tiene la clase codProd, la tiene productos.getCodProd, uno de uss atributos
			System.out.println("===============================================");
                        
                        
                        
                        
                 System.out.println("============= CLASES NO ASOCIADAS PROMEDIO=================");
                         // MOSTRAR SALARIO MEDIO DE LOS EMPLEADOS
                        String hqlPromedio = "select avg(pr.precio) from Productos as pr";
			Query consPromedio = session.createQuery(hqlPromedio);
			Double media = (Double) consPromedio.uniqueResult(); // USAR SOLO SI DEVUELVE UN UNICO RESULTADO
			System.out.printf("Precio medio: %.2f%n", media);
                        
                System.out.println("===============================================");
                
                       
                 System.out.println("============= CLASES NO ASOCIADAS PROMEDIO y SUMA ================");
                        String hql2 = "select avg(precio), sum(precio) from Productos ";
                        Query cons2 = session.createQuery(hql2);
                        Object[] resultado = (Object[]) cons2.uniqueResult(); // Por que apesar de ser dos columnas es sola una fila, si son valores no relacionados usar Object
                        System.out.printf("Precio medio: %.2f \n", resultado[0]);
                        System.out.printf("Suma del precio total: %.2f \n", resultado[1]);
                        
                System.out.println("===============================================");


                
                
                System.out.println("============= USO DEL INSERT+SELECT ================"); //HAY QUE USAR TRANSACCION EN UPDATE,INSERT O DELETE

                // Insertamos los departamentos de la tabla NUEVOS, la tabla tiene
		// que estar mapeada en nuestro proyecto
		String hqlInsert = "insert into Productos (codProd, pnombre, precio) " +
			             " select n.codProd, n.pnombre, n.precio from NuevosProductos n"; // Probar en la linea HQL
		Query insertar =session.createQuery( hqlInsert );
		int filascreadas = insertar.executeUpdate();	
                tx.commit(); // valida la transacción
                System.out.printf("FILAS INSERTADAS: %d%n",filascreadas);
                
                
                
                
                
	
            } catch (ObjectNotFoundException o) {
			System.out.println("NO EXISTE EL Producto...");
                        tx.rollback();// SI FALLA ALGO HACER ROLLBACK
            }               
                session.close();
		System.exit(0);
	}

private static void listaproductos(Session session, int codprod){
    
                System.out.println("===========CONSULTAS CON PARÁMETROS=========");
		String hql = "from Productos where codProd = :codigo";
		Query q1 = session.createQuery(hql);
		q1.setParameter("codigo", codprod); //Se castea por que la clase este atributo es de tipo float
		Productos prd = (Productos) q1.uniqueResult();
		System.out.printf("%s, %.2f %n", prd.getPnombre(), prd.getPrecio());
}
    
}
