<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Iwona
  Date: 10.06.2018
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rezerwacje</title>
</head>
<body>
<h2>Lista rezerwacji </h2>
<c:forEach items = "${issues}" var="i">
    <a href="<c:url value='/issues/findbyid'/>/${i.getBook().idBookToString()}"><h3>Rezerwacja nr: ${i.getIdIssueToString()}</h3></a>
    <br>
    Data wypozyczenia : ${i.getIssueDate()}<br>
    Data rezerwacji : ${i.getReservationDate()}<br>
    Data zwrotu : ${i.getReturnDate()}</p><br>


    <br>

    <hr>
</c:forEach>
</body>
</html>