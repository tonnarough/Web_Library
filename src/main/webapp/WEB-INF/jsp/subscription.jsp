<%--
  Created by IntelliJ IDEA.
  User: yatse
  Date: 20.01.2022
  Time: 3:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Subscription</title>
</head>
<body>
<div>
    <h1>Subscription</h1>
        <form method="post" action="controller">
            <label>
                <input type="hidden" name="command" value="subscription"/>
                <select name="type">
                    <c:forEach var="desc" items="${requestScope.subscriptionTypes}">
                        <option>${desc.description} - ${desc.price}</option><br/>
                        <c:set var="type" scope="request" value="${desc.price}" />
                    </c:forEach>
                </select><br/>
                <input type="number" maxlength="12" placeholder="0000-0000-0000-0000"><br/>
                <input type="number" maxlength="4" placeholder="MM/YYYY">
                <input type="number" maxlength="3" placeholder="CVV"><br>
                <input id="buy" class="button" type="submit" value="Buy"><br>
            </label><br>
        </form>

</div>
</body>
</html>
