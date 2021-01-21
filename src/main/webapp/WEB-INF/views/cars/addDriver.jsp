<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add driver to car</title>
</head>
<body>
<h1>Please, provide car and driver id</h1>
<h4 style="color: red">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/cars/addDriver">
    Please, enter car id: <input type="number" name="car_id" required>
    and driver id: <input type="number" name="driver_id" required>
    <button type="submit">Create</button>
</form>
</body>
</html>
