function displayMenu() {
    let menu = document.getElementById("menu");

    let htmlPlaneText = `<form action="/objects" method="POST">`;
    htmlPlaneText += `<label for="name">Name: </label>`;
    htmlPlaneText += `<input type="text" name="name" required><br>`;
    htmlPlaneText += `<input type="submit" value="Create"><br>`
    htmlPlaneText += '</form><br>'
    htmlPlaneText += `<button onclick="hideMenu()">Cancel</button>`

    menu.innerHTML = htmlPlaneText;
}

function hideMenu() {
    let menu = document.getElementById("menu");

    menu.innerHTML = `<button onclick="displayMenu()">New bucket</button>`
}