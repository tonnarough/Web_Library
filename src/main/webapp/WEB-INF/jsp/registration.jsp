<%--
  Created by IntelliJ IDEA.
  User: yatse
  Date: 11.01.2022
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
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
<fmt:message bundle="${loc}" key="label.button.back" var="back"/>
<fmt:message bundle="${loc}" key="label.mobile" var="mobile"/>
<fmt:message bundle="${loc}" key="label.birthday" var="birthday"/>
<fmt:message bundle="${loc}" key="label.button.rus" var="ru"/>
<fmt:message bundle="${loc}" key="label.button.eng" var="en"/>
<fmt:message bundle="${loc}" key="label.registration" var="reg_button"/>
<link rel="stylesheet" href="style/registration.css">
<%--<script defer src="js/validation_registration.js"></script>--%>

<html>
<head>
    <title>${title}</title>
</head>
<body>
<div class="registrationbox">
    <h1>${registration}</h1><br>

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
        <form id="form" method="post" action="controller">
            <label>
                <input type="hidden" name="command" value="registration">
                <div class="input-control">
                    <input id="login_id" type="text" placeholder="${login}" name="login"><br>
                    <div class="error"></div>
                </div>
                <div class="input-control">
                    <input id="password_id" type="password" placeholder="${password}" name="password"><br>
                    <div class="error"></div>
                </div>
                <div class="input-control">
                    <input id="last_name_id" type="text" placeholder="${last_name}" name="last_name"><br>
                    <div class="error"></div>
                </div>
                <div class="input-control">
                    <input id="first_name_id" type="text" placeholder="${first_name}" name="first_name"><br>
                    <div class="error"></div>
                </div>
                <div class="input-control">
                    <input id="father_name_id" type="text" placeholder="${father_name}" name="father_name"><br>
                    <div class="error"></div>
                </div>
                <div class="input-control">
                    <input id="email_id" type="text" placeholder="${email}" name="email"><br>
                    <div class="error"></div>
                </div>
                <div class="input-control">
                    <input id="mobile_id" type="text" placeholder="${mobile}" name="mobile"><br>
                    <div class="error"></div>
                </div>
                <div class="input-control">
                    <input id="date_id" type="date" placeholder="${birthday}" name="birthday"><br>
                    <div class="error"></div>
                </div>
                <input class="button" type="submit" value="${reg_button}"><br>
                <p class="link">
                    <a href="controller?command=go_to_main_unauth_page">${back}</a>
                </p>
            </label>
        </form>
    </div>
</div>
</body>
</html>
