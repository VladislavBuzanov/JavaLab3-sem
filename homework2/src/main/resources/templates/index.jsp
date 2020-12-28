<%--
  Created by IntelliJ IDEA.
  User: VOBuzanov
  Date: 28.12.2020
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/produce" method="post">
    <label>Name:
        <input type="text" name="name">
    </label>
    <label> Surname:
        <input type="text" name="surname">
    </label>
    <label> Age:
        <input type="text" name="age">
    </label>

    <label> Passport date:
        <input type="text" name="passportDate">
    </label>
    <label>Passport number:
        <input type="text" name="passportNumber">
    </label>
    <input type="submit">
</form>
</body>
</html>
