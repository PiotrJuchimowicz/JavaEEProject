<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Iwona
  Date: 14.06.2018
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Panel użytkownika</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Reference Bootstrap files -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>





<div>

    <div id="userspanel" style="margin-top: 60px;"
         class="mainbox col-md-6 col-md-offset-2 col-sm-6 col-sm-offset-8">

        <div class="panel panel-primary">

            <div class="panel-heading">
                <div class="panel-title">Panel użytkownika </div>
            </div>

            <div style="padding-top: 50px" class="panel-body">
                <img src="https://cdn-img.easyicon.net/png/5580/558013.gif" alt="brak"  class="rounded float-right" >

<br>

                <h3>Imie :</h3> ${user.getName()}<br>
                <h3>Nazwisko: </h3>${user.getSurname()}<br>
                <h3>E-mail: </h3> ${user.getEmail()}<br>

                <h3>Do zapłaty:</h3> ${user.getPayment()} zł <br>
<br>
<a CLASS="btn btn-primary" role="button" aria-pressed="true" href="${pageContext.request.contextPath}/users/userspanel/edit"> Edytuj dane </a>


            </div>

        </div>

    </div>

</div>



</body>
</html>
