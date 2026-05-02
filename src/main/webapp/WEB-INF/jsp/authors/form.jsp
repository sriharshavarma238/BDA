<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Author Form</title>
    <link rel="stylesheet" href="<c:url value='/css/app.css'/>">
</head>
<body>
<div class="page-shell">
    <div class="topbar">
        <div class="brand">Author Form</div>
        <div class="nav-links">
            <a href="<c:url value='/authors'/>">Back to Authors</a>
        </div>
    </div>

    <div class="card">
        <c:if test="${not empty errorMessage}">
            <div class="alert error"><c:out value="${errorMessage}"/></div>
        </c:if>

        <c:choose>
            <c:when test="${empty author.id}">
                <c:url var="formAction" value="/authors"/>
            </c:when>
            <c:otherwise>
                <c:url var="formAction" value="/authors/${author.id}"/>
            </c:otherwise>
        </c:choose>

        <form method="post" action="${formAction}">
            <input type="hidden" name="id" value="<c:out value='${author.id}'/>"/>
            <div class="field-grid">
                <div>
                    <label for="name">Name</label>
                    <input id="name" name="name" value="<c:out value='${author.name}'/>" required>
                </div>
                <div>
                    <label for="email">Email</label>
                    <input id="email" name="email" type="email" value="<c:out value='${author.email}'/>" required>
                </div>
            </div>
            <div>
                <label for="bio">Bio</label>
                <textarea id="bio" name="bio"><c:out value='${author.bio}'/></textarea>
            </div>
            <div>
                <button type="submit">Save Author</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>