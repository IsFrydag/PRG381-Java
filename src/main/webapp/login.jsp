<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/background.css">
        
    </head>
    <body>
        <div class="login-container">
            
            <div class="welcome">
                
                <h1 id="Welcomeh1">Welcome to BC Student Wellness Management System</h1>
                
            </div>
        
            <div class="login">
                
            <h2 id="h2">User Login</h2>
            
                <form name="Login" action="login.jsp" method="POST">
                    
                    <label for="email">Email:</label>
                    <input id="email" type="text" name="txtEmail" value="" size="50" placeholder="Email" required />
                    
                    <label for="password">Password:</label>
                    <input id="password" type="password" name="txtPassword" value="" size="50" placeholder="Password" required/>
                    
                    <input type="submit" value="LOGIN" name="btnlogin" />
                    
                </form>
        
            </div>
            <%
            
            //Checks if the form has been submitted before retrieving the values
            if("POST".equalsIgnoreCase(request.getMethod())){
            
            //Retrieving values from input 
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            
                }
            %>
        </div>
    </body>
</html>

