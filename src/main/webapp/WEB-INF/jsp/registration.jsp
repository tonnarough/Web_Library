<%--
  Created by IntelliJ IDEA.
  User: yatse
  Date: 11.01.2022
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div>
    <h1>Registration</h1><br>
    <form method="post" action="controller">
        <label>
            <input type="hidden" name="command" value="registration">
            <input type="text" required placeholder="login" name="login">
            <input type="password" required placeholder="password" name="password"><br>
            <input type="text" required placeholder="last name" name="last name"><br>
            <input type="text" required placeholder="first name" name="first name"><br>
            <input type="text" required placeholder="father name" name="father name"><br>
            <input type="text" required placeholder="email" name="email"><br>
            <input type="text" required placeholder="mobile" name="mobile"><br>
            <input type="text" required placeholder="birthday" name="birthday"><br>
            <input class="button" type="submit" value="Registration"><br>
        </label><br>
    </form>
</div>
</body>
</html>
