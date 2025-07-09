<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN</title>
        
        <link rel="stylesheet" href="css/styles.css">
        
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
            
            <label for="studentNumber">Student Number:</label><br>
            <input id="studentNumber" type="text" name="student_number" placeholder="e.g., 600501" required pattern="[0-9]{6}" title="6-digit number"><br>

            <label for="name">Name:</label><br>
            <input id="name" type="text" name="name" placeholder="Your Name" required><br>

            <label for="surname">Surname:</label><br>
            <input id="surname" type="text" name="surname" placeholder="Your Surname" required><br>

            <label for="email">Student Email:</label><br>
            <input id="email" type="email" name="email" placeholder="e.g., 600501@student.ac.za" required><br>

            <label for="phone">Phone:</label><br>
            <input id="phone" type="tel" name="phone" placeholder="e.g., 0821234567" required pattern="[0-9]{10}" title="10-digit number"><br>

            <label for="password">Password:</label><br>
            <input id="password" type="password" name="password" placeholder="Password" required pattern=".{8,}" title="At least 8 characters"><br>

            <button id="registerButton" type="submit">Register</button>
            
        </form>

        <% if (request.getAttribute("message") != null) { %>
            <p style="color: red;"><%= request.getAttribute("message") %></p>
        <% } %>
        
    </body>
</html>
