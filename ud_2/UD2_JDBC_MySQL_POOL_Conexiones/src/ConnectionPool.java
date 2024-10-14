
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;


public class ConnectionPool {
    
    private final String DB="ud2_mysql";
    private final String URL="jdbc:mysql://localhost:3306/"+DB+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USER="root";
    private final String PASS="Ad1rectory";
       
    private static ConnectionPool dataSource;
    private BasicDataSource basicDataSource=null;
    
    private ConnectionPool(){    // Constructor de la clase. Inicializa el objeto BasicDataSource. Al ser privado no se le puede llamar directamente
     
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASS);
        basicDataSource.setUrl(URL);
        
        basicDataSource.setMinIdle(5);  // Mínimo conexiones inactivas
        basicDataSource.setMaxIdle(20);  // Máximo conexiones inactivas
        basicDataSource.setMaxTotal(50);  // Máximo de conexiones activas + inactivas se permiten en el pool.
        basicDataSource.setMaxWaitMillis(-1);  // Tiempo en espera que se espera. -1 significa infinito, hasta que se libere una conexión
        
    }
    
    public static ConnectionPool getInstance() {  // Este método garantiza que sólo habrá una instancia de esta clase
        if (dataSource == null) {
            dataSource = new ConnectionPool();
            return dataSource;
        } else {
            return dataSource;
        }
    }

    public Connection getConnection() throws SQLException{
      return this.basicDataSource.getConnection();
    }
    
    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }    
    
}


