<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Iwona
  Date: 10.06.2018
  Time: 23:44
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
<h1>Usunąć z bazy zamowienie nr ${issue.getIdIssue()}?</h1>
<a href="<c:url value='/issues/remove'/>/${issue.getIdIssueToString()}">Usuń</a> <br>

<!-- ten link mi nie działa -->
<!-- jednak działa, ale nie działał, wiec zostawie tu ślad że jak coś to może trzeba naprawić -->

<a href="<c:url value='/issues/findbyid'/>/${issue.getBook().idBookToString()}">Anuluj</a> <br>
</body>
</html>
