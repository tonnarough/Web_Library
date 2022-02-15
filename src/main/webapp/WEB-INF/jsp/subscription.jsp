<%--
  Created by IntelliJ IDEA.
  User: yatse
  Date: 20.01.2022
  Time: 3:55
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
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
<link rel="stylesheet" href="style/subscription.css">
<%--<script defer src="js/validation_subscription.js"></script>--%>

<html>
<head>
    <title>${title}</title>
</head>
<body>
<div>
    <div class="subscriptionbox">
        <h1>${subscription}</h1>

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
                    <input type="hidden" name="command" value="subscription"/>
                    <select class="subscription_dropdown" name="subscriptionType">
                        <ul class="dropdown">
                            <c:forEach var="desc" items="${requestScope.subscriptionTypes}">
                                <option>${desc.description}: ${desc.price}</option>
                            </c:forEach>
                        </ul>
                    </select><br/>
                    <div class="input-control">
                        <input id="credit_card_number_id" name="credit_card_number" type="number"
                               placeholder="0000-0000-0000-0000"><br/>
                        <div class="error"></div>
                    </div>
                    <div class="input-control">
                        <input id="cardholder_name_id" name="cardholder_name" type="text"
                               placeholder="${cardholder}"><br>
                        <div class="error"></div>
                    </div>
                    <div class="input-control">
                        <input id="cvv_id" name="cvv" type="number" placeholder="CVV"><br>
                        <div class="error"></div>
                    </div>
                    <div class="input-control">
                        <input id="date_id" name="date" type="date"><br>
                        <div class="error"></div>
                    </div>
                    <input class="button" type="submit" value="${pay}"><br>
                </label>
            </form>
        </div>
    </div>
</div>
</body>
</html>

