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

<!-- Trigger drawShape() -->
<c:choose>
    <c:when test="${not empty figure}">
        <body onload="drawShape('${figure.size}', '${figure.xCord}', '${figure.yCord}', '${figure.shape}', '${figure.color}')">
    </c:when>
    <c:otherwise>
        <body>
    </c:otherwise>
</c:choose>
    
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

    <main class="ml-5">
        <h1>Figures</h1>

        <canvas id="canvas" width="640" height="480" style="border: solid black 2px;"></canvas>

        <form action="/figures" method="post">
            <label for="shape">Figura:</label>
            <select name="shape" id="shape" width="100" onchange="drawPreview()">
                <option value="circle">Cercle</option>
                <option value="square">Quadrat</option>
                <option value="triangle">Triangle</option>
                <option value="pentagon">Pentagon</option>
                <option value="star">Estrella</option>
            </select>
    
            <label for="color">Color:</label>
            <select name="color" id="color" onchange="drawPreview()">
                <option value="black">Negre</option>
                <option value="green">Verd</option>
                <option value="red">Vermell</option>
                <option value="blue">Blau</option>
                <option value="yellow">Groc</option>
                <option value="gray">Gris</option>
            </select>
            <br>
    
            <label for="size">Tamny</label>
            <input type="range" name="size" id="size" onchange="drawPreview()">
    
            <br>
    
            <label for="xCord">Coordenada X:</label>
            <input type="number" name="xCord" id="xCord" onchange="drawPreview()">
    
            <label for="yCord">Coordenada Y:</label>
            <input type="number" name="yCord" id="yCord" onchange="drawPreview()">
    
            <br>
    
            <label for="figureName">Nom Figura:</label>
            <input type="text" name="figureName" id="figureName">
    
            <br>
    
            <input type="submit" value="Crear firgura">
        </form>
    </main>
    
    <script>
        let canvas = document.getElementById("canvas");
        let ctx = canvas.getContext("2d");

        function drawPreview() {
            let size = document.getElementById("size").value;
            let xCord = document.getElementById("xCord").value;
            let yCord = document.getElementById("yCord").value;
            let shape = document.getElementById("shape").value;
            let color = document.getElementById("color").value;

            ctx.clearRect(0, 0, canvas.width, canvas.height);

            drawShape(size, xCord, yCord, shape, color);
        }

        function drawShape(size, xCord, yCord, shape, color) {
            ctx.fillStyle = color;

            if (shape === "circle") {
                ctx.beginPath();
                ctx.arc(xCord, yCord, size, 0, 2 * Math.PI);
                ctx.fill();
                ctx.stroke();
            } else if (shape === "square") {
                ctx.beginPath();
                ctx.fillRect(xCord - (size), yCord - (size), size * 2, size * 2);
                ctx.rect(xCord - (size), yCord - (size), size * 2, size * 2);
                ctx.stroke();
            } else if (shape === "triangle") {
                let height = Math.sqrt((size * size) - ((size / 2) * (size / 2)));

                xCord = Number(xCord);
                yCord = Number(yCord);
                size = Number(size);
                height = Number(height);

                console.log(height, size);
                console.log(xCord, yCord);

                ctx.beginPath();
                ctx.moveTo(xCord, yCord - (height));
                ctx.lineTo(xCord + (size), yCord + (height));
                ctx.lineTo(xCord - (size), yCord + (height));
                ctx.lineTo(xCord, yCord - (height));

                ctx.fill();

                ctx.stroke();
            } else if (shape === "pentagon") {
                let alpha = Number((2 * Math.PI) / 5);
                let beta = Number((Math.PI / 180) * -18);

                xCord = Number(xCord);
                yCord = Number(yCord);
                size = Number(size);

                ctx.beginPath();

                for (let i = 0; i < 5; i++) {
                    let curStep = (i * alpha) + beta;
                    ctx.lineTo(xCord + size * Math.cos(curStep), yCord + size * Math.sin(curStep));
                }

                ctx.fill();
                ctx.stroke();
            } else if (shape === "star") {
                let rotation = Number(Math.PI / 2 * 3);
                xCord = Number(xCord);
                yCord = Number(yCord);
                let step = Number(Math.PI / 7);
                let outerRadius = Number(size);
                let innerRadius = Number(size / 2);
                let x = xCord;
                let y = yCord;

                ctx.beginPath();
                ctx.moveTo(xCord, yCord - outerRadius);

                for (i = 0; i < 7; i++) {
                    x = xCord + Math.cos(rotation) * outerRadius;
                    y = yCord + Math.sin(rotation) * outerRadius;
                    ctx.lineTo(x, y)
                    rotation += step

                    x = xCord + Math.cos(rotation) * innerRadius;
                    y = yCord + Math.sin(rotation) * innerRadius;
                    ctx.lineTo(x, y)
                    rotation += step
                }
                ctx.lineTo (xCord, yCord - outerRadius)
                ctx.fill();
                ctx.stroke();
            }
        }
    </script>

    <!-- Bootstrap JS Scripts -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>

</html>