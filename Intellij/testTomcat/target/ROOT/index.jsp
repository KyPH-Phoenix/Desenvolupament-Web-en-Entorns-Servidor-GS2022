<html>
<body>
<h2>Hello World!</h2>

<%
    for(int size = 0; size < 5; size++) { %>
        <font size="<%= size %>">
            Demo
        </font>
<%
    }
%>

</body>
</html>
