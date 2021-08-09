package conexion;

import java.sql.*;

/**
 *
 * @author ioriyagamy
 */
public class Conexion {

//    Connection conn;
    private final String db = "Hillary";
    private final String user = "root";
    private final String pass = "root";
//    private final String url = "jdbc:mysql://localhost:3306/" + db + "?serverTimezone=UTC";
    private final String url = "jdbc:mysql://localhost/" + db + "?SslMode=none";
    private Connection conn = null;

//    public Connection getConn() {
//
//        try {
////            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(url, user, pass);
//            if (conn != null) {
//                System.out.println("Conexion a la base de Datos Listo.......");
//                return conn;
//            }
//
//        } catch (SQLException ex) {
//            System.err.println(ex);
//        }
//        return null;
//    }

    
    public Conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(this.url, this.user, this.pass);
            if(conn != null){
                System.out.println("Conexion a la base de datos " + this.db + "......Listo");
            }
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }
    
    public Connection getConn(){
        return conn;
    }
}
