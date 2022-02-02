<%--
  Created by IntelliJ IDEA.
  User: yatse
  Date: 2/1/2022
  Time: 9:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jwst" uri="http://by/epam/training" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title" var="title"/>
<fmt:message bundle="${loc}" key="label.button.rus" var="ru"/>
<fmt:message bundle="${loc}" key="label.button.eng" var="en"/>
<fmt:message bundle="${loc}" key="label.button.update" var="update"/>
<fmt:message bundle="${loc}" key="label.update.book.title" var="book_title"/>
<fmt:message bundle="${loc}" key="label.update.book.description" var="description"/>
<fmt:message bundle="${loc}" key="label.update.book.age.limit" var="age_limit"/>
<fmt:message bundle="${loc}" key="label.update.genre" var="genre"/>
<fmt:message bundle="${loc}" key="label.update.author.name" var="author_name"/>
<fmt:message bundle="${loc}" key="label.update.publishing.house" var="publishing_house"/>
<fmt:message bundle="${loc}" key="label.update.exit" var="exit"/>
<html>
<head>
    <title>${title}</title>
</head>
<body>

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
<form>
    <input type="hidden" name="command" value="update_book">
    <table>
        <tr>
            <td>${book_title}:</td>
            <div>${requestScope.updating}</div>
            <td>
                <input type="text" name="title" value="${requestScope.book.title}">
            </td>
            <td><input type="hidden" name="book" value="${requestScope.book.id}"></td>
        </tr>
        <tr>
            <td>${age_limit}:</td>
            <td><input type="text" name="age_limit" value="${requestScope.book.ageLimit}"></td>
        </tr>
        <tr>
            <td>${author_name}:</td>
            <c:forEach var="author" items="${requestScope.authors}">
                <td><input type="text" name="${author.id}last_name" value="${author.lastName}"></td>
                <td><input type="text" name="${author.id}first_name" value="${author.firstName}"></td>
                <td><input type="text" name="${author.id}father_name" value="${author.fatherName}"></td>
            </c:forEach>
        </tr>
        <tr>
            <td>${genre}:</td>
            <c:forEach var="genre" items="${requestScope.genres}">
                <td><input type="text" name="${genre.id}title" value="${genre.title}"></td>
            </c:forEach>
        </tr>
        <tr>
            <td>${publishing_house}:</td>
            <c:forEach var="publishingHouse" items="${requestScope.publishingHouse}">
                <td><input type="text" name="${publishingHouse.id}title" value="${publishingHouse.title}"></td>
            </c:forEach>
        </tr>
        <tr>
            <td>${description}:</td>
            <td><input type="text" name="description" value="${requestScope.book.description}"></td>
        </tr>
    </table>
    <input class="button" type="submit" value="${update}">
</form>

<form>
    <input type="hidden" name="command" value="go_to_main_auth_page">
    <input class="button" type="submit" value="${exit}">
</form>

</body>
</html>
