<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>

    <style>
        tr, td, th, table {
            border: 2px solid black;
        }

        td, th {
            padding: 4px;
        }
        
        body > * {
            margin: auto;
            margin-top: 20px;
            margin-bottom: 20px;
        }

        h1, a {
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>${title}</h1>

    <a href="/newCity/${code}">Add city</a>
    <table>
        <tr>
            <th>
                Name
            </th>

            <th>
                District
            </th>

            <th>
                Population
            </th>
        </tr>

        <c:forEach var="city" items="${cities}">
            <tr>
                <td>
                    ${city.name}
                </td> 
                
                <td>
                    ${city.district}
                </td>
                
                <td>
                    ${city.population}
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>