
package datos;

import java.sql.*;

public class Conexion {
    
    private  static String JDBC_DRIVER ="com.mysql.jdbc.Driver"; // cadena de carga de driver
    
    private static String JDBC_URL="jdbc:mysql://localhost/sga?useSSL=false"; //contine la cadena de conexion
    
    private static String JDBC_USER ="root";//
    
    private static String JDBC_PASS ="admin";
    
    private static  Driver driver =null;
    
    
    public static synchronized Connection getConnection() throws SQLException{
        
        if (driver==null) {
            try {
                // se registra el driver
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            } catch (Exception e) {
                System.out.println("fallo al cargar el driver");
                e.printStackTrace();
            }
            
        }
        return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASS);
    }
    
    public static void close(ResultSet rs){
        try {
            if (rs!=null) {
                rs.close();
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void close(Statement stmt){
        try {
            if (stmt!=null) {
                stmt.close();
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void close(Connection conn){
        try {
            if (conn!=null) {
                conn.close();
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}
