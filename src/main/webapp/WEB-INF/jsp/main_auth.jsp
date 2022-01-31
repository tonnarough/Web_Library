<%--
  Created by IntelliJ IDEA.
  User: yatse
  Date: 12.01.2022
  Time: 4:49
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
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
<fmt:message bundle="${loc}" key="label.button.search" var="search"/>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<jwst:welcomeUser text="${welcome}"/>
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
<form class = "logout">
    <a href="${pageContext.request.contextPath}/controller?command=logout">${logout}</a>
</form>

<form class="search">
    <input type="hidden" name="command" value="search_book_command">
    <input type="text" name="search" placeholder="${search}">
    <input class="button" type="submit" value="${search}"><br>
</form>

<form>

    <table>
        <c:forEach var="book" items="${requestScope.books}" varStatus="loop">

            <c:set var="loops" scope="page" value="${loop.getIndex()}"/>
            <c:if test="${loops eq 0 || loops % 3 eq 0}">
                <c:set var="index" scope="page" value="${loops}"/>
                <tr>
            </c:if>
            <td>
                    ${book.picture} <br>
                    <a href="controller?command=go_to_book_detail_page&books=${book.id}">${book.title}</a>
            </td>
            <c:if test="${loops eq index+3}">
                </tr>
            </c:if>
        </c:forEach>
    </table>
</form>

</body>

</html>

<style>
    body {
        background-color: lightblue;
    }
    p {
        font-size: 20px;
        font-weight: 600;
    }
    form.logout, p {
        text-align: right;
    }
    form.logout a {
        text-decoration: none;
        border: none;
        outline: none;
        color: black;
        font-weight: 600;
    }
    form.search {
        text-align: center;
    }
    form.search [type="text"] {
        width: 365px;
    }
    form.ru [type="submit"], form.en [type="submit"] {
        text-decoration: none;
        outline: none;
        border: none;
        background-color: lightblue;
        position: relative;
        bottom: 25px;
    }
    #ru:hover, #en:hover {
        opacity: 0.7;
    }
    table {
        background-color: lightblue;

    }
    table a {
        text-decoration: none;
        outline: none;
        color: black;
    }
    td {
        border-bottom: 2px solid black;
        border-right: 2px solid black;
        text-align: center;
    }
</style>