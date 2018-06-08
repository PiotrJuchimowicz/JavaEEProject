<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Zuzia
  Date: 08.06.2018
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Potwierdzenie</title>
</head>
<body>
    <h1>Usunąć z bazy użytkownika nr ${user.getIdUser()}?</h1>
    <a href="<c:url value='/users/remove'/>/${user.idUserToString()}">Usuń</a> <br>
    <a href="<c:url value='/users/findbyid'/>/${user.idUserToString()}">Anuluj</a> <br>
</body>
</html>
