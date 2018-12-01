<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/16 0016
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login.jsp</title>
</head>
<body>
<form action="<c:url value='/doLogin'/>" method="post">
    Username:<input type="text" name="name"><br>
    Password:<input type="password" name="password"><br>
    <input type="submit" value="登录">

</form>
</body>
</html>
