<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1> Login </h1>

    <c:if test="${not empty message}">
        ${message}
    </c:if>

    <form action="/login" method="post">
        Usuari: <input type="text" name="username"><br>
        Password: <input type="password" name="password"><br>
        <input type="submit" value="Envia">
    </form>
</body>
</html>