<%--
  Created by IntelliJ IDEA.
  User: yatse
  Date: 1/31/2022
  Time: 4:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jwst" uri="http://by/epam/training" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title" var="title"/>
<fmt:message bundle="${loc}" key="label.authors" var="author"/>
<fmt:message bundle="${loc}" key="label.genres" var="genre"/>
<fmt:message bundle="${loc}" key="label.publishing.house" var="publishingHouse"/>
<fmt:message bundle="${loc}" key="label.publishing.year" var="yearOfPublishing"/>
<fmt:message bundle="${loc}" key="label.button.rus" var="ru"/>
<fmt:message bundle="${loc}" key="label.button.eng" var="en"/>
<fmt:message bundle="${loc}" key="label.download" var="download"/>
<fmt:message bundle="${loc}" key="label.description" var="description"/>
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
    <c:out value="${requestScope.books.picture}"/>
    <c:out value="${requestScope.books.title}"/></br>
    <div>${description}:</div>
    <c:out value="${requestScope.books.description}"/>
</form>

<form>
    <div>${author}:</div>
    <c:forEach var="author" items="${requestScope.authors}">
        <c:out value="${author.firstName}"/>
        <c:out value="${author.lastName}"/>
        <c:out value="${author.fatherName}"/>
    </c:forEach>
</form>

<form>
    <div>${genre}:</div>
    <c:forEach var="genre" items="${requestScope.genres}">
        <c:out value="${genre.title}"/>
    </c:forEach>
</form>

<form>
    <div>${publishingHouse}:</div>
    <c:forEach var="publishingHouse" items="${requestScope.publishingHouses}">
        <c:out value="${publishingHouse.title}"/>
        <div>${yearOfPublishing}:</div>
        <c:out value="${publishingHouse.yearOfPublishing}"/>
    </c:forEach>
</form>

<form>
    <input type="hidden" name="command" value="download">
    <input type="hidden" name="download" value="${requestScope.books.file}">
    <input class="button" type="submit" value="${download}"><br>
</form>

<form>
    <input type="hidden" name="command" value="go_to_main_auth_page">
    <input type="submit" value="${exit}">
</form>

</body>
</html>
