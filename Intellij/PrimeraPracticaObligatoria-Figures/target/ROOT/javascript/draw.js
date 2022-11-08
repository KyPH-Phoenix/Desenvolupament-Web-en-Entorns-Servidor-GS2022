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