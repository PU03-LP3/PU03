// Autor: ""

// Controlador para manejar la visualización de imágenes de reportes
package Controller;

import Models.Report;
import ModelsDAO.ReportDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 * Servlet para recuperar y mostrar imágenes asociadas a reportes
 */
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        //Obtenemos el ID del reporte desde loparametros
        String idStr = request.getParameter("reportId");

        //validamos que el ID sea numero
        if (idStr == null || !idStr.matches("\\d+")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
            return;
        }

        int reportId = Integer.parseInt(idStr);

        try {
            ReportDAO dao = new ReportDAO();
            Report report = dao.getReportById(reportId);

            //Verificamos que exista el reporte y tenga imagen
            if (report == null || report.getPhoto() == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Imagen no encontrada");
                return;
            }

            //Configuramos la respuesta con el tipo y tamaño de la imagen
            response.setContentType(report.getContentType());
            response.setContentLength(report.getPhoto().length);
            
            //Escribimos la imagen en el output stream
            response.getOutputStream().write(report.getPhoto());
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}