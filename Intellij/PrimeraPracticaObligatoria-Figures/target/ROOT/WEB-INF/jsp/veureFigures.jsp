<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Veure Figures</title>
    <style>
        table, th, td{
            border: 2px solid black;
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>Llista de figures</h1>
    <table>
        <tr>
            <th>ID Figura</th>
            <th>Nom Figura</th>
            <th>Forma</th>
            <th>Color</th>
            <th>Tamany</th>
            <th>Coordenada X</th>
            <th>Coordenada Y</th>
            <th>ID Usuari</th>
        </tr>

        <c:forEach var="figure" items="${figures}">
            <tr>
                <td>${figure.figureId}</td>
                <td>${figure.name}</td>
                <td>${figure.shape}</td>
                <td>${figure.color}</td>
                <td>${figure.size}</td>
                <td>${figure.xCord}</td>
                <td>${figure.yCord}</td>
                <td>${figure.userId}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>