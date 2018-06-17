<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Iwona
  Date: 13.06.2018
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rezerwacje użytkownika</title>

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
                <div class="panel-title"><h3>Lista rezerwacji</h3></div>
            </div>

            <div style="padding-top: 30px" class="panel-body">


                <c:forEach items="${issues}" var="i">
                    <a href="<c:url value='/issues/reservations/findbyid'/>/${i.getIdIssue()}"><h3>Rezerwacja nr: ${i.getIdIssueToString()}</h3></a>
                    <br>
                    </a> <br>
                    Data wypozyczenia : ${i.getIssueDate()}<br>
                    Data rezerwacji : ${i.getReservationDate()}<br>
                    Data zwrotu : ${i.getReturnDate()}<br>
                    <br>
                    <security:authorize access="hasRole('CLIENT')">

                        <h3>Książka nr ${issue.getBook().idBookToString()} </h3>
                        Tytuł: ${issue.getBook().getTitle()}<br>
                        Autor: ${issue.getBook().getAuthor()}<br>
                        Kategoria: ${issue.getBook().getCategory()}<br>

                    </security:authorize>

                    <hr>
                </c:forEach>
                <div class="form-group">
                    <div class="col-xs-15">
                        <div>

                            <c:if test="${error != null}">

                                <div class="alert alert-danger col-xs-offset-1 col-xs-10">
                                        ${error}
                                </div>

                            </c:if>

                        </div>
                    </div>
                </div>


            </div>

        </div>

    </div>

</div>


</body>
</html>
