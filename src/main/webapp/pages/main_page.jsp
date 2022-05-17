<%--
  Created by IntelliJ IDEA.
  User: gravi
  Date: 14.04.2022
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Main</title>
</head>
<body>
Hello (forward) = ${user}
<hr/>
Hi! (redirect/forward) = ${user_name}
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="logout">
    <input type="submit" name="logout" value="LogOut">
</form>
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="admin_page">
    <input type="submit" name="admin page" value="admin page">
</form>
</body>
</html>
