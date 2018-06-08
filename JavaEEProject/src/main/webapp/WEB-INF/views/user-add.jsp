<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Zuzia
  Date: 08.06.2018
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nowy użytkownik</title>
</head>
<body>
    <div id="container" align="center">
        <form:form method="POST" modelAttribute="user">

            <label for="nameInput">Imię: </label>
            <form:input path="name" id="nameInput" /><br/><br/>

            <label for="surnameInput">Nazwisko: </label>
            <form:input path="surname" id="surnameInput" /><br/><br/>

            <label for="emailInput">Email: </label>
            <form:input path="email" id="emailInput" /><br/><br/>

            <label for="passwordInput">Hasło: </label>
            <form:input path="password" id="passwordInput" /><br/><br/>

            <label for="roleSelect">Rola:</label>
            <form:select path="role" id="roleSelect">
                <form:option value="">Wybierz rolę: </form:option>
                <c:forEach items="${roles}" var="r">
                    <form:option value="${r}">${r}</form:option>
                </c:forEach>
            </form:select><br/><br/>
            <br/>

            <input type="submit" value="Zatwierdź" />
    </form:form>
    <hr>
    <a href="<c:url value='/users/findall'/>">Powrót do listy użytkowników</a> <br>
</div>
</body>
</html>
