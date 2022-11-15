<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Exemple</title>
</head>
<body>
    

    <script id="mydata" type="application/json">
        ${myjson}
    </script>

    <script>
        let dataScript = document.getElementById("mydata").textContent;
        let data = JSON.parse(dataScript);
        console.log(data.walls.N);
    </script>
</body>
</html>

<!-- 

    String myjson = service.getJsonInfo();
    req.setAttribute("myjson", myjson);

    --
    *Displays*
    {
        "walls": {
            "N": wall,
            "S": door,
        } 
    }

 -->