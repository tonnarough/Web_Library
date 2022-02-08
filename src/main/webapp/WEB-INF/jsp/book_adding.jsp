<%--
  Created by IntelliJ IDEA.
  User: yatse
  Date: 2/5/2022
  Time: 7:11 PM
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
<fmt:message bundle="${loc}" key="label.book.title" var="book_title"/>
<fmt:message bundle="${loc}" key="label.book.description" var="description"/>
<fmt:message bundle="${loc}" key="label.book.age.limit" var="age_limit"/>
<fmt:message bundle="${loc}" key="label.book.number.of.page" var="number_of_page"/>
<fmt:message bundle="${loc}" key="label.name.last" var="last_name"/>
<fmt:message bundle="${loc}" key="label.name.first" var="first_name"/>
<fmt:message bundle="${loc}" key="label.name.father" var="father_name"/>
<fmt:message bundle="${loc}" key="label.genre" var="genre"/>
<fmt:message bundle="${loc}" key="label.publishing.house" var="publishing_house"/>
<fmt:message bundle="${loc}" key="label.publishing.year" var="publishing_year"/>
<fmt:message bundle="${loc}" key="label.exit" var="exit"/>
<fmt:message bundle="${loc}" key="label.add" var="add"/>
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
<form method="post" action="controller?command=book_adding" enctype="multipart/form-data">

    <input type="file" name="picture">

    <input type="file" name="book_file">

    <input type="text" name="book_title" placeholder="${book_title}">

    <input type="text" name="age_limit" placeholder="${age_limit}">

    <input type="text" name="number_of_page" placeholder="${number_of_page}">

    <input type="text" name="genre_title" placeholder="${genre}">

    <input type="text" name="author_last_name" placeholder="${last_name}">
    <input type="text" name="author_first_name" placeholder="${first_name}">
    <input type="text" name="author_father_name" placeholder="${father_name}">

    <input type="text" name="publishing_title" placeholder="${publishing_house}">

    <input type="text" name="year_of_publishing" placeholder="${publishing_year}">

    <input type="text" name="description" placeholder="${description}">

    <input class="button" type="submit" value="${add}">
</form>

<form>
    <input type="hidden" name="command" value="go_to_main_auth_page">
    <input class="button" type="submit" value="${exit}">
</form>

</body>
</html>

