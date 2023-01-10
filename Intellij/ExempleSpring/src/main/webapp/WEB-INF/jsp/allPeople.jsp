<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All People</title>
</head>
<body>
    <table>
        <th>
            <td>
                ID
            </td>
            <td>
                Name
            </td>
        </th>
        <c:forEach var="person" items="${people}">
            <tr>
                <td>
                    ${person.id}
                </td>
                <td>
                    ${person.name}
                </td>          
            </tr>
        </c:forEach>
    </table>
</body>
</html>