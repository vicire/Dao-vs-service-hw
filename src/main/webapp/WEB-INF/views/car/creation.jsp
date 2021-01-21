<%--
  Created by IntelliJ IDEA.
  User: savia
  Date: 20/01/2021
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
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
    and manufacturer id: <input type="text" name="manufacturer_id" required>
    <button type="submit">Create</button>
</form>
</body>
</html>
