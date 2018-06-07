<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Zuzia
  Date: 03.06.2018
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>


<html>
<head>
    <title>Nowa książka</title>
</head>
<body>
<div id="container" align="center">


    <form:form method="POST" modelAttribute="book">
        <label for="titleInput">Tytuł: </label>
        <form:input path="title" id="titleInput" /><br/><br/>

        <label for="authorInput">Autor: </label>
        <form:input path="author" id="authorInput" /><br/><br/>

        <label for="categoryInput">Kategoria: </label>
        <form:input path="category" id="categoryInput" /><br/><br/>

        <label for="amountInput">Ilość egzemplarzy: </label>
        <form:input path="numberOfCopies" id="amountInput" /><br/><br/>

        <label for="rentalTimeSelect">Czas wypożyczenia:</label>
        <form:select path="rentalTime" id="rentalTimeSelect">
            <form:option value="">Wybierz czas wypożyczenia: </form:option>
            <c:forEach items="${rentalTimes}" var="rt">
                <form:option value="${rt}">${rt}</form:option>
            </c:forEach>
        </form:select><br/><br/>
        <br/>
        <input type="submit" value="Zatwierdź" />
    </form:form>
</div>
</body>
</html>
