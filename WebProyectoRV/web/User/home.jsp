<%-- 
Pagina princiapl del usuario
Autor:
--%>

<%@page import="java.util.List"%>
<%@ page import="Models.User" %>
<%@ page import="Models.Report" %>
<%@ page import="ModelsDAO.ReportDAO" %>
<%@ page import="ModelsDAO.ReportStatusDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    //Verifica que el usuariosea correcto, caso contrario lo devuelve
    User user = (User) session.getAttribute("usuario");
    if (user == null) {
        response.sendRedirect("../index.jsp");
        return;
    }

    //Carga de datos sobre los reportes
    ReportDAO reportDAO = new ReportDAO();
    ReportStatusDAO statusDAO = new ReportStatusDAO();
    List<Report> reports = reportDAO.getAllReportsByUser(user.getId());
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mis Reportes - Tacna</title>
    
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f9f9f9;
            font-family: 'Segoe UI', sans-serif;
        }

        .navbar {
            background-color: #8B0000;
        }

        footer {
            margin-top: 40px;
            text-align: center;
            font-size: 0.9rem;
            color: #666;
        }

        .card-report {
            display: flex;
            flex-direction: row;
            gap: 15px;
            border-left: 5px solid #8B0000;
        }

        .report-info {
            flex: 1;
        }

        .report-img {
            flex: 1;
            max-width: 300px;
            height: auto;
        }

        .btn-guinda {
            background-color: #8B0000;
            color: white;
        }

        .btn-guinda:hover {
            background-color: #a90000;
        }
    </style>
</head>
<body>
    <!-- Barra superior -->
    <nav class="navbar navbar-dark mb-4">
        <div class="container-fluid">
            <span href="home.jsp" class="navbar-brand ms-3">Mis Reportes - Tacna</span>
            <a href="../logout" class="btn btn-outline-light me-3">Cerrar Sesión</a>
        </div>
    </nav>

    <div class="container">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h4>Hola, <%= user.getFullName() %></h4>
            <a href="reportar.jsp" class="btn btn-guinda">➕ Nuevo Reporte</a>
        </div>
             <!-- 6. LISTADO DE REPORTES -->
        <% if (reports.isEmpty()) { %>
        <!-- Mensaje cuando no hay reportes -->
            <div class="alert alert-info">No has realizado ningún reporte aún.</div>
        <% } else { %>
            <% for (Report r : reports) { 
                String statusName = statusDAO.getStatusName(r.getStatusId()); %>
                <!-- 7. TARJETA DE REPORTE INDIVIDUAL -->
                <div class="card shadow-sm mb-3 p-3 card-report">
                    <!-- Información del reporte -->
                    <div class="report-info">
                        <h5 class="card-title"><%= r.getTitle() %></h5>
                        <p class="card-text"><%= r.getDescription() %></p>
                        <p><strong>Ubicación:</strong> <%= r.getLatitude() %>, <%= r.getLongitude() %></p>
                        <p><strong>Estado:</strong> <%= statusName %></p>
                        <p class="text-muted"><small>Fecha: <%= r.getReportedAt() %></small></p>
                    </div>
                    <!-- 8. MANEJO DE IMÁGENES -->
                    <div class="report-img">
                        <% if (r.getPhoto() != null) { %> <!-- Muestra imagen a través del ImageServlet -->
                            <img src="../ImageServlet?reportId=<%= r.getId() %>" alt="Foto del reporte" class="img-fluid rounded">
                        <% } else { %>
                            <p class="text-muted">Sin foto adjunta</p>
                        <% } %>
                    </div>
                </div>
            <% } %>
        <% } %>

    </div>

    <footer class="mt-5">
        &copy; Sistema de Reporte Vial - Tacna, Perú
    </footer>
</body>
</html>