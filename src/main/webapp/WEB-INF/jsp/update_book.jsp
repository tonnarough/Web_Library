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
<fmt:message bundle="${loc}" key="label.button.back" var="back"/>
<fmt:message bundle="${loc}" key="label.button.update" var="update"/>
<fmt:message bundle="${loc}" key="label.book.title" var="book_title"/>
<fmt:message bundle="${loc}" key="label.book.description" var="description"/>
<fmt:message bundle="${loc}" key="label.book.age.limit" var="age_limit"/>
<fmt:message bundle="${loc}" key="label.genre" var="genre"/>
<fmt:message bundle="${loc}" key="label.author.name" var="author_name"/>
<fmt:message bundle="${loc}" key="label.publishing.house" var="publishing_house"/>
<link rel="stylesheet" href="style/update_book.css">

<html>
<head>
    <title>${title}</title>
</head>
<body>

<style>
    ::-webkit-scrollbar{
        width: 10px;
    }

    ::-webkit-scrollbar-track{
        border-radius: 5px;
        box-shadow: inset 0 0 10px rgb(23, 42, 48);
    }

    ::-webkit-scrollbar-thumb{
        border-radius: 5px;
        background-color: #ad431a;
    }

    ::-webkit-scrollbar-thumb:hover{
        background-color: #a43718;
    }
</style>

<div class="updatebox">

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
                <div class="input-control">
                    <div>${book_title}:</div>
                    <input id="title_id" type="text" name="title" value="${requestScope.book.title}">
                    <div class="error"></div>
                </div>
                <div class="input-control">
                    <div>${age_limit}:</div>
                    <input id="age_limit_id" type="text" name="age_limit" value="${requestScope.book.ageLimit}">
                    <div class="error"></div>
                </div>
                <div class="input-control">
                    <div>${author_name}:</div>
                    <c:forEach var="author" items="${requestScope.authors}">
                        <input id="last_name_id" type="text" name="${author.id}last_name" value="${author.lastName}">
                        <input id="first_name_id" type="text" name="${author.id}first_name" value="${author.firstName}">
                        <input id="father_name_id" type="text" name="${author.id}father_name" value="${author.fatherName}">
                    </c:forEach>
                    <div class="error"></div>
                </div>
                <div class="input-control">
                    <div>${genre}:</div>
                    <c:forEach var="genre" items="${requestScope.genres}">
                        <input id="genre_id" type="text" name="${genre.id}title" value="${genre.title}">
                    </c:forEach>
                    <div class="error"></div>
                </div>
                <div class="input-control">
                    <div>${publishing_house}:</div>
                    <c:forEach var="publishingHouse" items="${requestScope.publishingHouse}">
                        <input id="publishingHouse_title_id" type="text" name="${publishingHouse.id}title" value="${publishingHouse.title}">
                        <input id="publishingHouse_year_id" type="text" name="${publishingHouse.id}title" value="${publishingHouse.yearOfPublishing}">
                    </c:forEach>
                    <div class="error"></div>
                </div>
                <div class="input-control">
                    <div>${description}:</div>
                    <textarea name="description">
                    ${requestScope.book.description}"
                    </textarea>
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
