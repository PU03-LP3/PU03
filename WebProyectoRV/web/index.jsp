<%-- 
Pagina prinicpal de iniciar sesiony  registrarse
--%>

<%@page import="java.sql.SQLException"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Models.User" %>
<%@ page import="ModelsDAO.UserDAO" %>
<%@ page import="java.security.MessageDigest" %>
<%@ page import="java.security.NoSuchAlgorithmException" %>

<%
    // Procesar registro si se envió el formulario
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String action = request.getParameter("action");
        
        if ("register".equals(action)) {
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            try {
                //sVerificar si el usuario ya existe
                UserDAO userDAO = new UserDAO();
                if (userDAO.getUserByEmail(email) != null) {
                    response.sendRedirect("index.jsp?error=El+correo+ya+está+registrado");
                    return;
                }
                
                //Hash de la clave
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hashBytes = md.digest(password.getBytes());
                StringBuilder sb = new StringBuilder();
                for (byte b : hashBytes) {
                    sb.append(String.format("%02x", b));
                }
                String hashedPassword = sb.toString();
                
                // Crear nuevo usuario (no admin)
                User newUser = new User();
                newUser.setFullName(fullName);
                newUser.setEmail(email);
                newUser.setPassword(hashedPassword);
                newUser.setRole("CITIZEN");
                
                // Guardar usuario en la base de datos 
                userDAO.createUser(newUser);
                
                response.sendRedirect("index.jsp?success=Registro+exitoso.+Ahora+puedes+iniciar+sesión");
                return;
                
            } catch (NoSuchAlgorithmException | SQLException | ClassNotFoundException e) {
                response.sendRedirect("index.jsp?error=Error+en+el+registro");
                return;
            }
        }
    }

    // Redirección si ya está logueado
    User user = (User) session.getAttribute("usuario");
    if (user != null) {
        response.sendRedirect("User/home.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión - Tacna</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        body {
            background-color: #f9f9f9;
            font-family: 'Segoe UI', sans-serif;
        }

        .login-container {
            max-width: 400px;
            margin: 80px auto;
            padding: 30px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
        }

        .login-header {
            background-color: #8B0000;
            color: white;
            padding: 15px;
            border-radius: 10px 10px 0 0;
            text-align: center;
        }

        .form-control:focus {
            border-color: #8B0000;
            box-shadow: 0 0 0 0.2rem rgba(139, 0, 0, 0.25);
        }

        .btn-guinda {
            background-color: #8B0000;
            color: white;
        }

        .btn-guinda:hover {
            background-color: #a90000;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #8B0000;
            text-decoration: none;
        }

        .back-link:hover {
            text-decoration: underline;
        }
        
        .tab-content {
            padding: 20px 0;
        }
        
        .nav-tabs .nav-link {
            color: #8B0000;
        }
        
        .nav-tabs .nav-link.active {
            color: white;
            background-color: #8B0000;
            border-color: #8B0000;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="login-header">
            <h4>Bienvenido al Sistema de Reporte Vial</h4>
        </div>

        <% if (request.getParameter("error") != null) { %>
            <div class="alert alert-danger mt-3">
                <%= request.getParameter("error") %>
            </div>
        <% } %>
        
        <% if (request.getParameter("success") != null) { %>
            <div class="alert alert-success mt-3">
                <%= request.getParameter("success") %>
            </div>
        <% } %>

        <ul class="nav nav-tabs" id="authTabs" role="tablist">
            <li class="nav-item" role="presentation">
                <button class="nav-link active" id="login-tab" data-bs-toggle="tab" data-bs-target="#login" type="button" role="tab">Iniciar Sesión</button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link" id="register-tab" data-bs-toggle="tab" data-bs-target="#register" type="button" role="tab">Registrarse</button>
            </li>
        </ul>
        
        <div class="tab-content" id="authTabsContent">
            <div class="tab-pane fade show active" id="login" role="tabpanel">
                <form method="post" action="login" class="mt-3">
                    <div class="mb-3">
                        <label for="email" class="form-label">Correo Electrónico</label>
                        <input type="email" name="email" id="email" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label">Contraseña</label>
                        <input type="password" name="password" id="password" class="form-control" required>
                    </div>

                    <button type="submit" class="btn btn-guinda w-100">Iniciar Sesión</button>
                </form>
            </div>
            
            <div class="tab-pane fade" id="register" role="tabpanel">
                <form method="post" action="index.jsp" class="mt-3">
                    <input type="hidden" name="action" value="register">
                    
                    <div class="mb-3">
                        <label for="fullName" class="form-label">Nombre Completo</label>
                        <input type="text" name="fullName" id="fullName" class="form-control" required>
                    </div>
                    
                    <div class="mb-3">
                        <label for="regEmail" class="form-label">Correo Electrónico</label>
                        <input type="email" name="email" id="regEmail" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label for="regPassword" class="form-label">Contraseña</label>
                        <input type="password" name="password" id="regPassword" class="form-control" required minlength="6">
                    </div>

                    <button type="submit" class="btn btn-guinda w-100">Registrarse</button>
                </form>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>