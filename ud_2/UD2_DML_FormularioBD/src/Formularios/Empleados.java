package Formularios;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JComboBox;

public class Empleados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblEmpleados;
	private JButton btnVerDptos;
	
	String tablaBD = "empleados";	// define la tabla de la base de datos desde la que se obtienen los datos
	private JScrollPane scrollPane;
	private JPanel panel;

	/* Launch the application */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Empleados frame = new Empleados();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Create the frame */
	public Empleados() {
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(UIManager.getColor("InternalFrame.minimizeIconBackground"));
		panel.setBounds(0, 0, 424, 80);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel lblTitulo = new JLabel("FORMULARIO EMPLEADOS");
		lblTitulo.setBounds(0, 4, 179, 14);
		panel.add(lblTitulo);
		
				JButton btnVerEmpleados = new JButton("Ver empleados");
				btnVerEmpleados.setBounds(321, 11, 103, 23);
				panel.add(btnVerEmpleados);
				
				btnVerDptos = new JButton("Ver dptos.");
				btnVerDptos.setBounds(189, 11, 103, 23);
				panel.add(btnVerDptos);
				
				JComboBox cmbEmpleados = new JComboBox();
				cmbEmpleados.setBounds(10, 47, 404, 22);
				panel.add(cmbEmpleados);
				cmbEmpleados.removeAllItems();
				ArrayList<String> Lista = new ArrayList<String>();
				llenarCombo();
				
				btnVerDptos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tablaBD = "departamentos";
						llenarTabla(tablaBD);
					}
				});
				btnVerEmpleados.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tablaBD = "empleados";
						llenarTabla(tablaBD);
					}
				});
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 95, 424, 324);
		contentPane.add(scrollPane);
		
		tblEmpleados = new JTable();
		scrollPane.setViewportView(tblEmpleados);
		
		// Llamada al m�todo que carga los datos en la tabla
		llenarTabla(tablaBD);
				
	} // fin Empleados()
	
	
	/* M�todo que carga los datos en la tabla procedentes de la base de datos */
	private void llenarTabla(String tablaBD) {
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Paso 3. Identifico el origen de datos
			String url = "jdbc:mysql://localhost/ud2_xampp";
			String usuario = "leiker";
			String passwd = "leiker";
				
			// Paso 4. Crea objeto Connection
			Connection conexion = DriverManager.getConnection(url,usuario,passwd);
			
			Statement sentencia = (Statement) conexion.createStatement();
			String consulta = "SELECT * FROM " + tablaBD;					// Extrae los datos de la tabla de la BD
			ResultSet resul = sentencia.executeQuery(consulta);
			ResultSetMetaData rsmd = (ResultSetMetaData) resul.getMetaData();  	// Para obtener metadatos de la BD
			
			// N�mero de columnas
			int nColumnas = rsmd.getColumnCount();
			
			// N�mero de filas
			String consulta2 = "select count(*) from " + tablaBD;
			ResultSet resul2 = sentencia.executeQuery(consulta2);
			resul2.next();
			int filas = resul2.getInt(1);
			resul2.close();

			// Se obtiene cada una de las etiquetas para cada columna
			String[] etiquetas = new String[nColumnas];
			
			for (int i = 1; i <= nColumnas; i++) {
				rsmd.getColumnName(i);
				// System.out.println("A�ado la columna " + rsmd.getColumnName(i).toUpperCase());
				etiquetas[i - 1] = rsmd.getColumnName(i).toUpperCase();
			}
			//System.out.println("Filas: " + filas + ", columnas: " + nColumnas);

			// Recorremos el resul para cargar las filas de la consulta al array bidimensional de objetos
			int numeroFila = 0;
			Object[][] datos = new Object[filas][nColumnas];
			resul = sentencia.executeQuery(consulta);
			while (resul.next()) {
			   //Bucle para cada fila, a�adir las columnas 
		         for (int i = 0; i < nColumnas; i++) {
					datos[numeroFila][i] = resul.getObject(i + 1);
					// System.out.println("A�ado la columna " + i + ", datos " + resul.getString(i + 1));
				}
			   numeroFila++;
			}  // while

			// Asignamos los datos al modelo
			DefaultTableModel modelo = new DefaultTableModel(datos, etiquetas);
			modelo.setColumnIdentifiers(etiquetas);
			modelo.setDataVector(datos, etiquetas);
			
		    // Asignamos el modelo a la tabla
			tblEmpleados.setModel(modelo);
			Color fg = Color.LIGHT_GRAY;
			tblEmpleados.setBackground(fg);
			tblEmpleados.setForeground(Color.BLUE);
			
			// Vector que permite recuperar los datos de una fila de la tabla
			tblEmpleados.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					System.out.println("He pulsado en la fila " + tblEmpleados.getSelectedRow());
			        // Creamos un vector para recuperar la fila, partiendo del modelo
					Vector datostabla = modelo.getDataVector();
			        // Los pasamos a una lista
					List datosFila = (List) datostabla.get(tblEmpleados.getSelectedRow());
					// Recorremos la lista y visualizamos
					Integer codigo = 0;
					if (datosFila.size()>0)
						{ codigo = (Integer) datosFila.get(0);
			                     for (int i = 0; i < datosFila.size(); i++) {
							System.out.println("Dato " + i + " : " + datosFila.get(i));
						  }
						}
					   }
							
					});

			resul.close();
			conexion.close();

		} catch (ClassNotFoundException cn) {
			System.out.println("-------------------------------------");
			System.out.println("ERRORRRR EN EL DRIVER MYSQL");
			System.out.println(cn);
			System.out.println("-------------------------------------");
		
		} catch (SQLException e) {
			System.out.println("-------------------------------------");
			// e.printStackTrace();
			System.out.println("C�digo de error: " + e.getErrorCode());
			System.out.println("Mensaje de error: " + e.getMessage());
			System.out.println("-------------------------------------");
		}
		
	} // fin llenarTablaEmpleados
	
	/* M�todo que carga los datos en la tabla procedentes de la base de datos */
	public static ArrayList<String> llenarCombo() {
		ArrayList<String> Lista = new ArrayList<String>();
	try {	
		Class.forName("com.mysql.jdbc.Driver");
		// Paso 3. Identifico el origen de datos
		String url = "jdbc:mysql://localhost/ud2_xampp";
		String usuario = "leiker";
		String passwd = "leiker";
			
		// Paso 4. Crea objeto Connection
		Connection conexion = DriverManager.getConnection(url,usuario,passwd);
		
		Statement sentencia = (Statement) conexion.createStatement();
		
		String sql = "SELECT Apellidos FROM empleados";
		ResultSet resul = sentencia.executeQuery(sql);
		while (resul.next()) {
		   	String Apellido = resul.getString(1);
				
		}  // while
		
		resul.close();
		conexion.close();
		
	} catch (ClassNotFoundException cn) {
		System.out.println("-------------------------------------");
		System.out.println("ERRORRRR EN EL DRIVER MYSQL");
		System.out.println(cn);
		System.out.println("-------------------------------------");
	
	} catch (SQLException e) {
		System.out.println("-------------------------------------");
		// e.printStackTrace();
		System.out.println("C�digo de error: " + e.getErrorCode());
		System.out.println("Mensaje de error: " + e.getMessage());
		System.out.println("-------------------------------------");
	}
		
	return Lista;
	} // fin llenarCombo
	
}	//fin de la clase	

		
	

