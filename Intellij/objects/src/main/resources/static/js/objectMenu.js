function displayMenu(bucketName) {
    let menu = document.getElementById("menu");

    let htmlPlaneText = `<form action="/objects/${bucketName}" method="POST" enctype="multipar/form-data">`;
    
    htmlPlaneText += `<label for="file">File: </label>`;
    htmlPlaneText += `<input type="file" name="file" required><br>`;

    htmlPlaneText += `<label for="path">Path: </label>`;
    htmlPlaneText += `<input type="text" name="path" required><br>`;
    
    htmlPlaneText += `<input type="submit" value="Create"><br>`;
    htmlPlaneText += '</form><br>';
    htmlPlaneText += `<button onclick="hideMenu()">Cancel</button>`;

    menu.innerHTML = htmlPlaneText;
}

function hideMenu(bucketName) {
    let menu = document.getElementById("menu");

    menu.innerHTML = `<button onclick="displayMenu(${bucketName})">New object</button>`
}