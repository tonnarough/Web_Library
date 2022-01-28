<%--
  Created by IntelliJ IDEA.
  User: yatse
  Date: 12.01.2022
  Time: 4:49
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jwst" uri="http://by/epam/training" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.welcome" var="welcome"/>
<fmt:message bundle="${loc}" key="label.logout" var="logout"/>
<fmt:message bundle="${loc}" key="label.title" var="title"/>
<fmt:message bundle="${loc}" key="label.button.rus" var="ru"/>
<fmt:message bundle="${loc}" key="label.button.eng" var="en"/>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<jwst:welcomeUser text="${welcome}"/>

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
    <input type="hidden" name="command" value="main">
    <input class="button" type="submit" value="Press me pls"><br>
    <a href="${pageContext.request.contextPath}/controller?command=logout">${logout}</a>
</form>
</body>
</html>
