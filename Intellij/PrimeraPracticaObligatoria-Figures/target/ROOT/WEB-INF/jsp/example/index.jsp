<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Llibre de visites</title>
</head>
<body>
    <h1> Llibre de visites </h1>

    <c:forEach var="m" items="${messages}">
        <div style="margin-bottom: 10px; border: 1px solid black;">
            ${m.text}
        </div>
    </c:forEach>

    <form action="/index" method="post">
        text:<br>
        <textarea name="text"></textarea>
    
        <br>
    
        <input type="submit" value="Envia">    
    </form>
    
</body>
</html>