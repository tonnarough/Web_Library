<%--
  Created by IntelliJ IDEA.
  User: yatse
  Date: 20.01.2022
  Time: 3:55
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jwst" uri="http://by/epam/training" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.subscription" var="subscription"/>
<fmt:message bundle="${loc}" key="label.pay" var="pay"/>
<fmt:message bundle="${loc}" key="label.cardholder" var="cardholder"/>
<fmt:message bundle="${loc}" key="label.title" var="title"/>
<fmt:message bundle="${loc}" key="label.button.rus" var="ru"/>
<fmt:message bundle="${loc}" key="label.button.eng" var="en"/>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<div>
    <h1>${subscription}</h1>

    <form action="controller" method="post">
        <input type="hidden" name="command" value="set_locale"/>
        <input type="hidden" name="local" value="ru_RU"/>
        <input type="submit" value="${ru}">
    </form>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="set_locale"/>
        <input type="hidden" name="local" value="en_US"/>
        <input type="submit" value="${en}">
    </form>

        <form method="post" action="controller">
            <label>
                <input type="hidden" name="command" value="subscription"/>
                <select name="subscriptionType">
                    <c:forEach var="desc" items="${requestScope.subscriptionTypes}">
                        <option>${desc.description}: ${desc.price}</option><br/>
                    </c:forEach>
                </select><br/>

                <input name="credit_card_number" type="number" maxlength="12" placeholder="0000-0000-0000-0000"><br/>
                <input name="date" type="date" maxlength="4">
                <input name="cardholder_name" type="text" placeholder="${cardholder}">
                <input name="cvv" type="number" maxlength="3" placeholder="CVV"><br>
                <input id="buy" class="button" type="submit" value="${pay}"><br>
            </label><br>
        </form>

</div>
</body>
</html>
