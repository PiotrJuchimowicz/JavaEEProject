<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Iwona
  Date: 07.06.2018
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>


<head>
    <title>Login Page</title>

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

    <div id="loginbox" style="margin-top: 50px;"
         class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

        <div class="panel panel-primary">

            <div class="panel-heading">
                <div class="panel-title">Zaloguj się </div>
            </div>

            <div style="padding-top: 30px" class="panel-body">

<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">

    <c:if test="${param.logout != null}">
        <i class="failed">Wylogowano.</i>
    </c:if>

    <c:if test="${param.error != null}">
        <i class="failed">Wprowadzone hasło lub login jest niewłaściwe.</i>
    </c:if>



    <p>
        Login: <input type="text" name="username"/>
    </p>
    <p>
        Hasło: <input type="password" name="password"/>
    </p>

    <div style="margin-top: 10px" class="form-group">
        <div class="col-sm-6 controls">
            <button type="submit" class="btn btn-primary">Zaloguj</button>
        </div>
    </div>

    <br><br>

    <a href="<c:url value='/register/registration'/>">Nie masz konta? Zarejestruj się! </a> <br>
</form:form>
            </div>

        </div>

    </div>

</div>


</body>
</html>
