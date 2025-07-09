<%-- 
    Document   : login
    Created on : 09 Jul 2025, 11:30:59
    Author     : Heiner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          <h1 id="h1">Welcome to BC Student Wellness Management System</h1>
        
        <div class="form">
            <h2 id="h2">User Login</h2>
                <form name="Login" action="login.jsp" method="POST">
                
                    <h3 id="h3">Email:</h3>
                    <input type="text" name="txtEmail" value="" size="50" placeholder="Email" />
         
                    <h4 id="h4">Password:</h4>
                    <input type="password" name="txtPassword" value="" size="50" placeholder="Password"/>
                    
                    <input type="submit" value="LOGIN" name="btnlogin" />
                    
                </form>
        </div>

    </body>
</html>
