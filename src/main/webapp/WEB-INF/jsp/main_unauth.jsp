<%--
  Created by IntelliJ IDEA.
  User: yatse
  Date: 11.01.2022
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.button.login" var="login"/>
<fmt:message bundle="${loc}" key="label.registration" var="registration"/>
<fmt:message bundle="${loc}" key="label.title" var="title"/>
<fmt:message bundle="${loc}" key="label.is.registration" var="isRegistration"/>
<fmt:message bundle="${loc}" key="label.is.registration2" var="here"/>
<fmt:message bundle="${loc}" key="label.welcome.app" var="welcomeTo"/>
<fmt:message bundle="${loc}" key="label.welcome.app2" var="readers"/>
<fmt:message bundle="${loc}" key="label.button.rus" var="ru"/>
<fmt:message bundle="${loc}" key="label.button.eng" var="en"/>
<link rel="stylesheet" href="style/main_unauth.css">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700&display=swap" rel="stylesheet">

<html>
<head>
    <title>${title}</title>
</head>
<body>
<div class="container">

    <p class="welcome">${welcomeTo}<br>
        ${readers}
    </p>

    <form class="ru" action="controller" method="post">
        <input type="hidden" name="command" value="set_locale"/>
        <input type="hidden" name="local" value="ru_RU"/>
        <input type="submit" value="${ru}">
    </form>
    <form class="en" action="controller" method="post">
        <input type="hidden" name="command" value="set_locale"/>
        <input type="hidden" name="local" value="en_US"/>
        <input type="submit" value="${en}">
    </form>
    <div class="formbox">
        <form class="login">
            <input type="hidden" name="command" value="go_to_login_page"/>
            <input type="submit" value="${login}">
        </form>

        <form class="registration">
            <p class="link">${isRegistration}<br>
                <a href="controller?command=go_to_registration_page">${registration} </a>${here}</a>
            </p>
        </form>
    </div>
</div>
</body>
</html>
