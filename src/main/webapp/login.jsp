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
            
            <h1>Welcome to BC Student Wellness Management System</h1>
                <h2>User Login</h2>
                
                <% if (session.getAttribute("loginMessage") != null) { %>
                
                    <p class="success"><%= session.getAttribute("loginMessage") %></p>
                    <script>
                        setTimeout(function() {
                            window.location.href = "dashboard.jsp";
                        }, 2000); // Redirect after 2 seconds
                    </script>
                <% } else if (request.getAttribute("message") != null) { %>
                    <p class="error"><%= request.getAttribute("message") %></p>
                    
                <% } %>
            
                <form name="Login" action="LoginServlet" method="POST">
                    
                    <label for="email">Email:</label>
                    <input id="email" type="text" name="email" value="" size="50" placeholder="Email" required />
                    
                    <label for="password">Password:</label>
                    <input id="password" type="password" name="password" value="" size="50" placeholder="Password" required/>
                    
                    <input type="submit" value="LOGIN" name="btnlogin" />
                    
                </form>
        
            </div>
            
        </div>
    </body>
</html>

