package Controller;

import Models.Report;
import Models.User;
import ModelsDAO.ReportDAO;
import ModelsDAO.ReportStatusDAO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/UserReportPdfServlet")
public class UserReportPdfServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("usuario") : null;

        if (user == null) {
            response.sendRedirect("../index.jsp");
            return;
        }

        ReportDAO dao = new ReportDAO();
        ReportStatusDAO statusDAO = null;
        try {
            statusDAO = new ReportStatusDAO();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserReportPdfServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Report> reports;

        try {
            // Obtener solo los reportes del usuario actual
            reports = dao.getAllReportsByUser(user.getId());

            // Configurar respuesta como PDF para descarga
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=mis_reportes.pdf");

            // Crear documento PDF
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Estilos para el PDF
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.DARK_GRAY);
            Font labelFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font valueFont = new Font(Font.FontFamily.HELVETICA, 12);

            // Título del documento
            document.add(new Paragraph("Mis Reportes - " + user.getFullName(), titleFont));
            document.add(Chunk.NEWLINE);

            // Sección para cada reporte
            for (Report r : reports) {
                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);
                table.setWidths(new float[]{3, 2});

                // Tabla interna para los datos del reporte
                PdfPTable textTable = new PdfPTable(1);
                textTable.setWidthPercentage(100);
                textTable.addCell(createCell("Título: " + r.getTitle(), labelFont));
                textTable.addCell(createCell("Descripción: " + r.getDescription(), valueFont));
                textTable.addCell(createCell("Fecha: " + r.getReportedAt(), valueFont));
                textTable.addCell(createCell("Ubicación: " + r.getLatitude() + ", " + r.getLongitude(), valueFont));
                String statusName = statusDAO.getStatusName(r.getStatusId());
                textTable.addCell(createCell("Estado: " + statusName, valueFont));

                table.addCell(textTable);

                // Intentar agregar la imagen del reporte si existe
                try {
                    byte[] imageBytes = r.getPhoto();
                    if (imageBytes != null && imageBytes.length > 0) {
                        Image img = Image.getInstance(imageBytes);
                        img.scaleToFit(200, 200);
                        PdfPCell imgCell = new PdfPCell(img, true);
                        imgCell.setBorder(Rectangle.NO_BORDER);
                        table.addCell(imgCell);
                    } else {
                        PdfPCell noImgCell = new PdfPCell(new Phrase("Sin imagen"));
                        noImgCell.setBorder(Rectangle.NO_BORDER);
                        table.addCell(noImgCell);
                    }
                } catch (Exception e) {
                    PdfPCell errorImgCell = new PdfPCell(new Phrase("Error al cargar imagen"));
                    errorImgCell.setBorder(Rectangle.NO_BORDER);
                    table.addCell(errorImgCell);
                }

                document.add(table);
                document.add(new LineSeparator());
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método helper para crear celdas sin bordes
    private PdfPCell createCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }
}