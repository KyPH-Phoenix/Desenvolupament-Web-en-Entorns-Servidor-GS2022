<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NOTAS</title>
</head>
<body>
    <h1>LIBRO DE NOTAS</h1>

    <ul>
        <c:forEach var="message" items="${messages}">
            <li><p>${message.text}</p></li>
        </c:forEach>
    </ul>
    <form action="/index" method="post">
        <label for="newNote">Nota: </label>
        <input type="text" name="newNote" id="newNote">
        <input type="submit" value="Envia">
    </form>
</body>
</html>