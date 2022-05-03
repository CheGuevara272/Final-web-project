<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

<br/>
<form action="controller">
    <input type="hidden" name="command" value="login"/>
    Login: <input type="text" name="login" value=""/>
    <br/>
    Password: <input type="password" name="pass" value=""/>
    <br/>
    <input type="submit" name="sub" value="LogIn"/>
    <br/>
    ${login_msg}
</form>
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="registration_page"/>
    <input type="submit" name="sub" value="SignUp"/>
</form>
</body>
</html>