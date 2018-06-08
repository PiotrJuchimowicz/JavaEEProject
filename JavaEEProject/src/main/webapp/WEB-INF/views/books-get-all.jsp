<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <title>Książki</title>
    <style>
        a:link, a:visited {
            color: black;
            text-decoration: underline;
            cursor: pointer;
        }

        a:link:active, a:visited:active {
            color: black;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <h1>Lista zasobów biblioteki</h1>
    <hr><hr>
    <a href="<c:url value='/books/add'/>">Dodaj nową książkę</a> <br>
    <hr><hr>
    <a href="<c:url value='/books/findbycategory'/>"> Wyszukaj po kategorii</a> <br>
    <hr><hr>
    <a href="<c:url value='/books/findbyauthor'/>"> Wyszukaj po autorze</a> <br>
    <hr><hr>
    <form action="/books/title/">
        <input type="text" name="t" placeholder="Podaj tytuł">
        <input type="submit" value="Szukaj">
    </form>
    <hr><hr>
    <c:if test="${empty books}">
        Brak książek.
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

</body>
</html>