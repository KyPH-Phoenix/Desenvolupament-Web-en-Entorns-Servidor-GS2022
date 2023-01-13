<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New City</title>
</head>
<body>
    <h1>NEW CITY IN ${country}</h1>
    <form action="/newCity/${code}" method="post">
        <label for="cityName">Name:</label>
        <input type="text" name="cityName" id="cityName">

        <label for="district">District:</label>
        <select name="district" id="district">
            <c:forEach var="district" items="${districts}">
                <option value="${district}">${district}</option>
            </c:forEach>
        </select>

        <label for="population">Population</label>
        <input type="number" name="population" id="population">

        <input type="submit" value="Create">
    </form>
</body>
</html>