<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Author</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; }
        input[type="text"], textarea { width: 100%; padding: 8px; box-sizing: border-box; }
        .error { color: red; }
        .btn { padding: 10px 15px; background-color: #17a2b8; color: white; border: none; cursor: pointer; border-radius: 4px;}
        .btn:hover { background-color: #138496; }
        .back-link { display: block; margin-top: 20px; }
    </style>
</head>
<body>

    <h2>Add New Author</h2>

    <form:form action="/addAuthor" method="post" modelAttribute="author">
        <div class="form-group">
            <label for="name">Name:</label>
            <form:input path="name" id="name" />
            <form:errors path="name" cssClass="error" />
        </div>
        
        <div class="form-group">
            <label for="biography">Biography:</label>
            <form:textarea path="biography" id="biography" rows="4" />
            <form:errors path="biography" cssClass="error" />
        </div>

        <button type="submit" class="btn">Save Author</button>
    </form:form>

    <a href="/books" class="back-link">Back to List</a>

</body>
</html>
