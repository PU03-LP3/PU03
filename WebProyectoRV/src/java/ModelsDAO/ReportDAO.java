package ModelsDAO;

import ModelsDAO.*;
import Conexion.conexion;
import Models.Report;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReportDAO {


    //Inserta un nuevo reporte con su foto en la base de datos, col el objeto repote 
    //en caso de ser exitosa, se inserta, mas devuelve null si no a sido exitosa
    public boolean insertReportWithPhoto(Report report) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmtReport = null;
        PreparedStatement pstmtPhoto = null;
        ResultSet generatedKeys = null;

        try {
            conn = conexion.obtener();
            conn.setAutoCommit(false);
            String sqlReport = "INSERT INTO reports (user_id, problem_type_id, title, description, latitude, longitude, address, status_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstmtReport = conn.prepareStatement(sqlReport, Statement.RETURN_GENERATED_KEYS);
            pstmtReport.setInt(1, report.getUserId());
            pstmtReport.setInt(2, report.getProblemTypeId());
            pstmtReport.setString(3, report.getTitle());
            pstmtReport.setString(4, report.getDescription());
            pstmtReport.setDouble(5, report.getLatitude());
            pstmtReport.setDouble(6, report.getLongitude());
            pstmtReport.setString(7, report.getAddress());
            pstmtReport.setInt(8, report.getStatusId());

            int affectedRows = pstmtReport.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating report failed, no rows affected.");
            }

            generatedKeys = pstmtReport.getGeneratedKeys();
            if (generatedKeys.next()) {
                int reportId = generatedKeys.getInt(1);

                String sqlPhoto = "INSERT INTO report_photos (report_id, photo_data, content_type) VALUES (?, ?, ?)";
                pstmtPhoto = conn.prepareStatement(sqlPhoto);
                pstmtPhoto.setInt(1, reportId);
                pstmtPhoto.setBytes(2, report.getPhoto());
                pstmtPhoto.setString(3, report.getContentType());
                pstmtPhoto.executeUpdate();
            } else {
                throw new SQLException("Creating report failed, no ID obtained.");
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (pstmtPhoto != null) pstmtPhoto.close();
            if (pstmtReport != null) pstmtReport.close();
            if (conn != null) conn.close();
        }
    }
   
    



    //Obtiene reporte mediante el id
    public Report getReportById(int reportId) throws SQLException, ClassNotFoundException {
    String sql = "SELECT r.*, p.photo_data, p.content_type " +
                 "FROM reports r " +
                 "LEFT JOIN report_photos p ON r.id = p.report_id " +
                 "WHERE r.id = ?";
    
    try (Connection conn = conexion.obtener();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, reportId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return new Report(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("problem_type_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getDouble("latitude"),
                    rs.getDouble("longitude"),
                    rs.getString("address"),
                    rs.getInt("status_id"),
                    rs.getTimestamp("reported_at"),
                    rs.getBytes("photo_data"),
                    rs.getString("content_type")
                );
            }
        }
    }
    return null;
}


    
    
    //obtiene todos los reportes del usuario en especifico (usado para el home de usuario)
    //usa su id y devuelve una lsita
    public List<Report> getAllReportsByUser(int userId) throws SQLException, ClassNotFoundException {
        List<Report> reports = new ArrayList<>();
        String sql = "SELECT r.*, p.photo_data, p.content_type FROM reports r LEFT JOIN report_photos p ON r.id = p.report_id WHERE r.user_id = ? ORDER BY r.reported_at DESC";

        try (Connection conn = conexion.obtener();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Report report = new Report(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("problem_type_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getDouble("latitude"),
                    rs.getDouble("longitude"),
                    rs.getString("address"),
                    rs.getInt("status_id"),
                    rs.getTimestamp("reported_at"),
                    rs.getBytes("photo_data"),
                    rs.getString("content_type")
                );
                reports.add(report);
            }
        }
        return reports;
    }
}