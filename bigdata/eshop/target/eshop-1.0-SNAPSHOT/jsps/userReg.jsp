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
    <title>userReg.jsp</title>
</head>
<body>
<form action="<c:url value='/doReg'/>" method="post">
    Username:<input type="text" name="name" value="<c:out value="${user.name}"/>"><br>
    Password:<input type="password" name="password" value="<c:out value="${user.password}"/>"><c:out
        value="${requestScope['error.password.nosame']}"></c:out><br>
    ConfirmPassword:<input type="password" name="confirmPass"><br>
    Email:<input type="email" name="email" value="<c:out value="${user.email}"/>"><c:out
        value="${requestScope['error.email.registed']}"></c:out><br>
    NickName:<input type="text" name="nickName" value="<c:out value="${user.nickName}"/>"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
