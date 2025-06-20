// Autor: ""

// Controlador para manejar las solicitudes relacionadas con ciudadanos (usuarios no administradores)
package Controller;

import Models.User;
import ModelsDAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class UserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.login(email, password);

            if (user != null) {
                // Verificamos que no sea administrador (opcional si ya no hay admins en el sistema)
                if (user.isCitizen()) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", user);
                    response.sendRedirect("User/home.jsp");
                } else {
                    // Si por alguna razón aún hay admins en la BD
                    response.sendRedirect("index.jsp?error=Acceso+solo+permitido+a+ciudadanos");
                }
            } else {
                response.sendRedirect("index.jsp?error=Correo+o+contraseña+incorrectos");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?error=Error+al+conectar+con+la+base+de+datos");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }
}
