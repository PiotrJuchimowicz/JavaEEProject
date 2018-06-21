<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Iwona
  Date: 10.06.2018
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Lista zamówień</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Reference Bootstrap files -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>


<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Menu</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">

                <li ><a href="${pageContext.request.contextPath}/books/getall"> Strona główna <span
                        class="sr-only">(current)</span></a></li>

                <security:authorize access="hasAnyRole('EMPLOYEE', 'ADMIN')">
                    <li><a href="${pageContext.request.contextPath}/books/add">Dodaj książkę<span class="sr-only">(current)</span></a>
                    </li>

                    <li><a href="${pageContext.request.contextPath}/users/findall">Wyświetl użytkownikow<span class="sr-only">(current)</span></a>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">Wypożyczenia<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li ><a href="${pageContext.request.contextPath}/issues/findall">Wszystkie</a></li>
                            <li><a href="${pageContext.request.contextPath}/issues/notreturned">Nie zwrócone</a></li>

                        </ul>
                    </li>

                    <li><a href="${pageContext.request.contextPath}/issues/reservations">Rezerwacje<span
                            class="sr-only">(current)</span></a></li>


                </security:authorize>


            </ul>

            <ul class="nav navbar-nav navbar-right">

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"> Użytkownik <span class="caret"></span></a>
                    <ul class="dropdown-menu">

                        <security:authorize access="hasAnyRole('CLIENT', 'ADMIN', 'EMPLOYEE')">
                            <li><a href="${pageContext.request.contextPath}/users/userspanel">Moje konto</a></li>
                            <li><a href="${pageContext.request.contextPath}/issues/mine">Wypożyczone książki</a></li>
                            <li><a href="${pageContext.request.contextPath}/issues/reservations/mine">Zarezerwowane książki</a></li>
                        </security:authorize>

                        <li><a href="${pageContext.request.contextPath}/loginPage">Logowanie!</a></li>
                        <li><a href="${pageContext.request.contextPath}/register/registration">Rejestracja!</a></li>


                        <li>
                            <security:authorize access="hasAnyRole('CLIENT', 'ADMIN', 'EMPLOYEE')">
                            <form:form action="${pageContext.request.contextPath}/logout" method="POST">

                            <input CLASS="btn btn-primary" role="button" aria-pressed="true" type="submit"
                                   value="Wyloguj"/>

                            </form:form>
                            </security:authorize>
                    </ul>
                </li>


                </li>
            </ul>
            </ul>
        </div>
    </div>
</nav>





<div>



    <div class="container" style="margin-top: 50px;"
         class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

        <div class="panel panel-primary">

            <div class="panel-heading">
                <div class="panel-title"><h3>Lista wypożyczeń</h3></div>
            </div>

            <div style="padding-top: 30px" class="panel-body">



                <form action="/issues/ofbook/">
                    <input type="number" name="id" placeholder="Podaj id książki">
                    <input type="submit" value="Szukaj">
                </form>

                <form action="/issues/ofuser/">
                    <input type="number" name="id" placeholder="Podaj id użytkownika">
                    <input type="submit" value="Szukaj">
                </form>

                <br>
                <c:forEach items="${issues}" var="i">


                    <a href="<c:url value='/issues/findbyid'/>/${i.getIdIssueToString()}"><h3>Wypożyczenie
                        nr: ${i.getIdIssueToString()}</h3></a>
                    <br>
                    Data wypozyczenia : ${i.getIssueDate()}<br>
                    Data rezerwacji : ${i.getReservationDate()}<br>
                    Data zwrotu : ${i.getReturnDate()}</p><br>


                    <br>

                    <hr>
                </c:forEach>

                <a href="${pageContext.request.contextPath}/books/getall"> Wróć do strony głównej</a>
            </div>

        </div>

    </div>

</div>

</body>
</html>
