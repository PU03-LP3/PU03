package Negocio;

import Conexion.conexion;
import java.security.MessageDigest;
import java.sql.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class AdminNeg {

    public List<String[]> listarSolicitudes() {
        List<String[]> lista = new ArrayList<>();
        String sql = """
            SELECT r.id, r.title, u.full_name AS ciudadano, 
                   IFNULL(m.full_name, 'Sin asignar') AS encargado
            FROM reports r
            LEFT JOIN users u ON r.user_id = u.id
            LEFT JOIN users m ON r.assigned_to = m.id
        """;
        try (Connection cn = conexion.obtener();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new String[]{
                    rs.getString("id"),
                    rs.getString("title"),
                    rs.getString("ciudadano"),
                    rs.getString("encargado")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Map<String, Integer> obtenerManagers() {
        Map<String, Integer> managers = new HashMap<>();
        String sql = "SELECT id, full_name FROM users WHERE role = 'MANAGER'";
        try (Connection cn = conexion.obtener();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                managers.put(rs.getString("full_name"), rs.getInt("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return managers;
    }

    public boolean delegarReporte(int reportId, int managerId) {
        String sql = "UPDATE reports SET assigned_to = ? WHERE id = ?";
        try (Connection cn = conexion.obtener();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, managerId);
            ps.setInt(2, reportId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public byte[] obtenerFotoReporte(int reportId) {
        String sql = "SELECT photo_data FROM report_photos WHERE report_id = ? LIMIT 1";
        try (Connection cn = conexion.obtener();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, reportId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBytes("photo_data");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<String[]> obtenerProgresoManagers() {
    List<String[]> lista = new ArrayList<>();
    String sql = """
        SELECT u.full_name AS manager,
               COUNT(r.id) AS total,
               SUM(CASE WHEN rs.status_name = 'Closed' THEN 1 ELSE 0 END) AS cerrados
        FROM users u
        LEFT JOIN reports r ON r.assigned_to = u.id
        LEFT JOIN report_status rs ON r.status_id = rs.id
        WHERE u.role = 'MANAGER'
          AND (r.status_id IS NULL OR rs.status_name != 'Cancelled')
        GROUP BY u.id
    """;

    try (Connection cn = conexion.obtener();
         PreparedStatement ps = cn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            String nombre = rs.getString("manager");
            int total = rs.getInt("total");
            int cerrados = rs.getInt("cerrados");
            int porcentaje = (total == 0) ? 0 : (cerrados * 100 / total);
            lista.add(new String[]{nombre, String.valueOf(porcentaje)});
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return lista;
}
public boolean crearUsuario(String fullName, String email, String password, String role) {
    String sql = "INSERT INTO users (full_name, email, password, role, created_at) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
    try (Connection cn = conexion.obtener();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setString(1, fullName);
        ps.setString(2, email);
        ps.setString(3, sha256(password));
        ps.setString(4, role.toUpperCase());

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

private String sha256(String input) throws Exception {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] hash = md.digest(input.getBytes("UTF-8"));
    StringBuilder hexString = new StringBuilder();
    for (byte b : hash) {
        hexString.append(String.format("%02x", b));
    }
    return hexString.toString();
}

}
