<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Creation</title>
</head>
<body>
<h1>Please, provide cars details</h1>
<h4 style="color: red">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/car/creation">
    Please, fill car`s model: <input type="text" name="model" required>
    and manufacturer id: <input type="number" name="manufacturer_id" required>
    <button type="submit">Create</button>
</form>
</body>
</html>
