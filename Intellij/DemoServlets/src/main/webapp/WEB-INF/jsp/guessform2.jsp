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
    <h1> Guess Number </h1>

    <form method="post" action="/guess2">
        Number: <input type="number" name="number">
        <br>
        <input type="submit" value="Envia">
    </form>

    <p>
        <c:if test="${not empty state}">
            ${state}
            <br>
            <c:if test="${not empty diff}">
                ${diff}
            </c:if>
        </c:if>
    </p>

</body>
</html>