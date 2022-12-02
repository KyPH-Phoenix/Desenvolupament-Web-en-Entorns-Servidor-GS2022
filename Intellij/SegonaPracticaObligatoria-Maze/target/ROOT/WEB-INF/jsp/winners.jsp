<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Guanyadors</title>
    
    <!-- Bootstrap Stylesheet -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    
    <link rel="stylesheet" href="/css/styles.css">
</head>

<body class="vw-100 vh-100">
    <header>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <!-- Boton cuando se colapsa -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
                aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <section class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav ml-3">
                    <li class="nav-item active">
                        <a class="nav-link" href="/start">Menu Principal</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/winners">Leaderboard</a>
                    </li>
                </ul>
            </section>
        </nav>
    </header>

    <main class="px-3">
        <table class="table table-hover table-striped">
            <thead>
                <tr>
                    <th scope="col">Nom</th>
                    <th scope="col">Mapa</th>
                    <th scope="col">Temps</th>
                </tr>
            </thead>
    
            <tbody>
                <c:forEach var="winner" items="${winners}">
                <!-- <option value="${map.id}">${map.name}</option> -->
                    <tr>
                        <td class="pt-3">${winner.userName}</td>
                        <td class="pt-3">${winner.mapName}</td>
                        <td class="pt-3">${winner.stringTime}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </main>

    <!-- Bootstrap JS Scripts -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script> 
</body>

</html>