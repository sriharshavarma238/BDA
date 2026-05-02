<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Authors</title>
    <link rel="stylesheet" href="<c:url value='/css/app.css'/>">
</head>
<body>
<div class="page-shell">
    <div class="topbar">
        <div class="brand">Authors</div>
        <div class="nav-links">
            <a href="<c:url value='/'/>">Home</a>
            <a href="<c:url value='/books'/>">Books</a>
            <a href="<c:url value='/authors/new'/>">Add Author</a>
        </div>
    </div>

    <c:if test="${not empty successMessage}">
        <div class="alert success"><c:out value="${successMessage}"/></div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="alert error"><c:out value="${errorMessage}"/></div>
    </c:if>

    <div class="card table-wrap">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Bio</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${authors}" var="author">
                <tr>
                    <td><c:out value="${author.id}"/></td>
                    <td><c:out value="${author.name}"/></td>
                    <td><c:out value="${author.email}"/></td>
                    <td><c:out value="${author.bio}"/></td>
                    <td class="actions">
                        <a class="button secondary" href="<c:url value='/authors/${author.id}/edit'/>">Edit</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>