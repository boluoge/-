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
    <title>userList.jsp</title>
</head>
<table border="1">
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>password</td>
        <td>nickName</td>
        <td>email</td>
        <td>regDate</td>
        <td>删除</td>
        <td>查看</td>
        <td>编辑</td>
    </tr>
    <c:forEach items="${allUsers}" var="u">
        <tr>
            <td><c:out value="${u.id}"></c:out></td>
            <td><c:out value="${u.name}"></c:out></td>
            <td><c:out value="${u.password}"></c:out></td>
            <td><c:out value="${u.nickName}"></c:out></td>
            <td><c:out value="${u.email}"></c:out></td>
            <td><c:out value="${u.regDate}"></c:out></td>
            <td><a href="<c:url value='/admin/delUser?uid=${u.id}'/>">删除</a></td>
            <td><a href="<c:url value='/admin/viewUser?uid=${u.id}'/>">查看</a></td>
            <td><a href="<c:url value='/admin/editUser?uid=${u.id}'/>">编辑</a></td>
        </tr>
    </c:forEach>
</table>
</html>
