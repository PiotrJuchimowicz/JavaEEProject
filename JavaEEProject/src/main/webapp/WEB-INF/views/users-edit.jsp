<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Iwona
  Date: 14.06.2018
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edycja danych </title>


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

    <div id="edit" style="margin-top: 50px;"
         class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

        <div class="panel panel-primary">

            <div class="panel-heading">
                <div class="panel-title">Edytuj swoje dane </div>
            </div>

            <div style="padding-top: 30px" class="panel-body">


                <form:form method="POST" modelAttribute="user">


                    <div style="margin-bottom: 25px" class="input-group">

                        <form:input path="username" id="username"/><br/><br/>
                    </div>

                    <div style="margin-bottom: 25px" class="input-group">


                        <form:password path="password" id="password"/><br/><br/>
                    </div>


                    <div style="margin-bottom: 25px" class="input-group">


                        <form:input path="name" id="name"/><br/><br/>
                    </div>


                    <div style="margin-bottom: 25px" class="input-group">

                        <form:input path="surname" id="surname"/><br/><br/>
                    </div>

                    <div style="margin-bottom: 25px" class="input-group">

                        <form:input path="email" id="email"/><br/><br/>
                    </div>


                    <div style="margin-top: 10px" class="form-group">
                        <div class="col-sm-6 controls">
                            <button type="submit" class="btn btn-primary">Potwierdź</button>
                        </div>
                    </div>

                </form:form>

            </div>

        </div>

    </div>

</div>

</div>

</body>
</html>
