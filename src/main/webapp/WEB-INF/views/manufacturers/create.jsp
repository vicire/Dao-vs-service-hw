<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Creation</title>
</head>
<body>
<h1>Please, provide manufacturer details</h1>
<h4 style="color: red">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/manufacturers/create">
    Please, fill manufacturer`s brand: <input type="text" name="brand" required>
    and country: <input type="text" name="country" required>
    <button type="submit">Create</button>
</form>
</body>
</html>
