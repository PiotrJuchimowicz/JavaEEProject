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
<h1>Lista użytkowników serwisu</h1>
<hr><hr>
<a href="<c:url value='/users/add'/>">Dodaj nowego użytkownika</a> <br>
<hr><hr>
<c:if test="${empty users}">
    Brak użytkowników.
</c:if>
<c:forEach items = "${users}" var="u">
    <h3>${u.getIdUser()}: ${u.getName()} ${u.getSurname()}</h3>
    ${u.getEmail()}<br>
    <a href="<c:url value='/users/findbyid'/>/${u.idUserToString()}">Przejdź do strony użytkownika</a><br>
    <hr>
</c:forEach>
</body>
</html>
