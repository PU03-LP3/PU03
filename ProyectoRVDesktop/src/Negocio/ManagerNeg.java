package Negocio;

import Conexion.conexion;
import java.sql.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class ManagerNeg {

    public List<String[]> listarReportesAsignados(int managerId) {
        List<String[]> lista = new ArrayList<>();
        String sql = """
            SELECT r.id, r.title, u.full_name AS ciudadano, s.status_name
            FROM reports r
            LEFT JOIN users u ON r.user_id = u.id
            LEFT JOIN report_status s ON r.status_id = s.id
            WHERE r.assigned_to = ?
              AND r.status_id != (SELECT id FROM report_status WHERE status_name = 'Cancelled')
        """;
        try (Connection cn = conexion.obtener();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, managerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new String[]{
                    rs.getString("id"),
                    rs.getString("title"),
                    rs.getString("ciudadano"),
                    rs.getString("status_name")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Map<String, Integer> obtenerEstados() {
        Map<String, Integer> estados = new LinkedHashMap<>();
        String sql = "SELECT id, status_name FROM report_status";
        try (Connection cn = conexion.obtener();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                estados.put(rs.getString("status_name"), rs.getInt("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estados;
    }

    public boolean actualizarEstado(int reportId, int statusId) {
        String updateReport = "UPDATE reports SET status_id = ? WHERE id = ?";
        String insertHistory = "INSERT INTO report_status_history (report_id, status_id, changed_at) VALUES (?, ?, NOW())";
        try (Connection cn = conexion.obtener()) {
            cn.setAutoCommit(false);
            try (
                PreparedStatement psUpdate = cn.prepareStatement(updateReport);
                PreparedStatement psInsert = cn.prepareStatement(insertHistory)) {

                psUpdate.setInt(1, statusId);
                psUpdate.setInt(2, reportId);
                psUpdate.executeUpdate();

                psInsert.setInt(1, reportId);
                psInsert.setInt(2, statusId);
                psInsert.executeUpdate();

                cn.commit();
                return true;
            } catch (Exception e) {
                cn.rollback();
                e.printStackTrace();
                return false;
            }
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

    public int[] obtenerProgreso(int managerId) {
        String totalSQL = """
            SELECT COUNT(*) FROM reports
            WHERE assigned_to = ?
              AND status_id != (SELECT id FROM report_status WHERE status_name = 'Cancelled')
        """;
        String cerradosSQL = """
            SELECT COUNT(*) FROM reports
            WHERE assigned_to = ?
              AND status_id = (SELECT id FROM report_status WHERE status_name = 'Closed')
        """;
        try (Connection cn = conexion.obtener()) {
            int total = 0, cerrados = 0;

            try (PreparedStatement psTotal = cn.prepareStatement(totalSQL)) {
                psTotal.setInt(1, managerId);
                ResultSet rs = psTotal.executeQuery();
                if (rs.next()) total = rs.getInt(1);
            }

            try (PreparedStatement psCerrados = cn.prepareStatement(cerradosSQL)) {
                psCerrados.setInt(1, managerId);
                ResultSet rs = psCerrados.executeQuery();
                if (rs.next()) cerrados = rs.getInt(1);
            }

            return new int[]{cerrados, total};
        } catch (Exception e) {
            e.printStackTrace();
            return new int[]{0, 0};
        }
    }
}
