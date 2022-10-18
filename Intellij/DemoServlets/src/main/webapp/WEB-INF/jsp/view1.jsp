<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <style>
        table, th, td{
            border: 2px solid black;
            text-align: center;
        }       

        th {
            padding: 10px;
            margin: auto;
        }
    </style>
</head>

<body>
    <h1> MVC </h1>

    <!-- Us de variables -->
    <p> Hola, ${nom}. Ets ${profesio} </p>

    <!-- Imprimir un array amb un for -->
    <ul>
        <c:forEach var="i" items="${array}">
            <li> ${i} </li>
        </c:forEach>
    </ul>
    <p> Nom: ${person.name}, birth: ${person.birthYear} </p>

    <!-- IF -->
    <p>
        <c:if test="${temp < 30}">
            Fa fred!
        </c:if>
        <c:if test="${temp >= 30}">
            Fa calor!
        </c:if>
    </p>
    
    <!-- CHOOSE (equivalent a SWITCH o IFELSE) -->
    <p>
        <c:choose>
            <c:when test="${temp < 15}">
                Fa fred!
            </c:when>
            <c:when test="${temp < 25}">
                Normal
            </c:when>
            <c:when test="${temp < 40}">
                Fa calor!
            </c:when>
            <c:otherwise>
                Temperatura extrema!
            </c:otherwise>
        </c:choose>
    </p>

    <!-- Usar un mapa -->
    <p>
        <c:forEach var="n" items="${mapa}">
            <p> ${n.key} -- ${n.value} </p>
        </c:forEach>
    </p>

    <!-- Horari -->
    <table>
        <c:forEach var="row" items="${horari}">
            <tr>
                <c:forEach var="cell" items="${row}">
                    <td>${cell}</td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>

    
</body>
</html>