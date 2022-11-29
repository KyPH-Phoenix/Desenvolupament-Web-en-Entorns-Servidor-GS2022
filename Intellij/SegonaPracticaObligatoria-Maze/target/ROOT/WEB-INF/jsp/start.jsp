<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Start</title>
</head>
<body>
    <h1>Seleccio de mapa</h1>

    <form action="/start" method="post">
        <label for="mapid">Tria mapa</label>
        <select name="mapid" id="mapid">
            <c:forEach var="map" items="${maps}">
                <option value="${map.id}">${map.name}</option>
            </c:forEach>
        </select>

        <input type="submit" value="Jugar">
    </form>
</body>
</html>