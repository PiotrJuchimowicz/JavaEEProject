<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Zuzia
  Date: 08.06.2018
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kategorie</title>
</head>
<body>



    <c:forEach items="${categories}" var="c">
        <a href="<c:url value='/books/category'/>/${c}">${c}</a><br>
    </c:forEach>
    <hr>
    <a href="${pageContext.request.contextPath}/books/getall"> Wróć do strony głównej</a> <br>
</body>
</html>
