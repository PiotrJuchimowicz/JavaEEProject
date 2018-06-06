<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Iwona
  Date: 06.06.2018
  Time: 20:36
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
<title>Książka z kategorii ${book.getCategory()}</title>

<%--<a href="<c:url value='views/book-add.jsp'>">Dodaj nową książkę </a> <br>--%>



<c:forEach items = "${books}" var="b">
    <a href="<c:url value='/books/category'/>/${b.getCategory()}"> ${b.getIdBook()}: ${b.getTitle()} - ${b.getAuthor()}</a>  <br>
    ${b.getCategory()}<br>
    <hr>
</c:forEach>
</body>
</html>