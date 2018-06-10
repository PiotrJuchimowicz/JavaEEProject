<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Iwona
  Date: 11.06.2018
  Time: 00:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rezerwacje książki</title>
</head>
<body>

<c:forEach items = "${issues}" var="i">
    <a href="<c:url value='/issues/findbyid'/>/${i.getBook().idBookToString()}"><h3>Zamowienie nr: ${i.getIdIssueToString()}</h3></a>
    <br>
    Data wypozyczenia : ${i.getIssueDate()}<br>
    Data rezerwacji : ${i.getReservationDate()}<br>
    Data zwrotu : ${i.getReturnDate()} <br>
    <br>

    <hr>
</c:forEach>

</body>
</html>
