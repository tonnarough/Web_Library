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
<c:set var="message" scope="page" value="${2*2000}" />
<c:out value="${message}"/>
<%
    String regInfo = request.getParameter("Registration info");
    if(regInfo != null){
%>
<h2>
    <%
        out.println(regInfo);
        }
    %>
</h2>

<%
    String logInfo = request.getParameter("Login info");
    if(logInfo != null){
%>
<h2>
    <%
            out.println(logInfo);
        }
    %>
</h2>
</body>
</html>
