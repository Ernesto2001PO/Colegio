package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


public class Conexion {

    private static Logger logger = Logger.getLogger(Conexion.class.getName());
    private static final String URL = "jdbc:postgresql://localhost:5432/Colegio";
    private static final String USER = "postgres";
    private static final String PASS = "tito12396";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void useConnection() {
        Connection conn = null;
        try {
            conn = Conexion.getConnection();
            logger.info("Conexion exitosa!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.info("Conexion fallida!");
        } finally {
            Conexion.closeConnection(conn);
            logger.info("Conexion cerrada!");
        }
    }

    public static void main(String[] args) {
        useConnection();

    }
}
