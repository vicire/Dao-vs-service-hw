<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All manufacturers</title>
</head>
<body>
<h1>All manufacturers page</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Brand</th>
        <th>Country</th>
    </tr>
    <c:forEach var = "manufacturer" items = "${manufacturers}">
        <tr>
            <td>
                <c:out value = "${manufacturer.id}"/>
            </td>
            <td>
                <c:out value = "${manufacturer.brand}"/>
            </td>
            <td>
                <c:out value = "${manufacturer.country}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/manufacturer/delete?id=${manufacturer.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
