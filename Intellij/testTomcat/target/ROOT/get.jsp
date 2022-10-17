<html>
<body>
    <h1> GET </h1>

    <p>
        <%
            int a = Integer.parseInt(request.getParameter("a"));
            int b = Integer.parseInt(request.getParameter("b"));
        %>

        <%= a %>
        +
        <%= b %>
        =
        <%= a + b %>
    </p>
</body>
</html>