<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Books</title>
    <link rel="stylesheet" href="<c:url value='/css/app.css'/>">
</head>
<body>
<div class="page-shell">
    <div class="topbar">
        <div class="brand">Books</div>
        <div class="nav-links">
            <a href="<c:url value='/'/>">Home</a>
            <a href="<c:url value='/authors'/>">Authors</a>
            <a href="<c:url value='/books/new'/>">Add Book</a>
        </div>
    </div>

    <c:if test="${not empty successMessage}">
        <div class="alert success"><c:out value="${successMessage}"/></div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="alert error"><c:out value="${errorMessage}"/></div>
    </c:if>

    <div class="card">
        <h2 class="section-title">Joined book details</h2>
        <p class="muted">This table comes from the custom inner join query in the repository layer.</p>
        <div class="table-wrap">
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>ISBN</th>
                    <th>Year</th>
                    <th>Price</th>
                    <th>Author</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${bookDetails}" var="book">
                    <tr>
                        <td><c:out value="${book.id}"/></td>
                        <td><c:out value="${book.title}"/></td>
                        <td><c:out value="${book.isbn}"/></td>
                        <td><c:out value="${book.publishedYear}"/></td>
                        <td><c:out value="${book.price}"/></td>
                        <td><c:out value="${book.authorName}"/></td>
                        <td class="actions">
                            <a class="button secondary" href="<c:url value='/books/${book.id}/edit'/>">Edit</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>