<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Iwona
  Date: 10.06.2018
  Time: 03:12
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

<h1> lista </h1>

<c:forEach items="${issues}" var="i">
    <a href="<c:url value='/issues/findbyid'/>/${i.getIdIssueToString()}"> ${i.getIdIssue()}<br>
    <hr>
</c:forEach>


</body>
</html>