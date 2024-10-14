import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Ejer2_5 {

	static String BDPer = "EMPLEDEP.yap";

	public static void main(String[] args) throws ParseException {

		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDPer);
		/*
		 * 
		 * COMENTAMOS PARA QUE NO LOS VUELVA A CREAR
		 * 
		 * // Creamos Departamentos Departamento p1 = new Departamento(10,
		 * "Guadalajara", "Sevilla"); Departamento p2 = new Departamento(20,
		 * "Investigación", "Madrid"); Departamento p3 = new Departamento(30, "Ventas",
		 * "Barcelona"); Departamento p4 = new Departamento(40, "Producion", "Bilbao");
		 * 
		 * // Almacenar objetos Departamento en la base de datos db.store(p1);
		 * db.store(p2); db.store(p3); db.store(p4);
		 * 
		 * // Creamos Empleados
		 * 
		 * SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); Date fecha1 =
		 * formato.parse("23/11/2015");
		 * 
		 * Date hoy = new Date(); Date fechaHoy = new Date(hoy.getTime());
		 * 
		 * Empleado e1 = new Empleado(7369, "Sanchez","Empledo",7902,fecha1,1040.0,0.0,
		 * 20); Empleado e2 = new Empleado(7499,
		 * "Arroyo","Vendedor",7698,fechaHoy,1500.0,390.0, 30); Empleado e3 = new
		 * Empleado(7521, "Sala","Vendedor",7698,fecha1,1652.0,650.0, 30); Empleado e4 =
		 * new Empleado(7566, "Jimenez","Director",7839,fecha1,2900.0,0.0, 20);
		 * 
		 * 
		 * // Almacenar objetos Empleados en la base de datos db.store(e1);
		 * db.store(e2); db.store(e3); db.store(e4);
		 */
		
		Departamento dpto = new Departamento(null, null,null);
		ObjectSet<Departamento> result = db.queryByExample(dpto);
		if (result.size() == 0)
			System.out.println("No existen Registros de DEPARTAMENTOS..");
		else {
			System.out.printf("N�mero de dptos: %d %n", result.size());

			while (result.hasNext()) {
				Departamento p = result.next();
				System.out.printf("Número: %d, Nombre: %s, Ciudad: %s %n", p.getDept_no(), p.getDept_nombre(), p.getDept_loc());
			}
		}
		db.close(); // cerrar base de datos

	}// fin de main

}
