<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Creation</h1>
<a href="${pageContext.request.contextPath}/drivers/create">Create driver</a>
<a href="${pageContext.request.contextPath}/manufacturers/create">Create manufacturer</a>
<a href="${pageContext.request.contextPath}/cars/create">Create car</a>
<a href="${pageContext.request.contextPath}/cars/drivers/add">Add driver to car</a>
<h1>Tables</h1>
<a href="${pageContext.request.contextPath}/drivers">All drivers</a>
<a href="${pageContext.request.contextPath}/manufacturers">All manufacturers</a>
<a href="${pageContext.request.contextPath}/cars">All cars</a>
<a href="${pageContext.request.contextPath}/cars/current">My current cars</a>
</body>
</html>
