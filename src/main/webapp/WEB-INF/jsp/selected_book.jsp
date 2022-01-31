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
<html>
<head>
    <title>${title}</title>
</head>
<body>

    <form>
        <table>
            <td>
                <c:out value="${requestScope.books.picture}"/>
                <c:out value="${requestScope.books.title}"/>
            </td>
        </table>
    </form>

</body>
</html>
