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
<fmt:message bundle="${loc}" key="label.page.next" var="next"/>
<fmt:message bundle="${loc}" key="label.page.previous" var="previous"/>
<fmt:message bundle="${loc}" key="label.button.delete" var="delete"/>
<fmt:message bundle="${loc}" key="label.button.update" var="update"/>
<fmt:message bundle="${loc}" key="label.button.profile" var="profile"/>
<fmt:message bundle="${loc}" key="label.button.add.book" var="add_book"/>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<jwst:welcomeUser text="${welcome}"/>
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
<form class="logout">
    <a href="${pageContext.request.contextPath}/controller?command=logout">${logout}</a>
</form>

<form class="search">
    <input type="hidden" name="command" value="search_book">
    <input type="text" name="search" placeholder="${search}">
    <input class="button" type="submit" value="${search}"><br>
</form>

<form>
    <a href="controller?command=go_to_user_profile_page">${profile}</a>
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
                <a href="controller?command=go_to_book_detail_page&books=${book.id}">${book.title}</a></br>

                <c:if test="${sessionScope.role_id eq 1}">
                    <a href="controller?command=delete_book&books=${book.id}">${delete}</a></br>
                    <a href="controller?command=go_to_update_book_page&books=${book.id}">${update}</a>
                </c:if>
            </td>

            <c:if test="${loops eq index+3}">
                </tr>
            </c:if>
        </c:forEach>
    </table>
</form>
<form>
    <table>

        <c:if test="${requestScope.currentPage ne 1}">
            <tr>
                <a href="controller?command=go_to_main_auth_page&currentPage=${requestScope.currentPage-1}">${previous}</a>
            </tr>
        </c:if>

        <table>
            <tr>
                <c:forEach begin="1" end="${requestScope.numberOfPage}" var="i">
                    <c:choose>
                        <c:when test="${requestScope.currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <a href="controller?command=go_to_main_auth_page&currentPage=${i}">${i}</a>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </table>

        <c:if test="${requestScope.currentPage ne requestScope.numberOfPages}">
            <tr>
                <a href="controller?command=go_to_main_auth_page&currentPage=${requestScope.currentPage+1}">${next}</a>
            </tr>
        </c:if>
    </table>
</form>

<c:if test="${sessionScope.role_id eq 1}">
    <form>
        <a href="controller?command=go_to_adding_book_page">${add_book}</a>
    </form>
</c:if>

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
        padding-left: 100px;
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