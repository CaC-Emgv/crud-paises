
package modelo;



import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;


    

public class Conexion{
    private static Connection con;
    private static BasicDataSource dataSource;
    
    private Conexion(){}
    

    public static DataSource getDataSource() {
        if (dataSource == null) {
            try {
                String URL = "jdbc:mysql://root:root@localhost:3306/crud?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
                dataSource = new BasicDataSource();
                dataSource.setUrl(URL);
                dataSource.setInitialSize(50);
            } catch (Exception ex) {
                throw new RuntimeException("Error al conectar con la BD", ex);
            }
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
    
    
}
    
    