<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Zuzia
  Date: 08.06.2018
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Autorzy</title>
</head>
<body>
    <c:forEach items="${authors}" var="a">
        <a href="<c:url value='/books/author'/>/${a}">${a}</a><br>
    </c:forEach>
    <hr>
    <a href="<c:url value='/books/getall'/>">Powrót do listy książek</a> <br>
</body>
</html>
