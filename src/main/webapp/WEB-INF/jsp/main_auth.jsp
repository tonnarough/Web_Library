<%--
  Created by IntelliJ IDEA.
  User: yatse
  Date: 12.01.2022
  Time: 4:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome to webapp</title>
</head>
<body>
<h1 style="color: indianred"> Hello </h1>
<form>
<a href="${pageContext.request.contextPath}/controller?command=logout">Logout</a>
</form>
</body>
</html>
