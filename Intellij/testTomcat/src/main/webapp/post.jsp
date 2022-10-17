<html>
<body>
    <h1> POST </h1>

    <%
        String a = request.getParameter("a");
        String b = request.getParameter("b");
        if (a == null || b == null) {
            %>
            <form method="post" action="/post.jsp">
                    Num A:  <input type="number" name="a">
                <br>
                    Num B: <input type="number" name="b">
                <br>
                <button type="submit">SUMA</button>
            </form>
        <%
        } else {
            int c = Integer.parseInt(a) + Integer.parseInt(b);
            out.print(a + " + " + b + " = " + c);
        }
    %>
</body>
</html>