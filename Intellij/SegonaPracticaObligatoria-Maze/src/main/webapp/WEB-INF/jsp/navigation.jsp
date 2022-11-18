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

        let data = JSON.parse(document.getElementById("mydata").textContent);
        console.log(data);
        
        ctx.font = "30px Arial";

        ctx.fillText("Room: " + data.player.currentRoom, 10, 30);
        ctx.fillText("Keys: " + data.player.keys, 10, 65);
        ctx.fillText("Coins: " + data.player.coins, 10, 100);

        ctx.font = "18px Arial"
        ctx.fillText(data.message, 200, 40);

        if (data.room.coin) {
            coin.onload = () => drawCoin();
        }

        if (data.room.key) {
            key.onload = () => drawKey();
        }

        if (data.room.walls.n.type == "wall") {
            drawWall("N");
        } else {
            if (!data.room.walls.n.open) {
                drawLockedDoor("N");
            }
        }

        if (data.room.walls.s.type == "wall") {
            drawWall("S");
        } else {
            if (!data.room.walls.s.open) {
                drawLockedDoor("S");
            }
        }

        if (data.room.walls.e.type == "wall") {
            drawWall("E");
        } else {
            if (!data.room.walls.e.open) {
                drawLockedDoor("E");
            }
        }

        if (data.room.walls.w.type == "wall") {
            drawWall("W");
        } else {
            if (!data.room.walls.w.open) {
                drawLockedDoor("W");
            }
        }

        function drawLockedDoor(direction) {
            ctx.fillStyle = "red";
            fillSpace(direction);
        }

        function drawWall(direction) {
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
                    window.location.assign("/nav?dir=N");
                }

                if (x >= 672 && x <= 705 && y >= 508 && y <= 535) {
                    window.location.assign("/nav?dir=W");
                }

                if (x >= 735 && x <= 769 && y >= 508 && y <= 535) {
                    window.location.assign("/nav?dir=E");
                }

                if (x >= 707 && x <= 735 && y >= 535 && y <= 570) {
                    window.location.assign("/nav?dir=S");
                }

                if (y >= 400 && y <= 480) {
                    if (x >= 220 && x <= 300 && data.room.coin) {
                        window.location.assign("/getcoin");
                    }

                    if (x >= 490 && x <= 570 && data.room.key) {
                        window.location.assign("/getkey");
                    }
                }

                if (data.room.walls.n.type == "door" && !data.room.walls.n.type.open) {
                    if (x >= 353 && x <= 452 && y >= 72 && y <= 96) {
                         
                    }
                }
            }
        });

    </script>
</body>

</html>