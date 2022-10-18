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
    <h1> 
        Form
        <c:if test="${not empty nom}">
            : ${nom}
        </c:if>
    </h1>

    <c:choose>
        <c:when test="${empty result}">
            <form method="post" action="/pars">
                Num1: <input type="number" name="a">
                <br>
                Num2: <input type="number" name="b">
                <br>
                <select name="operation" id="operation">
                    <option value="1">Suma</option>
                    <option value="2">Resta</option>
                    <option value="3">Multiplicacio</option>
                    <option value="4">Divisio</option>
                </select>
                <br>
                <input type="submit" value="envia">
            </form>
        </c:when>
        <c:otherwise>
            <p>
                ${a} ${operator} ${b} = ${result}
            </p>
        </c:otherwise>
    </c:choose>
</body>
</html>