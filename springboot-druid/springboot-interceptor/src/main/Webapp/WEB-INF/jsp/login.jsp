<%@page contentType="text/html; uft-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
</head>
<body>
<form method="get" action="/user/login">
    用户名:<input type="text" name="username" />
    密码：<input type="text" name="password">
    <input type="submit" value="登录">
</form>
</body>
</html>