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
                // Retrieves the current session, if it exists
                if (session == null || session.getAttribute("loginMessage") == null) {
                    response.sendRedirect("login.jsp"); // Redirect back to login if login failed
                    // Stops further processing of the page
                    return;
                    String name = (String) session.getAttribute("name");
                }
            %>
            <h1 class="DashboardTitle"> Hello there!</h1>
            <div class="Dashexplain">
                <p>Your health is super important to us at BC, whether it's your physical health, mental health or your fingers (because obviously we can't code without them!)</p>
            </div>
            <form action="LogoutServlet" method="post">
                <button type="submit" class="logout-button">Logout</button>
            </form>
        </div>
    </body>
</html>