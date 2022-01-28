<%--
  Created by IntelliJ IDEA.
  User: yatse
  Date: 11.01.2022
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.login" var="login"/>
<fmt:message bundle="${loc}" key="label.password" var="password"/>
<fmt:message bundle="${loc}" key="label.registration" var="registration"/>
<fmt:message bundle="${loc}" key="label.title" var="title"/>
<fmt:message bundle="${loc}" key="label.name.last" var="last_name"/>
<fmt:message bundle="${loc}" key="label.name.first" var="first_name"/>
<fmt:message bundle="${loc}" key="label.name.father" var="father_name"/>
<fmt:message bundle="${loc}" key="label.email" var="email"/>
<fmt:message bundle="${loc}" key="label.mobile" var="mobile"/>
<fmt:message bundle="${loc}" key="label.birthday" var="birthday"/>
<fmt:message bundle="${loc}" key="label.button.rus" var="ru"/>
<fmt:message bundle="${loc}" key="label.button.eng" var="en"/>
<fmt:message bundle="${loc}" key="label.button.registration" var="reg_button"/>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<div>
    <h1>${registration}</h1><br>

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

    <form method="post" action="controller">
        <label>
            <input type="hidden" name="command" value="registration">
            <input type="text" required placeholder="${login}" name="login"><br>
            <input type="password" required placeholder="${password}" name="password"><br>
            <input type="text" required placeholder="${last_name}" name="last_name"><br>
            <input type="text" required placeholder="${first_name}" name="first_name"><br>
            <input type="text" required placeholder="${father_name}" name="father_name"><br>
            <input type="text" required placeholder="${email}" name="email"><br>
            <input type="text" required placeholder="${mobile}" name="mobile"><br>
            <input type="date" required placeholder="${birthday}" name="birthday"><br>
            <input class="button" type="submit" value="${reg_button}"><br>
        </label><br>
    </form>
</div>
</body>
</html>
