<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Zuzia
  Date: 02.06.2018
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Użytkownik ${user.getIdUser()}</title>

</head>
<body>
    <h2>${user.getName()} ${user.getSurname()}</h2>
    Email: ${user.getEmail()}<br>
    Płatność: ${user.getPayment()}<br>
    Rola: ${user.getRole()}<br>
    Zamówienia: <br>
    <c:if test="${empty user.getIssuesOfThisUser()}">
        Brak zamówień<br>
    </c:if>
    <c:if test="${!empty user.getIssuesOfThisUser()}">
        nie jest empty
        <c:forEach items = "${user.getIssuesOfThisUser()}" var="i">
            ${i.toString()}
        </c:forEach>
    </c:if>

    <a href="<c:url value='/users/removeconfirm'/>/${user.idUserToString()}">Usuń użytkownika</a> <br>
    <a href="<c:url value='/users/findall'/>">Powrót do listy użytkowników</a> <br>
</body>
</html>
