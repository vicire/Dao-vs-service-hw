<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Creation</title>
</head>
<body>
<h1>Please, provide driver details</h1>
<form method="post" action="${pageContext.request.contextPath}/driver/creation">
    Please, fill driver`s name: <input type="text" name="name">
    and license number: <input type="text" name="licenseNumber">
    <button type="submit">Create</button>
</form>
</body>
</html>
