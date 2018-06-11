<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Iwona
  Date: 10.06.2018
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Zamowienie nr :${issue.getIdIssueToString()}</title>

</head>
<body>
<h2>Zamowienie nr :${issue.getIdIssueToString()}</h2> <br>
<h3>Książka nr ${issue.getBook().idBookToString()} </h3>
Tytuł: ${issue.getBook().getTitle()}<br>
Autor: ${issue.getBook().getAuthor()}<br>
Kategoria: ${issue.getBook().getCategory()}<br>


<a href="<c:url value='/issues/borrow'/>/${issue.getIdIssueToString()}">Wypożycz</a> <br>


<a href="<c:url value='/issues/removeconfirm'/>/${issue.getIdIssueToString()}">Usuń zamówienie</a> <br>
<a href="<c:url value='/issues/findall'/>">Powrót do listy zamówień</a> <br>

</body>
</html>


</body>
</html>
