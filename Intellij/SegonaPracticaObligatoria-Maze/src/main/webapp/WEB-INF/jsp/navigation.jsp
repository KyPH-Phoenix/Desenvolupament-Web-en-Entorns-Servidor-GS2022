<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navigation</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>

<body>
    <canvas id="canvas" width="800" height="600"></canvas>

    <script id="mydata" type="application/json">
        ${myjson}
    </script>

    <script>
        let canvas = document.getElementById("canvas");
        let ctx = canvas.getContext("2d");

        let coin = new Image();
        let key = new Image();
        let cross = new Image();
        let character = new Image();

        coin.src = '/img/coin.png';
        key.src = '/img/key.png';
        cross.src = '/img/cross.png';
        character.src = '/img/character.png';

        ctx.font = "30px Arial";
        ctx.fillText("Room: 1", 10, 30);
        ctx.fillText("Keys: 0", 10, 65);
        ctx.fillText("Coins: 0", 10, 100);

        ctx.fillStyle = "black";

        ctx.fillRect(170, 70, 180, 25);
        ctx.fillRect(450, 70, 180, 25);

        ctx.fillRect(170, 70, 25, 180);
        ctx.fillRect(605, 70, 25, 180);

        ctx.fillRect(170, 350, 25, 180);
        ctx.fillRect(605, 350, 25, 180);

        ctx.fillRect(170, 505, 180, 25);
        ctx.fillRect(450, 505, 180, 25);

        cross.onload = () => ctx.drawImage(cross, 670, 470, 100, 100);
        character.onload = () => ctx.drawImage(character, 370, 220, 80, 130)

        coin.onload = () => drawCoin();
        key.onload = () => drawKey();

        function lockedDoor(direction) {
            ctx.fillStyle = "red";
            fillSpace(direction);
        }

        function wall(direction) {
            ctx.fillStyle = "black";
            fillSpace(direction);
        }

        function fillSpace(direction) {
            if (direction == "N") {
                ctx.fillRect(350, 70, 100, 25);

            } else if (direction == "S") {
                ctx.fillRect(350, 505, 100, 25);

            } else if (direction == "W") {
                ctx.fillRect(170, 250, 25, 100);

            } else if (direction == "E") {
                ctx.fillRect(605, 250, 25, 100);
            }
        }

        function drawCoin() {
            ctx.drawImage(coin, 220, 400, 80, 80);
        }

        function drawKey() {
            ctx.drawImage(key, 490, 400, 80, 80);
        }

        canvas.addEventListener("mousedown", function (event) {
            const boundingRect = canvas.getBoundingClientRect();
            const x = event.clientX - boundingRect.left;
            const y = event.clientY - boundingRect.top;

            if (event.button == 0) {
                console.log(x, y)

                if (x >= 707 && x <= 735 && y >= 472 && y <= 506) {
                    console.log("up")
                }

                if (x >= 672 && x <= 705 && y >= 508 && y <= 535) {
                    console.log("left")
                }

                if (x >= 735 && x <= 769 && y >= 508 && y <= 535) {
                    console.log("right")
                }

                if (x >= 707 && x <= 735 && y >= 535 && y <= 570) {
                    console.log("down")
                }

                if (y >= 400 && y <= 480) {
                    if (x >= 220 && x <= 300) {
                        console.log("Coge moneda");
                    }

                    if (x >= 490 && x <= 570) {
                        console.log("Coge llave");
                    }
                }
            }
        });

    </script>
</body>

</html>