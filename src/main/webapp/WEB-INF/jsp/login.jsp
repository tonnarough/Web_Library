<%--
  Created by IntelliJ IDEA.
  User: yatse
  Date: 11.01.2022
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.login" var="login"/>
<fmt:message bundle="${loc}" key="label.password" var="password"/>
<fmt:message bundle="${loc}" key="label.title" var="title"/>
<fmt:message bundle="${loc}" key="label.button.rus" var="ru"/>
<fmt:message bundle="${loc}" key="label.button.eng" var="en"/>
<fmt:message bundle="${loc}" key="label.button.login" var="log_button"/>
<html>
<head>
    <title>${title}</title>
</head>
<body>
    <div class="singIn">
        <h1>${login}</h1>

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
                <input type="hidden" name="command" value="sign_in">
                <input id="login_id" type="text" required placeholder="${login}" name="login"><br>
                <input id="password_id" type="password" required placeholder="${password}" name="password"><br>
                <input id="sign_in" class="button" type="submit" value="${log_button}"><br>
            </label><br>
        </form>
    </div>
</body>
</html>
