<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html>
    <head>
        <!-- Specifies the file type -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Dashboard</title>
        
        <link rel="stylesheet" href="css/background.css"> 
    </head>
    <body>
        <!-- Wrapper for background css. Each div is a moving object. -->
        <div class="wrapper">
            <div class="box">
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
            </div>
            
            <!-- JSP scriptlet to handle session validation -->
            <%
    session = request.getSession(false);
    if (session == null || session.getAttribute("fullName") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String fullName = (String) session.getAttribute("fullName");
%>

<h1 class="DashboardTitle">Hello <%= fullName %>!</h1>

            <div class="Dashexplain">
                <p>Your health is super important to us at BC, whether it's your physical health, mental health or your fingers (because obviously we can't code without them!)</p>
            </div>
            <form action="LogoutServlet" method="post">
                <button type="submit" class="logout-button">Logout</button>
            </form>
        </div>
    </body>
</html>