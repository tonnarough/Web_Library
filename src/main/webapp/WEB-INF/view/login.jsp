<%--
  Created by IntelliJ IDEA.
  User: yatse
  Date: 08.01.2022
  Time: 4:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <div>
        <h1>Вход в систему</h1><br>
        <form method = "post" action ="">
            <label>
                <input type ="text" required placeholder="login" name="login">
                <input type ="password" required placeholder="password" name="password"><br>
                <input class ="button" type="submit" value="Войти"><br>
            </label><br>
        </form>
    </div>
</body>
</html>
