<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Creation</title>
</head>
<body>
<h1>Please, provide driver details</h1>
<h4 style="color: red">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/drivers/create">
    Please, fill driver`s name: <input type="text" name="name" required>
    and license number: <input type="text" name="licenseNumber" required>
    <button type="submit">Create</button>
</form>
</body>
</html>
