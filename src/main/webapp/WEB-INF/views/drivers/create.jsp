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
    , license number: <input type="text" name="licenseNumber" required>
    <p> Please, enter your login: <input type="text" name="login" required>
    and password: <input type="password" name="password" required>
    Please, repeat your password : <input type="password" name="passwordRepeat" required>
    <button type="submit">Create</button>
</form>
</body>
</html>
