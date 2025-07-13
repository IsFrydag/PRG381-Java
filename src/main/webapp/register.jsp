<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        
        <link rel="stylesheet" href="css/background.css">
        
    </head>
    
    
    
    <body>
        
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
            </div>
        </div>
        
        <form method="POST" action="RegisterServlet">
            
            <label for="name">Name:</label><br>
            <input id="name" type="text" name="name" placeholder="Your Name" required><br>

            <label for="surname">Surname:</label><br>
            <input id="surname" type="text" name="surname" placeholder="Your Surname" required><br>

            <label for="email">Email:</label><br>
            <input id="email" type="email" name="email" placeholder="e.g., ...@student.ac.za" required><br>

            <label for="password">Password:</label><br>
            <input id="password" type="password" name="password" placeholder="Password" required pattern=".{8,}" title="At least 8 characters"><br>

            <button id="registerButton" type="submit">Register</button>
            
        </form>

        <% if (request.getAttribute("message") != null) { %>
            <p style="color: red;"><%= request.getAttribute("message") %></p>
        <% } %>
        
    </body>
</html>
