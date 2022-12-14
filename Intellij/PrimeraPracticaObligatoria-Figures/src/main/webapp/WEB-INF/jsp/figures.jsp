<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Figures</title>

    <!-- Bootstrap Stylesheet -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>


<body class="pb-5">
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
        <h1>Figures</h1>

        <canvas id="canvas" width="640" height="480" style="border: solid black 2px;"></canvas>

        <form action="/figures" method="post">
            <label for="shape">Figura:</label>
            <select name="shape" id="shape" class="form-select bg-dark text-white" onchange="drawPreview()" required>
                <option value="circle">Cercle</option>
                <option value="square">Quadrat</option>
                <option value="triangle">Triangle</option>
                <option value="pentagon">Pentagon</option>
                <option value="star">Estrella</option>
            </select>
    
            <label for="color">Color:</label>
            <select name="color" id="color" class="form-select bg-dark text-white" onchange="drawPreview()" required>
                <option value="black">Negre</option>
                <option value="green">Verd</option>
                <option value="red">Vermell</option>
                <option value="blue">Blau</option>
                <option value="yellow">Groc</option>
                <option value="gray">Gris</option>
            </select>
            <br>
    
            <label for="size">Tamny</label>
            <input type="range" name="size" id="size" onchange="drawPreview()" required>
    
            <br>
    
            <label for="xCord">Coordenada X:</label>
            <input type="number" name="xCord" id="xCord" onchange="drawPreview()" required>
    
            <label for="yCord">Coordenada Y:</label>
            <input type="number" name="yCord" id="yCord" onchange="drawPreview()" required>
    
            <br>
    
            <label for="figureName">Nom Figura:</label>
            <input type="text" name="figureName" id="figureName">
    
            <br>
    
            <input type="submit" class="btn btn-dark" value="Crear firgura">
            <button onclick="drawPreview()" type="button" class="btn btn-dark">Previsualitza</button>
        </form>
    </main>
    
    <script src="/javascript/draw.js"></script>
    <script>
        canvas.addEventListener("mousedown", function (event) {
            const boundingRect = canvas.getBoundingClientRect();
            const x = event.clientX - boundingRect.left;
            const y = event.clientY - boundingRect.top;

            if (event.button == 0 ) {
                document.getElementById("xCord").value = x;
                document.getElementById("yCord").value = y;
                drawPreview();
            }
        });
    </script>

    <!-- Bootstrap JS Scripts -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>

</html>