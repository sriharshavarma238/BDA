<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link rel="stylesheet" href="<c:url value='/css/app.css'/>">
</head>
<body>
<div class="page-shell">
    <div class="card">
        <h1 class="section-title">Something went wrong</h1>
        <p class="muted"><c:out value="${errorMessage}"/></p>
        <a class="button primary" href="<c:url value='/'/>">Return Home</a>
    </div>
</div>
</body>
</html>