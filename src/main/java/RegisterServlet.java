/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres"; // Your PostgreSQL username
    private static final String DB_PASSWORD = "password123";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
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

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve form parameters
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement pstmt = null;
        String message = null;

        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish the database connection
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            String sql = "INSERT INTO students (name, surname, email, password) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.setString(3, email);
            pstmt.setString(4, password);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected); // Debugging

            if (rowsAffected > 0) {
                response.sendRedirect("success.jsp");
                return;
            } else {
                message = "Registration failed. Please try again.";
            }

        } catch (ClassNotFoundException e) {
            message = "JDBC Driver not found. Make sure the PostgreSQL JDBC JAR is in your project libraries (pom.xml).";
            e.printStackTrace();
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) { // 23505 is the SQLSTATE for unique violation
                message = "Registration failed: Email already exists. 😓";
            } else {
                message = "Database error: " + e.getMessage();
            }
            e.printStackTrace();
        } finally {
            // Close resources in a finally block
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Set message as request attribute and forward back to the registration page
        request.setAttribute("message", message);
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Register Servlet handles student registration.";
    }
}
