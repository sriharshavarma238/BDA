<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book Form</title>
    <link rel="stylesheet" href="<c:url value='/css/app.css'/>">
</head>
<body>
<div class="page-shell">
    <div class="topbar">
        <div class="brand">Book Form</div>
        <div class="nav-links">
            <a href="<c:url value='/books'/>">Back to Books</a>
        </div>
    </div>

    <div class="card">
        <c:if test="${not empty errorMessage}">
            <div class="alert error"><c:out value="${errorMessage}"/></div>
        </c:if>

        <c:choose>
            <c:when test="${empty book.id}">
                <c:url var="formAction" value="/books"/>
            </c:when>
            <c:otherwise>
                <c:url var="formAction" value="/books/${book.id}"/>
            </c:otherwise>
        </c:choose>

        <form method="post" action="${formAction}">
            <input type="hidden" name="id" value="<c:out value='${book.id}'/>"/>
            <div class="field-grid">
                <div>
                    <label for="title">Title</label>
                    <input id="title" name="title" value="<c:out value='${book.title}'/>" required>
                </div>
                <div>
                    <label for="isbn">ISBN</label>
                    <input id="isbn" name="isbn" value="<c:out value='${book.isbn}'/>" required>
                </div>
                <div>
                    <label for="publishedYear">Published Year</label>
                    <input id="publishedYear" name="publishedYear" type="number" value="<c:out value='${book.publishedYear}'/>" required>
                </div>
                <div>
                    <label for="price">Price</label>
                    <input id="price" name="price" type="number" step="0.01" value="<c:out value='${book.price}'/>" required>
                </div>
            </div>
            <div>
                <label for="authorId">Author</label>
                <select id="authorId" name="authorId" required>
                    <option value="">Select an author</option>
                    <c:forEach items="${authors}" var="author">
                        <option value="${author.id}" <c:if test="${book.authorId == author.id}">selected</c:if>>
                            <c:out value="${author.name}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <button type="submit">Save Book</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>