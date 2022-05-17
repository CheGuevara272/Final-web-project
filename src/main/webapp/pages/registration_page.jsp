<%--
  Created by IntelliJ IDEA.
  User: gravi
  Date: 29.04.2022
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="language_text"/>
<c:set var="path">${pageContext.request.contextPath}</c:set>

<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${path}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${path}/css/enter.css">

    <title><fmt:message key="registration.page"/></title>
</head>
<body>
<form action="controller">
    Login: <input type="text" name="login" value="">
    <hr/>
    Password: <input type="text" name="password" value="">
    <hr/>
    Name: <input type="text" name="name" value="">
    <hr/>
    Surname: <input type="text" name="surname" value="">
    <hr/>
    E-Mail: <input type="email" name="email" value="">
    <hr/>
    Birthday: <input type="date" name="birthday" value="">
    <hr/>
    Phone number: <input type="tel" name="phone" value="">
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