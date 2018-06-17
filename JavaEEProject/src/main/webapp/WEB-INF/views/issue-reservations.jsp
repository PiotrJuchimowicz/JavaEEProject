<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Iwona
  Date: 10.06.2018
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rezerwacje</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Reference Bootstrap files -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

    <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>





<div>

    <div class="container" style="margin-top: 50px;"
         class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

        <div class="panel panel-primary">

            <div class="panel-heading">
                <div class="panel-title"><h3>Lista rezerwacji</h3></div>
            </div>

            <div style="padding-top: 30px" class="panel-body">


                <form action="/issues/reservations/book/">
                    <input type="number" name="id" placeholder="Podaj id książki">
                    <input type="submit" value="Szukaj">
                </form>

                <form action="/issues/reservations/ofuser/">
                    <input type="number" name="id" placeholder="Podaj id użytkownika">
                    <input type="submit" value="Szukaj">
                </form>



<c:forEach items = "${issues}" var="i">
    <a href="<c:url value='/issues/reservations/findbyid'/>/${i.getIdIssue()}"><h3>Rezerwacja nr: ${i.getIdIssueToString()}</h3></a>
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