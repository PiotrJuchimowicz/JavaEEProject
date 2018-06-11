<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Zuzia
  Date: 01.06.2018
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  
<head>

        <title>Książka nr ${book.getIdBook()}</title>

        <style>
            .failed{
                color:red;
            }
        </style>

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
                <div class="panel-title"> Książka nr ${book.getIdBook()} </div>
            </div>

            <div style="padding-top: 30px" class="panel-body">


            <h2>${book.getTitle()}</h2>
    <h3>${book.getAuthor()}</h3>
    Kategoria: ${book.getCategory()}<br>
    Maksymalny czas wypożyczenia: ${book.getRentalTime()}<br>
    Ilość egzemplarzy: ${book.getNumberOfCopies()}<br>


                <br>

        <a href="<c:url value='/books/reservation'/>/${book.idBookToString()}"> Zarezerwuj książke, nie martw się, że ktoś Ci ją zabierze przed egzaminem !!!! </a>  <br>



    <a href="<c:url value='/books/removeconfirm'/>/${book.idBookToString()}">Usuń książkę</a> <br>
    <a href="<c:url value='/books/getall'/>">Powrót do listy książek</a> <br>

            </div>

        </div>

    </div>

</div>


</body>
</html>
