<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html>
<head>
    <title>index.jsp</title>
</head>
<body>
<c:if test="${sessionScope.name ==null}">
    <c:out value="${requestScope.error}"></c:out>
</c:if>
<c:if test="${sessionScope.name !=null}">
    欢迎<c:out value="${sessionScope.name}"></c:out>
</c:if>
<a href="<c:out value='/toRegPage'/>">用户注册</a>
</body>
</html>
