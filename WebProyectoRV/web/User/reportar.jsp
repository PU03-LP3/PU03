<%-- 
Pagina princiapl del reporte
Autor:
--%>
<%@ page import="Models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    //Verifcaion de usuario, lor edirije si no esta autorizado
    User user = (User) session.getAttribute("usuario");
    if (user == null) {
        response.sendRedirect("../index.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Reportar Problema Vial - Tacna</title>
    
    <!-- Bootstrap 5.3 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Leaflet CSS -->
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
    <style>
        body {
            background-color: #f9f9f9;
            font-family: 'Segoe UI', sans-serif;
        }

        .navbar {
            background-color: #8B0000;
        }

        .btn-guinda {
            background-color: #8B0000;
            color: white;
        }

        .btn-guinda:hover {
            background-color: #a90000;
        }

        #map {
            height: 300px;
            width: 100%;
            border-radius: 8px;
        }

        label {
            font-weight: bold;
        }
    </style>
</head>
<body>
    <!-- Barra superior -->
    <nav class="navbar navbar-dark mb-4">
        <div class="container-fluid">
            <span href="home.jsp" class="navbar-brand ms-3">Reporte Vial - Tacna</span>
            <a href="../logout" class="btn btn-outline-light me-3">Cerrar Sesión</a>
        </div>
    </nav>

    <div class="container">
        <h3 class="mb-4 text-center">Registrar Nuevo Reporte</h3>

        <% if (request.getParameter("success") != null) { %>
            <div class="alert alert-success"><%= request.getParameter("success") %></div>
        <% } %>

        <% if (request.getParameter("error") != null) { %>
            <div class="alert alert-danger"><%= request.getParameter("error") %></div>
        <% } %>

        <form method="post" action="../submitReport" enctype="multipart/form-data" class="needs-validation" novalidate>
            <div class="mb-3">
                <label for="title" class="form-label">Título</label>
                <input type="text" name="title" id="title" class="form-control" required>
                <div class="invalid-feedback">Campo requerido</div>
            </div>

            <div class="mb-3">
                <label for="problemType" class="form-label">Tipo de problema</label>
                <select name="problemType" id="problemType" class="form-select" required>
                    <option value="">Selecciona una opción</option>
                    <option value="1">Hueco</option>
                    <option value="2">Fallo en semáforo</option>
                    <option value="3">Inundación</option>
                    <option value="4">Otro</option>
                </select>
                <div class="invalid-feedback">Campo requerido</div>
            </div>

            <div class="mb-3">
                <label for="description" class="form-label">Descripción</label>
                <textarea name="description" id="description" class="form-control" rows="3" required></textarea>
                <div class="invalid-feedback">Campo requerido</div>
            </div>

            <div class="mb-3">
                <label for="address" class="form-label">Dirección aproximada (opcional)</label>
                <input type="text" name="address" id="address" class="form-control">
            </div>

            <div class="mb-3">
                <label>Ubicación</label>
                <div id="map"></div>
                <input type="hidden" name="latitude" id="latitude" required>
                <input type="hidden" name="longitude" id="longitude" required>
                <div class="invalid-feedback">Selecciona una ubicación</div>
            </div>

            <div class="mb-3">
                <label for="photo" class="form-label">Foto del problema</label>
                <input type="file" name="photo" id="photo" accept="image/*" class="form-control" required>
                <div class="invalid-feedback">Sube una imagen del problema</div>
            </div>

            <button type="submit" class="btn btn-guinda w-100">Enviar Reporte</button>
        </form>
        <div class="mt-4 text-center">
            <a href="home.jsp" class="btn btn-outline-secondary">⬅️ Regresar al Inicio</a>
        </div>
        <hr class="mt-5 mb-4">

        <p class="text-center text-muted small">Sistema de reporte vial para ciudadanos de Tacna, Perú</p>
    </div>

    <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
    <script>
        var tacna = L.latLng(-18.0146, -70.2513);
        var map = L.map('map').setView(tacna, 14);

        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; OpenStreetMap contributors'
        }).addTo(map);

        var marker = L.marker(tacna).addTo(map)
            .bindPopup("Haz clic en el mapa para seleccionar la ubicación")
            .openPopup();

        function updateMarker(lat, lng) {
            marker.setLatLng([lat, lng]);
            document.getElementById("latitude").value = lat;
            document.getElementById("longitude").value = lng;
        }

        map.on('click', function(e) {
            updateMarker(e.latlng.lat, e.latlng.lng);
        });

        (() => {
            'use strict';
            const forms = document.querySelectorAll('.needs-validation');
            Array.from(forms).forEach(form => {
                form.addEventListener('submit', event => {
                    if (!form.checkValidity()) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        })();
    </script>
</body>
</html>