<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Book</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; }
        input[type="text"], select { width: 100%; padding: 8px; box-sizing: border-box; }
        .error { color: red; }
        .btn { padding: 10px 15px; background-color: #28a745; color: white; border: none; cursor: pointer; border-radius: 4px;}
        .btn:hover { background-color: #218838; }
        .back-link { display: block; margin-top: 20px; }
    </style>
</head>
<body>

    <h2>Add New Book</h2>

    <c:if test="${not empty errorMessage}">
        <div class="error" style="margin-bottom: 15px;">${errorMessage}</div>
    </c:if>

    <form:form action="/addBook" method="post" modelAttribute="book">
        <div class="form-group">
            <label for="title">Title:</label>
            <form:input path="title" id="title" />
            <form:errors path="title" cssClass="error" />
        </div>
        
        <div class="form-group">
            <label for="isbn">ISBN:</label>
            <form:input path="isbn" id="isbn" />
            <form:errors path="isbn" cssClass="error" />
        </div>

        <div class="form-group">
            <label for="author">Author:</label>
            <form:select path="author" id="author">
                <form:option value="" label="-- Select Author --"/>
                <form:options items="${authors}" itemValue="id" itemLabel="name"/>
            </form:select>
            <form:errors path="author" cssClass="error" />
        </div>

        <button type="submit" class="btn">Save Book</button>
    </form:form>

    <a href="/books" class="back-link">Back to List</a>

</body>
</html>
