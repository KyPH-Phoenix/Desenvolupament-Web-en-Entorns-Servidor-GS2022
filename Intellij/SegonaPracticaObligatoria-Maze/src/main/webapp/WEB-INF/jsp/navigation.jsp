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

<body onload="drawRoom()">
    <canvas id="canvas" width="800" height="600"></canvas>

    <script id="mydata" type="application/json">
        ${myjson}
    </script>

    <script id="mydata" type="application/json">
        {
            "room":{
                "walls":{
                    "s":{
                        "type":"door",
                        "open":true
                    },
                    "e":{
                        "type":"door",
                        "open":true
                    },
                    "w":{
                        "type":"door",
                        "open":false
                    },
                    "n":{
                        "type":"door",
                        "open":true
                    }
                },
                "key":true,
                "coin":true
            },
            "player":{
                "currentRoom":1,
                "coins":0,
                "keys":0
            },
            "message":""
        }
    </script>

    <script>
        let canvas = document.getElementById("canvas");
        let ctx = canvas.getContext("2d");
        let data = JSON.parse(document.getElementById("mydata").textContent);
        console.log(data);

        let directions = {
            "down": {
                "frames": {
                    "f1": { "x": 0, "y": 0 },
                    "f2": { "x": 64, "y": 0 },
                    "f3": { "x": 128, "y": 0 },
                    "f4": { "x": 192, "y": 0 }
                },
                "clear": [350, 230, 100, 300],
                "positions": {
                    "p1": { "x": 355, "y": 235 },
                    "p2": { "x": 355, "y": 295 },
                    "p3": { "x": 355, "y": 355 },
                    "p4": { "x": 355, "y": 415 }
                }
            },
            "up": {
                "frames": {
                    "f1": { "x": 0, "y": 192 },
                    "f2": { "x": 64, "y": 192 },
                    "f3": { "x": 128, "y": 192 },
                    "f4": { "x": 192, "y": 192 }
                },
                "clear": [350, 50, 100, 300],
                "positions": {
                    "p1": { "x": 355, "y": 235 },
                    "p2": { "x": 355, "y": 175 },
                    "p3": { "x": 355, "y": 115 },
                    "p4": { "x": 355, "y": 55 }
                }
            },
            "left": {
                "frames": {
                    "f1": { "x": 0, "y": 64 },
                    "f2": { "x": 64, "y": 64 },
                    "f3": { "x": 128, "y": 64 },
                    "f4": { "x": 192, "y": 64 }
                },
                "clear": [150, 250, 300, 100],
                "positions": {
                    "p1": { "x": 355, "y": 235 },
                    "p2": { "x": 295, "y": 235 },
                    "p3": { "x": 235, "y": 235 },
                    "p4": { "x": 175, "y": 235 }
                }
            },
            "right": {
                "frames": {
                    "f1": { "x": 0, "y": 128 },
                    "f2": { "x": 64, "y": 128 },
                    "f3": { "x": 128, "y": 128 },
                    "f4": { "x": 192, "y": 128 }
                },
                "clear": [350, 250, 300, 100],
                "positions": {
                    "p1": { "x": 355, "y": 235 },
                    "p2": { "x": 415, "y": 235 },
                    "p3": { "x": 475, "y": 235 },
                    "p4": { "x": 535, "y": 235 }
                }
            }
        }

        function drawRoom() {
            let cross = new Image();
            let character = new Image();
            let coin = new Image();
            let key = new Image();

            cross.src = '/img/cross.png';
            character.src = '/img/leon.png';
            coin.src = '/img/coin.png';
            key.src = '/img/key.png';

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
            character.onload = () => ctx.drawImage(character, 0, 0, 64, 64, 355, 235, 96, 96);


            ctx.font = "30px Arial";

            ctx.fillText("Room: " + data.player.currentRoom, 10, 30);
            ctx.fillText("Keys: " + data.player.keys, 10, 65);
            ctx.fillText("Coins: " + data.player.coins, 10, 100);

            ctx.font = "18px Arial"
            ctx.fillText(data.message, 200, 40);

            if (data.room.target) ctx.fillText("Has GANAO", 200, 40);

            drawSide("N", data.room.walls.n.type, data.room.walls.n.open);
            drawSide("S", data.room.walls.s.type, data.room.walls.s.open);
            drawSide("E", data.room.walls.e.type, data.room.walls.e.open);
            drawSide("W", data.room.walls.w.type, data.room.walls.w.open);

            if (data.room.coin) {
                coin.onload = () => drawCoin(coin);
            }

            if (data.room.key) {
                key.onload = () => drawKey(key);
            }
        }

        function drawSide(side, type, doorOpen) {
            if (type == "wall") {
                drawWall(side);
            } else {
                if (!doorOpen) {
                    drawLockedDoor(side);
                }
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

        function drawCoin(coin) {
            ctx.drawImage(coin, 220, 400, 80, 80);
        }

        function drawKey(key) {
            ctx.drawImage(key, 490, 400, 80, 80);
        }

        canvas.addEventListener("mousedown", function (event) {
            const boundingRect = canvas.getBoundingClientRect();
            const x = event.clientX - boundingRect.left;
            const y = event.clientY - boundingRect.top;

            let character = new Image();
            character.src = '/img/leon.png';



            let start;
            const step = (dir) => (timestamp) => {
                console.log(dir);
                if (start == undefined) {
                    start = timestamp;
                }

                let elapsed = timestamp - start;
                lastTimestamp = timestamp;

                if (elapsed <= 600) {
                    if (elapsed <= 150) {
                        ctx.clearRect(dir.clear[0], dir.clear[1], dir.clear[2], dir.clear[3]);
                        ctx.drawImage(character, dir.frames.f1.x, dir.frames.f1.y, 64, 64, dir.positions.p1.x, dir.positions.p1.y, 96, 96);
                    } else if (elapsed <= 300) {
                        ctx.clearRect(dir.clear[0], dir.clear[1], dir.clear[2], dir.clear[3]);
                        ctx.drawImage(character, dir.frames.f2.x, dir.frames.f2.y, 64, 64, dir.positions.p2.x, dir.positions.p2.y, 96, 96);
                    } else if (elapsed <= 450) {
                        ctx.clearRect(dir.clear[0], dir.clear[1], dir.clear[2], dir.clear[3]);
                        ctx.drawImage(character, dir.frames.f3.x, dir.frames.f3.y, 64, 64, dir.positions.p3.x, dir.positions.p3.y, 96, 96);
                    } else {
                        ctx.clearRect(dir.clear[0], dir.clear[1], dir.clear[2], dir.clear[3]);
                        ctx.drawImage(character, dir.frames.f4.x, dir.frames.f4.y, 64, 64, dir.positions.p4.x, dir.positions.p4.y, 96, 96);
                    }

                    requestAnimationFrame(step(dir));
                }
            }

            if (event.button == 0) {
                console.log(x, y)

                if (x >= 707 && x <= 735 && y >= 472 && y <= 506) {
                    if (data.room.walls.n.type == "door") {
                        if (data.room.walls.n.open) {
                            requestAnimationFrame(step(directions.up));
                        }
                    }
                    setTimeout(() => window.location.assign("/nav?dir=N"), 600);
                }

                if (x >= 672 && x <= 705 && y >= 508 && y <= 535) {
                    if (data.room.walls.w.type == "door") {
                        if (data.room.walls.w.open) {
                            requestAnimationFrame(step(directions.left));
                        }
                    }                    
                    setTimeout(() => window.location.assign("/nav?dir=W"), 600);
                }

                if (x >= 735 && x <= 769 && y >= 508 && y <= 535) {
                    if (data.room.walls.e.type == "door") {
                        if (data.room.walls.e.open) {
                            requestAnimationFrame(step(directions.right));
                        }
                    }
                    setTimeout(() => window.location.assign("/nav?dir=E"), 600);
                }

                if (x >= 707 && x <= 735 && y >= 535 && y <= 570) {
                    if (data.room.walls.s.type == "door") {
                        if (data.room.walls.s.open) {
                            requestAnimationFrame(step(directions.down));
                        }
                    }
                    setTimeout(() => window.location.assign("/nav?dir=S"), 600);
                }

                if (y >= 400 && y <= 480) {
                    if (x >= 220 && x <= 300 && data.room.coin) {
                        window.location.assign("/getcoin");
                    }

                    if (x >= 490 && x <= 570 && data.room.key) {
                        window.location.assign("/getkey");
                    }
                }

                if (data.room.walls.n.type == "door" && !data.room.walls.n.open) {
                    if (x >= 353 && x <= 452 && y >= 72 && y <= 96) {
                        console.log("open north");
                        window.location.assign("/open?dir=N");

                    }
                }

                if (y >= 253 && y <= 352) {
                    if (data.room.walls.w.type == "door" && !data.room.walls.w.open) {
                        if (x >= 172 && x <= 196) {
                            console.log("open west");
                            window.location.assign("/open?dir=W");

                        }
                    }

                    if (data.room.walls.e.type == "door" && !data.room.walls.e.open) {
                        if (x >= 607 && x <= 631) {
                            console.log("open east");
                            window.location.assign("/open?dir=E");

                        }
                    }
                }

                if (data.room.walls.s.type == "door" && !data.room.walls.s.open) {
                    if (x >= 353 && x <= 452 && y >= 508 && y <= 531) {
                        console.log("open south");
                        window.location.assign("/open?dir=S");

                    }
                }
            }
        });
    </script>
</body>

</html>