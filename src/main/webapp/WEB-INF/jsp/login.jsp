<%--
  Created by IntelliJ IDEA.
  User: yatse
  Date: 11.01.2022
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <div class="singIn">
        <h1>Sign in</h1>
        <form method="post" action="controller">
            <label>
                <input type="hidden" name="command" value="sign_in">
                <input id="login_id" type="text" required placeholder="login" name="login"><br>
                <input id="password_id" type="password" required placeholder="password" name="password"><br>
                <input id="sign_in" class="button" type="submit" value="Sign in"><br>
            </label><br>
        </form>
    </div>
</body>
</html>
