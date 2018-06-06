<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Zuzia
  Date: 03.06.2018
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Potwierdzenie</title>
    <style>
        a:link, a:visited {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        a:link:active, a:visited:active {
            color: black;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <h1>Usunąć z bazy książkę nr ${book.getIdBook()}?</h1>
    <a href="<c:url value='/books/remove'/>/${book.idBookToString()}">Usuń</a> <br>
    <a href="<c:url value='/books/findbyid'/>/${book.idBookToString()}">Anuluj</a> <br>
</body>
</html>
