<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Zuzia
  Date: 08.06.2018
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>





    <c:if test="${empty books}">
        Nie znaleziono książek z danej kategorii.
    </c:if>
    <c:forEach items = "${books}" var="b">
        <h2>${b.getIdBook()}: ${b.getTitle()} - ${b.getAuthor()}</h2>
        ${b.getCategory()}<br>

        <a href="<c:url value='/books/findbyid'/>/${b.idBookToString()}">Przejdź do strony książki</a>  <br>

        <%--JEŻELI LICZBA EGZEMPLARZY JEST WIĘKSZA OD ZERA TO MOŻNA WYPOŻYCZYC--%>
        <c:if test="${b.getNumberOfCopies() > 0}">
            <a href="<c:url value='/books/findbyid'/>/${b.idBookToString()}"> TU BĘDZIE REZERWOWANIE</a>  <br>
        </c:if>
        <hr>
    </c:forEach>
    <hr>
    <a href="${pageContext.request.contextPath}/books/getall"> Wróć do strony głównej</a> <br>
</body>
</html>
