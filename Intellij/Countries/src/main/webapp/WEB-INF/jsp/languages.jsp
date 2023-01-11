<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LANGUAGES FROM ${title}</title>

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
                Name
            </th>

            <th>
                IsOfficial
            </th>

            <th>
                Percentage %
            </th>
        </tr>

        <c:forEach var="language" items="${languages}">
            <tr>
                <td>
                    <a href="/countries/${language.name}">${language.name}</a>
                </td> 
                
                <td>
                    ${language.official}
                </td>
                
                <td>
                    ${language.percentage}
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>