<%@ page isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Figures</title>
        </head>

        <body>
            <h1>Figures</h1>
            <canvas id="canvas" width="640" height="480" style="border: solid black 2px;"></canvas>
            <form action="/figures" method="post">

                <label for="shapes">Figura:</label>
                <select name="shapes" id="shapes" width="100" onchange="drawPreview()">
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

                <input type="submit" value="Crear firgura">
            </form>

            <script>
                let canvas = document.getElementById("canvas");
                let ctx = canvas.getContext("2d");

                function drawPreview() {
                    let size = document.getElementById("size").value;
                    let xCord = document.getElementById("xCord").value;
                    let yCord = document.getElementById("yCord").value;
                    let shape = document.getElementById("shapes").value;
                    ctx.fillStyle = document.getElementById("color").value;

                    ctx.clearRect(0, 0, canvas.width, canvas.height);

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

                        // ctx.moveTo(xCord, yCord - size);
                        // ctx.lineTo(xCord - (size * Math.cos((2 * alpha) + beta)), yCord - (size * Math.sin((2 * alpha) + beta)));
                        // ctx.lineTo(xCord - (size * Math.cos((3 * alpha) + beta)), yCord + (size * Math.sin((3 * alpha) + beta)));
                        // ctx.lineTo(xCord + (size * Math.cos((4 * alpha) + beta)), yCord + (size * Math.sin((4 * alpha) + beta)));
                        // ctx.lineTo(xCord + (size * Math.cos(beta)), yCord - (size * Math.sin(beta)));
                        // ctx.lineTo(xCord, yCord - size);

                        for (let i = 0; i < 5; i++) {
                            let curStep = (i * alpha) + beta;
                            ctx.lineTo(xCord + size * Math.cos(curStep), yCord + size * Math.sin(curStep));
                        }

                        ctx.fill();
                        ctx.stroke();
                    } else if (shape === "star") {

                    } else {
                        
                    }
                }
            </script>
        </body>

        </html>