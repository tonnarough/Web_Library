<%--
  Created by IntelliJ IDEA.
  User: yatse
  Date: 11.01.2022
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.link.login" var="login"/>
<fmt:message bundle="${loc}" key="label.link.registration" var="registration"/>
<fmt:message bundle="${loc}" key="label.link.rusbutton" var="ru"/>
<fmt:message bundle="${loc}" key="label.link.engbutton" var="en"/>

<html>
<head>
    <title>Main</title>
</head>
<body>
<div class="form">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="set_locale"/>
        <input type="hidden" name="local" value="ru_RU"/>
        <input type="submit" value="${ru}">
    </form>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="set_locale"/>
        <input type="hidden" name="local" value="en_US"/>
        <input type="submit" value="${en}">
    </form>
    <form>
        <a href="controller?command=GO_TO_LOGIN_PAGE">${login}</a>
        <br>
        <a href="controller?command=GO_TO_REGISTRATION_PAGE">${registration}</a>
    </form>
</div>
</body>
</html>
