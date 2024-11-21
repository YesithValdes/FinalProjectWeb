<%-- 
    Document   : editBook
    Created on : 21/11/2024, 9:40:14 a. m.
    Author     : Alexis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="entities.Books" %>
<%
    Books book = (Books) request.getAttribute("book");
    if (book == null) {
        response.sendRedirect("DashboardServlet");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Libro</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
</head>
<body>
    <header>
        <h2 class="text-center my-4">Editar Libro</h2>
    </header>
    <div class="container">
        <form action="EditBookServlet" method="POST">
            <input type="hidden" name="book_id" value="${book.bookId}">
            <div class="mb-3">
                <label for="title" class="form-label">Título</label>
                <input type="text" class="form-control" name="title" value="${book.title}" required>
            </div>
            <div class="mb-3">
                <label for="year_published" class="form-label">Año de Publicación</label>
                <input type="number" class="form-control" name="year_published" value="${book.yearPublished}" min="1900" max="2024" required>
            </div>
            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
            <a href="DashboardServlet" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
</body>
</html>

