<%--
  Created by IntelliJ IDEA.
  User: yatse
  Date: 2/1/2022
  Time: 2:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jwst" uri="http://by/epam/training" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.search" var="search_title"/>
<fmt:message bundle="${loc}" key="label.button.rus" var="ru"/>
<fmt:message bundle="${loc}" key="label.button.eng" var="en"/>
<html>
<head>
    <title>${search_title} ${requestScope.Parameter}:</title>
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

</body>
</html>
