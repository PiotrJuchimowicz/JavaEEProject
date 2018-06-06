<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Zuzia
  Date: 01.06.2018
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Książka nr ${book.getIdBook()}</title>
    <style>
        a:link, a:visited {
            color: black;
            text-decoration: underline;
            cursor: pointer;
        }

        a:link:active, a:visited:active {
            color: black;
        }
    </style>
</head>
<body>
    <h2>${book.getTitle()}</h2>
    <h3>${book.getAuthor()}</h3>
    Kategoria: ${book.getCategory()}<br>
    Maksymalny czas wypożyczenia: ${book.getRentalTime()}<br>
    Ilość egzemplarzy: ${book.getNumberOfCopies()}<br>
    <hr>
    ${book.toString()}<br>
    <hr>

    <a href="<c:url value='/books/removeconfirm'/>/${book.idBookToString()}">Usuń książkę</a> <br>
    <a href="<c:url value='/books/getall'/>">Powrót do listy książek</a> <br>

</body>
</html>
