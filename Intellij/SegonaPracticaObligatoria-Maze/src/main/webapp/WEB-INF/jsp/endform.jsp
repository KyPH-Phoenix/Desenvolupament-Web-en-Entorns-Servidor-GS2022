<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Guanyador</title>
    
    <!-- Bootstrap Stylesheet -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body class="pl-5 pt-3 vw-100 vh-100">
    <main class="d-flex flex-column justify-content-center align-items-center w-100 h-75">
        <h1>Has Guanyat</h1>
        <h3>${mapName} - Temps: ${time}</h3>
        <h3>Registra la teva victòria</h3>
        <form action="/endform" method="post" class="d-flex flex-column pb-1">
            <label for="userName"></label>
            <input type="text" name="userName" id="userName" maxlength="20" class="mb-2">
            <input type="submit" value="Registra" class="btn btn-primary py-2">  
        </form>
    </main>
</body>
</html>