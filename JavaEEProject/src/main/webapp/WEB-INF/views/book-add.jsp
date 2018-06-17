<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

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
                <div class="panel-title"> Nowa książka </div>
            </div>

            <div style="padding-top: 30px" class="panel-body">

<div id="container" align="center">



    <form:form method="POST" modelAttribute="book">
        <label for="titleInput">Tytuł: </label>
        <form:input path="title" id="titleInput" />
        <%--<form:errors path="title" id="titleInput" cssClass="error"/>--%>
        <br/><br/>


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



        <div class="form-group">
            <div class="col-xs-15">
                <div>


                    <c:if test="${error != null}">

                        <div class="alert alert-danger col-xs-offset-1 col-xs-10">
                                ${error}
                        </div>

                        <br><br> <br>
                    </c:if>

                    <c:if test="${confirm != null}">

                        <div class="alert alert-success col-xs-offset-1 col-xs-10">
                                ${confirm}
                        </div>

                        <br><br> <br>
                    </c:if>


                </div>
            </div>
        </div>

        <br/>
        <input type="submit" value="Zatwierdź" />
    </form:form>
</div>

                <a href="${pageContext.request.contextPath}/books/getall"> Wróć do strony głównej</a>

            </div>

        </div>

    </div>

</div>
</body>
</html>
