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
<fmt:message bundle="${loc}" key="label.button.back" var="back"/>
<fmt:message bundle="${loc}" key="label.button.update" var="update"/>
<fmt:message bundle="${loc}" key="label.login" var="login"/>
<fmt:message bundle="${loc}" key="label.mobile" var="mobile"/>
<fmt:message bundle="${loc}" key="label.new.password" var="newPassword"/>
<fmt:message bundle="${loc}" key="label.confirm.new.password" var="confirmNewPassword"/>
<fmt:message bundle="${loc}" key="label.confirm.old.password" var="confirmOldPassword"/>
<fmt:message bundle="${loc}" key="label.email" var="email"/>
<fmt:message bundle="${loc}" key="label.birthday" var="birthday"/>
<fmt:message bundle="${loc}" key="label.exit" var="exit"/>
<link rel="stylesheet" href="style/update_user_profile.css">

<html>
<head>
    <title>${title}</title>
</head>
<body>

<div class="userprofilebox">
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
                <input type="hidden" name="command" value="update_book">
                <input type="hidden" name="book" value="${requestScope.book.id}">
                    <div>${login}:</div>
                <div>${requestScope.user.login}</div>
                <div class="input-control">
                    <div>${last_name}:</div>
                    <input id="lastName_id" type="text" name="lastName" value="${requestScope.userDetail.lastName}">
                    <div class="error"></div>
                </div>
                <div class="input-control">
                    <div>${first_name}:</div>
                    <input id="firstName_id" type="text" name="firstName" value="${requestScope.userDetail.firstName}">
                    <div class="error"></div>
                </div>
                <div class="input-control">
                    <div>${father_name}:</div>
                    <input id="fatherName_id" type="text" name="fatherName" value="${requestScope.userDetail.fatherName}">
                    <div class="error"></div>
                </div>
                <div class="input-control">
                    <div>${email}:</div>
                    <input id="email_id" type="email" name="email" value="${requestScope.userDetail.email}">
                    <div class="error"></div>
                </div>
                <div class="input-control">
                    <div>${mobile}:</div>
                    <input id="mobile_id" type="text" name="mobile" value="${requestScope.userDetail.mobile}">
                    <div class="error"></div>
                </div>
                    <div>${birthday}:</div>
                    <div>${requestScope.userDetail.birthday}</div>
                <div class="input-control">
                    <div>${newPassword}:</div>
                    <input id="new_password_id" type="password" name="newPassword">
                    <div class="error"></div>
                </div>
                <div class="input-control">
                    <div>${confirmNewPassword}:</div>
                    <input id="confirm_new_password_id" type="password" name="confirmNewPassword">
                    <div class="error"></div>
                </div>
                <div class="input-control">
                    <div>${confirmOldPassword}:</div>
                    <input id="old_password_id" type="password" name="confirmOldPassword">
                    <div class="error"></div>
                </div>
                <input class="button" type="submit" value="${update}">
                <p class="link">
                    <a href="controller?command=go_to_main_auth_page">${back}</a>
                </p>
            </label>
        </form>
    </div>
</div>

</body>
</html>

