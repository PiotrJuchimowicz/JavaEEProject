<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Zuzia
  Date: 06.06.2018
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Nowa książka</title>
</head>
<body>

    ${message} <br>
    <hr>
    <a href="<c:url value='/books/getall'/>">Powrót do listy książek</a> <br>

</body>
</html>
