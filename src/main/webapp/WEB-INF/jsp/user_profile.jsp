<%--
  Created by IntelliJ IDEA.
  User: yatse
  Date: 2/10/2022
  Time: 12:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jwst" uri="http://by/epam/training" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.button.rus" var="ru"/>
<fmt:message bundle="${loc}" key="label.button.eng" var="en"/>
<fmt:message bundle="${loc}" key="label.title" var="title"/>
<fmt:message bundle="${loc}" key="label.name.last" var="last_name"/>
<fmt:message bundle="${loc}" key="label.name.first" var="first_name"/>
<fmt:message bundle="${loc}" key="label.name.father" var="father_name"/>
<fmt:message bundle="${loc}" key="label.login" var="login"/>
<fmt:message bundle="${loc}" key="label.mobile" var="mobile"/>
<fmt:message bundle="${loc}" key="label.email" var="email"/>
<fmt:message bundle="${loc}" key="label.birthday" var="birthday"/>
<fmt:message bundle="${loc}" key="label.exit" var="exit"/>
<html>
<head>
    <title>${title}</title>
</head>
<body>

<form class = "ru" action="controller" method="post">
    <input type="hidden" name="command" value="set_locale"/>
    <input type="hidden" name="local" value="ru_RU"/>
    <input type="submit" value="${ru}">
</form>
<form class = "en" action="controller" method="post">
    <input type="hidden" name="command" value="set_locale"/>
    <input type="hidden" name="local" value="en_US"/>
    <input type="submit" value="${en}">
</form>

<form>
    <div>${login}:</div>
    <c:out value="${requestScope.user.login}"/>
</form>

<form>
    <div>${last_name}:</div>
    <c:out value="${requestScope.userDetail.lastName}"/>
</form>

<form>
    <div>${first_name}:</div>
    <c:out value="${requestScope.userDetail.firstName}"/>
</form>

<form>
    <div>${father_name}:</div>
    <c:out value="${requestScope.userDetail.fatherName}"/>
</form>

<form>
    <div>${email}:</div>
    <c:out value="${requestScope.userDetail.email}"/>
</form>

<form>
    <div>${mobile}:</div>
    <c:out value="${requestScope.userDetail.mobile}"/>
</form>

<form>
    <div>${birthday}:</div>
    <c:out value="${requestScope.userDetail.birthday}"/>
</form>

<form>
    <input type="hidden" name="command" value="go_to_main_auth_page">
    <input class="button" type="submit" value="${exit}">
</form>

</body>
</html>

