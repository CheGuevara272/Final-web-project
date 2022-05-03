<%--
  Created by IntelliJ IDEA.
  User: gravi
  Date: 29.04.2022
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>RegistrationPage</title>
</head>
<body>
<form action="controller">
    Login: <input type="text" name="login" value="">
    <hr/>
    Password: <input type="text" name="password" value="">
    <hr/>
    Name: <input type="text" name="name" value="">
    <hr/>
    Last name: <input type="text" name="last_name" value="">
    <hr/>
    E-Mail: <input type="text" name="e_mail" value="">
    <hr/>
    Birthday: <input type="text" name="birthday" value="">
    <hr/>
    Phone number: <input type="text" name="phone_number" value="">
    <hr/>
    <input type="hidden" name="command" value="register">
    <input type="submit" name="sub" value="SignUp">
    <hr/>
</form>
<form action="controller">
    <input type="hidden" name="command" value="logout"/>
    <input type="submit" value="LogIn"/>
</form>
</body>
</html>
