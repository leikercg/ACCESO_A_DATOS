// Conexión a servidor MySQL, pero usando un POOL DE CONEXIONES

// Paso 1. Importar las clases necesarias

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UD2_JDBC_MySQL_POOL_Conexiones {

    public static void main(String[] args) {
        // TODO code application logic here
        
        try {
        	// LOS PASOS 2 y 3 los hace la clase ConnectionPool con un objeto BasicDataSource 
        	// Paso 4. Crea objeto Connection
            Connection conexion =  ConnectionPool.getInstance().getConnection();
            
            if(conexion !=null){					// Muestra un mensaje indicando si conecta o no
                System.out.println("conectado ");
                // ConnectionPool.getInstance().closeConnection(conexion);
            }else{
                System.out.println("No conectado");                
            }
            
            
            // Paso 5. Crea objeto Statement
         	Statement sentencia = conexion.createStatement();
         			
         	// Pasos 6 y 7. Ejecuta la consulta y recupera los datos en el ResulSet
         	String sql = "SELECT * FROM departamentos";
         	ResultSet res = sentencia.executeQuery(sql);
         			
         	// Hace el tratamiento de los datos. En este caso los imprime recorriendo el ResultSet
         	while (res.next() ) {
         		System.out.printf("%d, %s, %s %n", res.getInt(1), res.getString(2), res.getString(3));
         	}
         			
         	res.close();		// Paso 8. Libera objeto ResultSet
         	sentencia.close();	// Paso 9. Libera objeto Statement

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        /*
        for (int i = 0; i < 2000; i++) {
        try {
        Connection c =  ConnectionPool.getInstance().getConnection();
        if(c!=null){
        System.out.println("conectado "+i);
        ConnectionPool.getInstance().closeConnection(c);
        }else{
        System.out.println("No conectado "+i);
        }
        } catch (IOException ex) {
        Logger.getLogger(TestDBConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
        Logger.getLogger(TestDBConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PropertyVetoException ex) {
        Logger.getLogger(TestDBConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
        }*/ 
        
        
        
        /*
        for (int i = 0; i < 2000; i++) {
            try {

    
                Connection c =  ConnectionPool.getInstance().getConnection();
                if(c!=null){
                    System.out.println("conectado "+i);
                    ConnectionPool.getInstance().closeConnection(c);
                }else{
                    System.out.println("No conectado "+i);                
                }
                
            } catch (IOException ex) {
                Logger.getLogger(TestDBConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(TestDBConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(TestDBConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    }
    
}