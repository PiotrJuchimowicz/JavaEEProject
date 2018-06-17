<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Użytkownicy seriwsu</title>

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

    <div class="container" style="margin-top: 50px;"
         class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

        <div class="panel panel-primary">

            <div class="panel-heading">
                <div class="panel-title"><h2>Lista użytkowników serwisu</h2></div>
            </div>

            <div style="padding-top: 30px" class="panel-body">




<a href="${pageContext.request.contextPath}/books/getall"> Wróć do strony głównej</a>



<c:forEach items = "${users}" var="u">

    <a href="<c:url value='/users/findbyid'/>/${u.getIdUser()}">
        <h4>Użytkownik nr ${u.getIdUser()}</h4></a>
  Imię:  ${u.getName()}<br>
  Nazwisko:   ${u.getSurname()}<br>
  Adres e-mail:   ${u.getEmail()}<br>
  Dług: ${u.getPayment()} zł
    <hr>
</c:forEach>

            </div>

        </div>

    </div>

</div>


</body>
</html>
