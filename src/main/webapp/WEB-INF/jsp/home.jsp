<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Library Manager</title>
    <link rel="stylesheet" href="<c:url value='/css/app.css'/>">
</head>
<body>
<div class="page-shell">
    <div class="topbar">
        <div class="brand">Library Manager</div>
        <div class="nav-links">
            <a href="<c:url value='/authors'/>">Authors</a>
            <a href="<c:url value='/books'/>">Books</a>
        </div>
    </div>

    <div class="hero">
        <h1>Spring Boot + JSP CRUD demo</h1>
        <p>This application manages authors and books with create, read, and update workflows. It also demonstrates a JPA inner join query and sample data initialization.</p>
        <div>
            <a class="button primary" href="<c:url value='/authors/new'/>">Add Author</a>
            <a class="button secondary" href="<c:url value='/books/new'/>">Add Book</a>
        </div>
    </div>
</div>
</body>
</html>