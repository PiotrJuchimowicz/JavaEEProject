<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Zuzia
  Date: 02.06.2018
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Użytkownicy seriwsu</title>
</head>
<body>
<h2>Lista użytkowników serwisu</h2>
<c:forEach items = "${users}" var="u">
    ${u.getId()}<br>
    ${u.getName()}<br>
    ${u.getSurname()}<br>
    ${b.getEmail()}<br>
    <hr>
</c:forEach>
</body>
</html>
