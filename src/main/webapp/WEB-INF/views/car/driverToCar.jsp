<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add driver to car</title>
</head>
<body>
<h1>Please, provide car and driver id</h1>
<form method="post" action="${pageContext.request.contextPath}/car/driverToCar">
    Please, enter car id: <input type="text" name="car_id">
    and driver id: <input type="text" name="driver_id">
    <button type="submit">Create</button>
</form>
</body>
</html>
