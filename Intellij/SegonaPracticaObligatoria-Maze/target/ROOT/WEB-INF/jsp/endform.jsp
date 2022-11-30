<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Guanyador</title>
</head>
<body>
    <h1>Has Guanyat</h1>
    <h3>${mapName} - Temps: ${time}</h3>
    <h3>Registra la teva victòria</h3>
    <form action="/endform" method="post">
        <label for="userName"></label>
        <input type="text" name="userName" id="userName" maxlength="20">
        <input type="submit" value="Registra">  
    </form>
</body>
</html>