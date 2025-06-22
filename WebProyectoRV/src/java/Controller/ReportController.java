package Controller;

import Models.Report;
import Models.User;
import ModelsDAO.ReportDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/submitReport")
@MultipartConfig // Permite manejo de archivos en formularios
public class ReportController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("usuario") : null;

        if (user == null) {
            response.sendRedirect("../index.jsp");
            return;
        }

        try {
            // Obtener parámetros del formulario
            String problemType = request.getParameter("problemType"); // ← Puedes usarlo si es dinámico
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String latitudeStr = request.getParameter("latitude");
            String longitudeStr = request.getParameter("longitude");
            String address = request.getParameter("address");

            // Validaciones mínimas (puedes expandir según necesidad)
            if (title == null || title.isBlank() || description == null || description.isBlank()) {
                response.sendRedirect("User/reportar.jsp?error=Campos+obligatorios+faltantes");
                return;
            }

            double latitude = Double.parseDouble(latitudeStr);
            double longitude = Double.parseDouble(longitudeStr);

            // Procesar archivo (foto)
            Part filePart = request.getPart("photo");
            byte[] photoData = null;
            String contentType = null;

            if (filePart != null && filePart.getSize() > 0) {
                photoData = filePart.getInputStream().readAllBytes();
                contentType = filePart.getContentType();
            }

            // Crear objeto Reporte
            Report report = new Report();
            report.setUserId(user.getId());
            report.setProblemTypeId(Integer.parseInt(problemType)); 
            report.setTitle(title);
            report.setDescription(description);
            report.setLatitude(latitude);
            report.setLongitude(longitude);
            report.setAddress(address);
            report.setStatusId(1); // Estado inicial
            report.setPhoto(photoData);
            report.setContentType(contentType);

            // Guardar en base de datos
            ReportDAO reportDAO = new ReportDAO();
            boolean inserted = reportDAO.insertReportWithPhoto(report);

            if (inserted) {
                response.sendRedirect("User/reportar.jsp?success=Reporte+enviado+correctamente");
            } else {
                response.sendRedirect("User/reportar.jsp?error=Error+al+guardar+el+reporte");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("User/reportar.jsp?error=Coordenadas+inválidas");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("User/reportar.jsp?error=Error+en+el+proceso");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher("/User/reportar.jsp").forward(request, response);
    }
}
