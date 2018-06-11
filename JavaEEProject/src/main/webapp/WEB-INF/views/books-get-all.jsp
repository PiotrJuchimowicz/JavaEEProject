<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
    <title>Lista zasobów</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Reference Bootstrap files -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

    <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>
<body>

<!-- TO BEDZIE TYLKO DLA ADMINA -->
<security:authorize access="hasRole('ADMIN')">
<div>
    <a href="${pageContext.request.contextPath}/register/registration"
       class="btn btn-primary"
       role="button" aria-pressed="true">
        Dodaj użytkownika
    </a>
</div>
</security:authorize>

<!-- TO DODAJE PRZYCISK WYLOGUJ -->
<security:authorize access="hasAnyRole('CLIENT','ADMIN', 'EMPLOYEE')">
<form:form action="${pageContext.request.contextPath}/logout" method="POST">

    <input CLASS="btn btn-primary" role="button" aria-pressed="true" type="submit" value="Wyloguj"/>

</form:form>

</security:authorize>
<!-- tylko dla pracowników - dodawanie książki -->
<security:authorize access="hasRole('EMPLOYEE')">
    <p>
        <a href="${pageContext.request.contextPath}/books/add">Dodaj ksiazke</a>
        (tylko dla pracowników!)
    </p>
</security:authorize>


<div>

    <div class="container" style="margin-top: 50px;"
         class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

        <div class="panel panel-primary">

            <div class="panel-heading">
                <div class="panel-title">Lista zasobów biblioteki</div>
            </div>

            <div style="padding-top: 30px" class="panel-body">



<!-- tylko dla pracowników - dodawanie książki -->
<security:authorize access="hasRole('EMPLOYEE')">
    <p>
        <a href="${pageContext.request.contextPath}/books/add">Dodaj ksiazke</a>
        (tylko dla pracowników!)
    </p>
</security:authorize>


<a href="<c:url value='/books/findbycategory'/>"> Wyszukaj po kategorii</a> <br>
<hr>
<a href="<c:url value='/books/findbyauthor'/>"> Wyszukaj po autorze</a> <br>
<hr>
<form action="/books/title/">
    <input type="text" name="t" placeholder="Podaj tytuł">
    <input type="submit" value="Szukaj">
</form>
<hr><hr>
<c:if test="${empty books}">
    Brak książek.
</c:if>
<c:forEach items = "${books}" var="b">
                <a href="<c:url value='/books/findbyid'/>/${b.idBookToString()}">  <h4>${b.getIdBook()}: ${b.getTitle()} - ${b.getAuthor()}</h4></a>
    ${b.getCategory()}<br>



    <hr>
</c:forEach>
            </div>

        </div>

    </div>

</div>


</body>
</html>
