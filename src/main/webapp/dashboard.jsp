<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.HttpSession" %>
<%
    session = request.getSession(false);
    if (session == null || session.getAttribute("name") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    
    String name = (String) session.getAttribute("name");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <link rel="stylesheet" type="text/css" href="css/background.css">
    </head>
    <body>
        <h1>Hello, <%=name%>!</h1>
        <div class="explain">
            <p>Your health is super important to us at BC, whether it's your physical health, mental health or your fingers (because obviously we can't code without them!)</p>
        </div>
        <form action="LogoutServlet" method="post">
            <button type="submit" class="logout-button">Logout</button>
        </form>
    </body>
</html>