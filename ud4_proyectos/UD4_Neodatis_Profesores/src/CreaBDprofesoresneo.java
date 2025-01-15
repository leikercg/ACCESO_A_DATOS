// Crea una base de datos NeoDatis a partir de una base de datos relacional MySQL

import java.sql.*;
import java.util.HashSet;

import org.neodatis.odb.ODB;

import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import clases.*;

public class CreaBDprofesoresneo {
	static ODB bd;
	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/profesores", "root", "");
			bd = ODBFactory.open("profesores.neo");
			
			// Recorrer c1_asignaturas y guardar en NeoDatis
			InsertarAsignaturas(conexion);
			
			// Recorrer c1_Centros y guardar en NeoDatis
			InsertarCentros(conexion);
			
			// Recorrer c1_Profesores y guardar en NeoDatis
			InsertarProfesores(conexion);
			
			// Llenar el set de profesores de asignaturas, por cada objeto asignatura hacemos la select
			llenarSetProfesAsignaturas(conexion);
			
			// Llenar el set de profesores de Centros y el director
			llenarSetProfesEnCentrosYDirector(conexion);
			
			conexion.close();
			bd.close();
			
		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}  // fin main

	private static void llenarSetProfesEnCentrosYDirector(Connection conexion) throws SQLException {
		Objects<Centro> objectscen = bd.getObjects(Centro.class);
		while (objectscen.hasNext()) {
			Centro cee = objectscen.next();
			HashSet<Profesor> setprofesores = new HashSet<Profesor>();
			Statement sentencia = conexion.createStatement();
			ResultSet resul = sentencia
					.executeQuery("SELECT * FROM c1_profesores where cod_centro=" + cee.getCodCentro());
			while (resul.next()) {
				IQuery consulta = new CriteriaQuery(Profesor.class, Where.equal("codProf", resul.getInt(1)));
				Profesor obj = (Profesor) bd.getObjects(consulta).getFirst();
				setprofesores.add(obj);
			}
			cee.setSetprofesores(setprofesores);
			// Inserto el director.
			sentencia = conexion.createStatement();
			resul = sentencia.executeQuery("SELECT director FROM c1_centros where cod_centro=" + cee.getCodCentro());
			if (resul.next()) {
				IQuery consulta = new CriteriaQuery(Profesor.class, Where.equal("codProf", resul.getInt(1)));
				try {
					Profesor obj = (Profesor) bd.getObjects(consulta).getFirst();
					cee.setDirector(obj);
				} catch (IndexOutOfBoundsException ee) {
					System.out.println("Centro  " + cee.getCodCentro() + ", Sin Director, es null.");
				}
			}
			bd.store(cee);
			resul.close();
			sentencia.close();
		}
		bd.commit();
	}  // llenarSetProfesEnCentrosYDirector

	private static void llenarSetProfesAsignaturas(Connection conexion) throws SQLException {
		Objects<Asignatura> objects = bd.getObjects(Asignatura.class);
		while (objects.hasNext()) {
			Asignatura asi = objects.next();
			HashSet<Profesor> setprofesores = new HashSet<Profesor>();
			Statement sentencia = conexion.createStatement();
			ResultSet resul = sentencia
					.executeQuery("SELECT * FROM c1_asigprof where cod_asig = '" + asi.getCodAsig() + "'");
			while (resul.next()) {
				IQuery consulta = new CriteriaQuery(Profesor.class, Where.equal("codProf", resul.getInt(2)));
				Profesor obj = (Profesor) bd.getObjects(consulta).getFirst();
				setprofesores.add(obj);
			}
			asi.setSetprofesores(setprofesores);
			bd.store(asi);
			resul.close();
			sentencia.close();
		}
		bd.commit();	
	}  // llenarSetProfesAsignaturas

	private static void InsertarProfesores(Connection conexion) {
		try {
			Statement sentencia = conexion.createStatement();
			ResultSet resul = sentencia.executeQuery("SELECT * FROM c1_Profesores");  // Obtiene los valores de la tabla relacional
			while (resul.next()) {
				if (comprobarprofe(resul.getInt(1)) == false) {
					
					// Obtiene el objeto Centro que se corresponde al codigo de centro del profesor que ha leido de la tabla relacional
					IQuery consulta = new CriteriaQuery(Centro.class, Where.equal("codCentro", resul.getInt(6)));
					Centro cen = (Centro) bd.getObjects(consulta).getFirst();		  // 
					
					// Crea la instancia de Profesor, teniendo en cuenta que el atributo centros es el objeto que ha recuperado en la consulta
					Profesor nueprof = new Profesor(resul.getInt(1), resul.getString(2), resul.getString(3),
							resul.getDate(4), resul.getString(5), cen);
					bd.store(nueprof);												  // Almacena objeto clase Profesor
					System.out.println("Profe grabado " + resul.getInt(1));
				} else
					System.out.println("Profe: " + resul.getInt(1) + ", EXISTE.");
			}
			bd.commit();															  // Confirma todos los cambios
			resul.close();
			sentencia.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}  // fin InsertarProfesores

	private static void InsertarCentros(Connection conexion) {
		try {
			Statement sentencia = (Statement) conexion.createStatement();
			ResultSet resul = sentencia.executeQuery("SELECT * FROM c1_centros");   // Obtiene los valores de la tabla relacional
			while (resul.next()) {
				if (comprobarcentro(resul.getInt(1)) == false) {
					HashSet<Profesor> setprofesores = new HashSet<Profesor>();		// Nueva instancia de conjunto tipo Profesor
					// El atributo que ocupa la tercera posición es el Director, que es de la clase Profesor, y lo dejamos a null
					Centro cen = new Centro(resul.getInt(1), resul.getString(2), null, resul.getString(4),
							resul.getString(5), resul.getString(6), setprofesores);
					bd.store(cen);													// Almacena objeto clase Centro
					System.out.println("Centro grabado " + resul.getInt(1));
				} else
					System.out.println("Centro: " + resul.getInt(1) + ", EXISTE.");
			}
			bd.commit();															// Confirma todos los cambios
			resul.close();
			sentencia.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}  // fin InsertarCentros

	private static void InsertarAsignaturas(Connection conexion)  {
		try {
			Statement sentencia = (Statement) conexion.createStatement();
			ResultSet resul = sentencia.executeQuery("SELECT * FROM c1_asignaturas");  // Obtiene los valores de la tabla relacional
			while (resul.next()) {
				if (comprobarasig(resul.getString(1)) == false) {
					HashSet<Profesor> setprofesores = new HashSet<Profesor>();  // Nueva instancia de conjunto tipo Profesor
					Asignatura ass = new Asignatura(resul.getString(1), resul.getString(2), setprofesores);
					bd.store(ass);												// Almacena objeto clase Asignatura
					System.out.println("Asignatura grabada " + resul.getString(1));
				} else
					System.out.println("Asignatura: " + resul.getString(1) + ", EXISTE.");
			}
			bd.commit();														// Confirma todos los cambios
			resul.close();
			sentencia.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}  // fin InsertarAsignaturas

	private static boolean comprobarasig(String cod) {
		try {
			IQuery consulta = new CriteriaQuery(Asignatura.class, Where.equal("codAsig", cod));
			Asignatura obj = (Asignatura) bd.getObjects(consulta).getFirst();
			return true;  // "EXISTE DEVUELVE true.";
			
		} catch (IndexOutOfBoundsException e) {  // Salta excepción => "NO EXISTE DEVUELVE false."
			return false;
		}
	} // fin comprobarasig

	private static boolean comprobarcentro(int cod) {
		try {
			IQuery consulta = new CriteriaQuery(Centro.class, Where.equal("codCentro", cod));
			Centro obj = (Centro) bd.getObjects(consulta).getFirst();
			return true;  // "EXISTE DEVUELVE true.";
			
		} catch (IndexOutOfBoundsException e) {  // Salta excepción => "NO EXISTE DEVUELVE false."
			return false;
		}
	} // fin comprobarcentro

	private static boolean comprobarprofe(int cod) {

		try {
			IQuery consulta = new CriteriaQuery(Profesor.class, Where.equal("codProf", cod));
			Profesor obj = (Profesor) bd.getObjects(consulta).getFirst();
			return true;  // "EXISTE DEVUELVE true.";

		} catch (IndexOutOfBoundsException e) {  // Salta excepción => "NO EXISTE DEVUELVE false."
			return false;
		}
	} // fin comprobarprofe
	
} // fin clase