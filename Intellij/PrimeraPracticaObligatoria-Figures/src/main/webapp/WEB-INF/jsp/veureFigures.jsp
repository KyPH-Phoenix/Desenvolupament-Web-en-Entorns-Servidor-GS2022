<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Veure Figures</title>
    
    <!-- Bootstrap Stylesheet -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<body>
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
                        <a class="nav-link" href="/figures">Crear Figura</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/veureFigures">Veure Figures</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/mevesFigures">Les Meves Figures</a>
                    </li>
                </ul>
            </section>
        </nav>
    </header>

    <main class="m-5">
        <h1>Llista de figures</h1>
        <table class="table table-hover table-striped">
            <thead>
                <tr>
                    <th scope="col">ID Figura</th>
                    <th scope="col">Nom Figura</th>
                    <th scope="col">Data de Creacio</th>
                    <th scope="col">ID Usuari</th>
                    <th scope="col">Nom Usuari</th>
                    <th scope="col" colspan="2" class="text-center pr-5">Accions</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="figure" items="${figures}">
                    <tr>
                        <td scope="row" class="pt-3">${figure.figureId}</td>
                        <td class="pt-3">${figure.name}</td>
                        <td class="pt-3">${figure.creationDate.year} ${figure.creationDate.month} ${figure.creationDate.dayOfMonth}</td>
                        <td class="pt-3">${figure.user.id}</td>
                        <td class="pt-3">${figure.user.userName}</td>
                        <td>
                            <form action="/figures" method="get">
                                <input type="hidden" name="figureId" id="figureId" value="${figure.figureId}">
                                <input type="submit" value="Mostrar" class="btn btn-dark">
                            </form>
                        </td>
                        <td>
                            <form action="/borrarFigura" method="post" onsubmit="return confirmDelete()">
                                <input type="hidden" name="figureId" id="figureId" value="${figure.figureId}">
                                <input type="submit" value="Borrar" class="btn btn-dark">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </main>

    <script>
        function confirmDelete() {
            if (confirm("Segur que vols esborrar la figura?")) {
                return true;
            } else {
                return false;
            }
        }
    </script>

     <!-- Bootstrap JS Scripts -->
     <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
     <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script> 
</body>
</html>