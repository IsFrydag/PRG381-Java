<%-- 
    Document   : dashboard
    Created on : 09 Jul 2025, 11:31:08
    Author     : Jana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "javax.servlet.http.HttpSession" %>
<%
 HttpSession session = request.getSession(false);
 if(session == nul || session.getAttribute("name")==null){
    response.sendRedirect("login.jsp");
    return;
    //this if statement just gets the current session- 
    //and if theres no session or no student/name it redirects to the login page
    }
    
    //below just retrieves the name of student from the current session
String name = (String) session.getAttribute("name");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <link rel="stylesheet" type="css" href="css/background.css">
    </head>
    <body>
        <h1>Hello, <%name%>!</h1>
        <div class="explain">
        <p>Your health is super important to us at BC, whether it's your physical health, mental health or your fingers(because obviously we cant code without them!)</p>
        </div>
        <form action ="LogoutServlet" method ="post">
            <button type ="submit" class ="logout-button">Logout</button>
        </form>
        
        
    </body>
</html>
