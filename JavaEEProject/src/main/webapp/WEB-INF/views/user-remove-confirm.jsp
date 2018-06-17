<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Iwona
  Date: 12.06.2018
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Usuwanie użytkownika</title>
</head>
<body>


<h1>Usunąć z bazy użytkownika nr ${user.getIdUser()}?</h1>
<a href="<c:url value='/users/remove'/>/${user.getIdUser()}">Usuń</a> <br>

<a href="<c:url value='/users/findbyid'/>/${user.getIdUser()}">Anuluj</a> <br>


</body>
</html>
