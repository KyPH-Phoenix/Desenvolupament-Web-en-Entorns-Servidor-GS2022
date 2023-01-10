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

        body > * {
            margin: auto;
            margin-top: 20px;
        }

        h1 {
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>${title}</h1>

    <table>
        <tr>
            <th>
                Code
            </th>

            <th>
                Name
            </th>

            <th>
                Population
            </th>

            <th colspan="2">
                Actions
            </th>
        </tr>
        
        <c:forEach var="country" items="${countries}">
            <tr>
                <td>
                    ${country.code}
                </td>

                <td>
                    ${country.name}
                </td> 

                <td>
                    ${country.population}
                </td>

                <td>
                    <form action="/cities/${country.code}" method="get">
                        <input type="hidden" value="${country.code}">
                        <input type="submit" value="Cities">
                    </form>
                </td>

                <td>
                    <form action="/languages/${country.code}" method="get">
                        <input type="hidden" value="${country.code}">
                        <input type="submit" value="Languages">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>