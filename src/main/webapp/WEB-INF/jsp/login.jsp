<%--
  Created by IntelliJ IDEA.
  User: yatse
  Date: 11.01.2022
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
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
<div class="loginbox">
    <h1>${login}</h1>

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
        <form method="post" action="controller">
            <label>
                <input type="hidden" name="command" value="sign_in">
                <input id="login_id" type="text" required placeholder="${login}" name="login"><br>
                <input id="password_id" type="password" required placeholder="${password}" name="password"><br>
                <input id="sign_in" class="button" type="submit" value="${log_button}"><br>
            </label><br>
        </form>
    </div>
</div>
</body>
</html>

<style>

    body {
        margin: 0;
        padding: 0;
        background: lightblue;
        background-size: cover;
        font-family: sans-serif;
    }

    .loginbox {
        width: 320px;
        height: 420px;
        background-color: rgba(0, 0, 0, 0.8);
        color: #fff;
        left: 50%;
        top: 50%;
        position: absolute;
        transform: translate(-50%, -50%);
        box-sizing: border-box;
        padding: 70px 30px;
    }

    h1 {
        margin: 0;
        text-align: center;
        padding: 0 0 20px;
        font-size: 22px;
    }

    .formbox{
        width: 100%;
    }

    .formbox p{
        margin: 0;
        padding: 0;
        font-weight: bold;
    }

    .formbox input{
        width: 100%;
        margin-bottom: 20px;
    }

    .formbox input[type="text"], .formbox input[type="password"]{
        border: 0;
        border-bottom: 1px solid #fff;
        background: transparent;
        outline: none;
        height: 40px;
        color: #fff;
        font-size: 15px;
    }

    .formbox input[type="text"]:focus, .formbox input[type="password"]:focus{
        border-bottom: lightblue;
    }

    .formbox input[type="submit"]{
        border: 0;
        outline: 0;
        background: lightblue;
        height: 40px;
        border-radius: 20px;
        font-size: 18px;
        color: #fff;
    }

    .formbox input[type="submit"]:hover{
        cursor: pointer;
        background: #fff;
        color: #000;
    }

    .formbox a{
        text-decoration: none;
        font-size: 12px;
        line-height: 20px;
        color: darkgray;
    }

    .formbox a:hover{
        color: lightblue;
    }

    form.ru [type="submit"], form.en [type="submit"] {
        text-decoration: none;
        outline: none;
        border: none;
        background-color: lightblue;
        position: center;
        bottom: 15px;
    }
    #ru:hover, #en:hover {
        opacity: 0.7;
    }
</style>
