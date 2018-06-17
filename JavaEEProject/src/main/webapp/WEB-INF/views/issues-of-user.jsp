<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Iwona
  Date: 12.06.2018
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wypożyczenia użytkownika </title>

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
                <div class="panel-title"><h3>Lista wypożyczeń</h3></div>
            </div>

            <div style="padding-top: 30px" class="panel-body">




            <c:forEach items = "${issues}" var="i">
    <h4>Wypożyczenie nr: ${i.getIdIssueToString()}</h4>
    <br>
    Data wypozyczenia : ${i.getIssueDate()}<br>
    Data rezerwacji : ${i.getReservationDate()}<br>
    Data zwrotu : ${i.getReturnDate()}<br>
    <br>
    Książka nr:${i.getBook().getIdBook()} <br>
    tytuł: ${i.getBook().getTitle()}<br>
    autor: ${i.getBook().getAuthor()}<br>
    kategoria: ${i.getBook().getCategory()}<br>

    <br>

                <div class="form-group">
                    <div class="col-xs-15">
                        <div>


                            <c:if test="${returnError != null}">

                                <div class="alert alert-danger col-xs-offset-1 col-xs-10">
                                        ${returnError}
                                </div>

                            </c:if>

                            <c:if test="${confirm != null}">

                                <div class="alert alert-success col-xs-offset-1 col-xs-10">
                                        ${confirm}
                                </div>

                            </c:if>


                        </div>
                    </div>
                </div>
                <br><br> <br>
                <a href="<c:url value='/books/return'/>/${i.getIdIssueToString()}">Zwróć książkę </a> <br>
    <hr>
</c:forEach>
                <a href="${pageContext.request.contextPath}/books/getall"> Wróć do strony głównej</a>

            </div>

        </div>

    </div>

</div>


</body>
</html>
