package Conexion;

import java.sql.*;

public class conexion {
    private static Connection cnx = null;

    public static Connection obtener() throws SQLException, ClassNotFoundException {
        if (cnx == null || cnx.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://161.132.45.136:3306/road_report_system?autoReconnect=true&useSSL=false";
                cnx = DriverManager.getConnection(url, "usuario", "nestor");
            } catch (SQLException ex) {
                throw new SQLException(ex);
            } catch (ClassNotFoundException ex) {
                throw new ClassCastException(ex.getMessage());
            }
        }
        return cnx;
    }

    private static void cerrar() throws SQLException {
        if (cnx != null && !cnx.isClosed()) {
            cnx.close();
        }
    }
} 