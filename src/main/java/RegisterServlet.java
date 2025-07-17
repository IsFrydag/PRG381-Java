/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException; // Handles input/output exceptions, used for servlet request/response operations
import java.io.PrintWriter; // Provides a way to write text data to the response, used in processRequest for HTML output
import java.sql.Connection; // Provides a connection to the database, used to interact with PostgreSQL
import java.sql.DriverManager; // Manages JDBC driver connections, used to establish a connection to the database
import java.sql.PreparedStatement; // Enables precompiled SQL statements with parameters, used for secure database inserts
import java.sql.SQLException; // Handles SQL-related exceptions, used for database error management
import jakarta.servlet.ServletException; // Handles servlet-specific exceptions, used for servlet lifecycle errors
import jakarta.servlet.annotation.WebServlet; // Provides annotation-based servlet configuration, used to map the servlet to a URL
import jakarta.servlet.http.HttpServlet; // Base class for HTTP servlets, extended to handle HTTP requests
import jakarta.servlet.http.HttpServletRequest; // Represents an HTTP request, used to retrieve form data
import jakarta.servlet.http.HttpServletResponse; // Represents an HTTP response, used to send redirects or forward requests

// Maps this servlet to the "/RegisterServlet" URL pattern
@WebServlet(urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    // Database connection URL for PostgreSQL
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    // Database username
    private static final String DB_USER = "postgres";
    // Database password
    private static final String DB_PASSWORD = "password123";
    
    // Processes both GET and POST requests, currently outputs a simple HTML page (not typically used)
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Sets the response content type to HTML
        response.setContentType("text/html;charset=UTF-8");
        // Creates a PrintWriter to write HTML content to the response
        try (PrintWriter out = response.getWriter()) {
            // Outputs a basic HTML page with the servlet's context path
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // Handles GET requests by forwarding to the register.jsp page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forwards the request to the registration page
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    // Handles POST requests for student registration
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieves form parameters for registration
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Initialize database connection and statement variables
        Connection conn = null;
        PreparedStatement pstmt = null;
        String message = null;

        try {
            // Loads the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establishes a connection to the database
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // Prepares an SQL query to insert a new student record
            String sql = "INSERT INTO students (name, surname, email, password) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            // Sets the form parameters for the SQL query
            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.setString(3, email);
            pstmt.setString(4, password);

            // Executes the insert query and gets the number of affected rows
            int rowsAffected = pstmt.executeUpdate();
            // Logs the number of rows affected for debugging
            System.out.println("Rows affected: " + rowsAffected);

            // Checks if the insert was successful
            if (rowsAffected > 0) {
                // Redirects to the success page on successful registration
                response.sendRedirect("success.jsp");
                return;
            } else {
                // Sets an error message for failed registration
                message = "Registration failed. Please try again.";
            }

        } catch (ClassNotFoundException e) {
            // Sets an error message if the JDBC driver is not found
            message = "JDBC Driver not found. Make sure the PostgreSQL JDBC JAR is in your project libraries (pom.xml).";
            e.printStackTrace();
        } catch (SQLException e) {
            // Checks for unique constraint violation (e.g., duplicate email)
            if (e.getSQLState().equals("23505")) { // 23505 is the SQLSTATE for unique violation
                message = "Registration failed: Email already exists. 😓";
            } else {
                // Sets a generic database error message
                message = "Database error: " + e.getMessage();
            }
            e.printStackTrace();
        } finally {
            // Closes database resources to prevent leaks
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                // Logs any errors that occur while closing resources
                e.printStackTrace();
            }
        }

        // Sets the error message as a request attribute and forwards back to the registration page
        request.setAttribute("message", message);
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    // Provides a description of the servlet
    @Override
    public String getServletInfo() {
        return "Register Servlet handles student registration.";
    }
}
