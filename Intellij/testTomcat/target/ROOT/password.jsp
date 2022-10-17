<html>
<body>
    <h1> LOGIN </h1>
    <%
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        if (user == null || pass == null) {
            %>
            <form method="post" action="/password.jsp">
                User:  <input type="text" name="user">
            <br>
                Pass: <input type="password" name="pass">
            <br>
                <input type="submit" value="envia">
            </form>
            <%
        } else {
            if (user.equals("liceu") && pass.equals("1234")) {
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "/private.jsp");
                out.print("OK");
            } else {
                user = null;
                pass = null;
                response.sendError(401, "Caca de la vaca");
            }
        }
        %>
</body>
</html>