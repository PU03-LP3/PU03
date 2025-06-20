// Autor: ""

// Controlador para cerrar sesión
package Controller;

// Importamos las librerias necesarias
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

// Este servlet maneja el cierre de sesión del usuario
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Obtenemos la sesion actual (el false es pa que no cree una nueva si no existe)
        HttpSession session = request.getSession(false);
        
        //Si hay sesin la invalidamos
        if (session != null) {
            session.invalidate();  //cerrar secion de datos de usuario
        }
        
        //Redirigimos al login 
        response.sendRedirect("index.jsp");  //rta correta
    }
}


//HttpServlet es una clase abstracta de la biblioteca jakarta.servlet.http. Proporciona métodos ya definidos para manejar solicitudes HTTP