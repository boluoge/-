<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/16 0016
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>viewUser.jsp</title>
</head>
<body>
<table border="1">
    <tr>
        <td>ID</td>
        <td><c:out value="${user.id}"></c:out></td>
    </tr>
    <tr>
        <td>Name</td>
        <td><c:out value="${user.name}"></c:out></td>
    </tr>
    <tr>
        <td>Password</td>
        <td><c:out value="${user.password}"></c:out></td>
    </tr>
    <tr>
        <td>Email</td>
        <td><c:out value="${user.email}"></c:out></td>
    </tr>
    <tr>
        <td>NickName</td>
        <td><c:out value="${user.nickName}"></c:out></td>
    </tr>
    <tr>
        <td>RegDate</td>
        <td><c:out value="${user.regDate}"></c:out></td>
    </tr>
</table>
</body>
</html>
