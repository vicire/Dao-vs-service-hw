<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login page</h1>
<h4 style="color: red">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/login">
    Please, enter your login: <input type="text" name="login" required>
    and password: <input type="password" name="pwd" required>
    <button type="submit">Login</button>
    <p><a href="${pageContext.request.contextPath}/drivers/create">Register driver</a></p>
</form>
</body>
</html>
