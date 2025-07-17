/*

* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license

* Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template

*/
import java.io.IOException; // Handles input/output exceptions, used for servlet request/response operations
import java.sql.Connection; // Provides a connection to the database, used to interact with PostgreSQL
import java.sql.DriverManager; // Manages JDBC driver connections, used to establish a connection to the database
import java.sql.PreparedStatement; // Enables precompiled SQL statements with parameters, used for secure database queries
import java.sql.ResultSet; // Represents the result set from a database query, used to retrieve query results
import java.sql.SQLException; // Handles SQL-related exceptions, used for database error management
import jakarta.servlet.ServletException; // Handles servlet-specific exceptions, used for servlet lifecycle errors
import jakarta.servlet.annotation.WebServlet; // Provides annotation-based servlet configuration, used to map the servlet to a URL
import jakarta.servlet.http.HttpServlet; // Base class for HTTP servlets, extended to handle HTTP requests
import jakarta.servlet.http.HttpServletRequest; // Represents an HTTP request, used to retrieve form data and session information
import jakarta.servlet.http.HttpServletResponse; // Represents an HTTP response, used to send redirects or forward requests
import jakarta.servlet.http.HttpSession; // Manages user session data, used to store user information after login

// Maps this servlet to the "/Loginservlet" URL pattern
@WebServlet(urlPatterns = {"/Loginservlet"})

public class Loginservlet extends HttpServlet{
 
    // Database connection URL for PostgreSQL
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    // Database username
    private static final String DB_USER = "postgres";
    // Database password
    private static final String DB_PASSWORD = "password123";
 
    // Handles GET requests by forwarding to the login.jsp page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forwards the request to the login page
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
 
    // Handles POST requests for user login
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieves email and password from the form submission
        String email = request.getParameter("email");
        String password = request.getParameter("password");
 
        // Initialize database connection, statement, and result set variables
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        String message = null;
 
        try {
            // Loads the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
 
            // Establishes a connection to the database
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
 
            // Prepares an SQL query to check if the email and password exist in the students table
            String sql = "SELECT * FROM students WHERE email = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            // Sets the email and password parameters for the query
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            // Executes the query and stores the result
            result = pstmt.executeQuery();
 
            // Checks if a matching record was found
            if (result.next()) {
                // Logs successful login to the console
                System.out.println("Login successful for email: " + email);
                // Creates or retrieves the user's session
                HttpSession session = request.getSession();
                
                // Stores user email and name in the session
                session.setAttribute("userEmail", email);
                session.setAttribute("userName", result.getString("name"));
                // Stores a success message in the session for display on the dashboard
                session.setAttribute("loginMessage", "Login successful! Welcome, " + result.getString("name") + ".");

                // Redirects to the dashboard page
                response.sendRedirect("dashboard.jsp");
                return;
            } else {
                // Sets an error message for invalid credentials
                message = "Incorrect email or password. Please try again.";
            }
 
        } catch (ClassNotFoundException e) {
            // Sets an error message if the JDBC driver is not found
            message = "JDBC Driver not found.";
            e.printStackTrace();
        } catch (SQLException e) {
            // Sets an error message for database-related errors
            message = "Database error - " + e.getMessage();
            e.printStackTrace();
        } finally {
            // Closes database resources to prevent leaks
            try {
                if (result != null) result.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                // Logs any errors that occur while closing resources
                e.printStackTrace();
            }
        }
 
        // Sets the error message as a request attribute and forwards back to the login page
        request.setAttribute("message", message);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
 
    // Provides a description of the servlet
    @Override
    public String getServletInfo() {
        return "Login Servlet handles user authentication.";
    }

}
 