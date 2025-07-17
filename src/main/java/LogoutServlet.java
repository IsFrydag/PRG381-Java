/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.IOException; // Handles input/output exceptions, used for servlet response operations
import jakarta.servlet.ServletException; // Handles servlet-specific exceptions, used for servlet lifecycle errors
import jakarta.servlet.annotation.WebServlet; // Provides annotation-based servlet configuration, used to map the servlet to a URL
import jakarta.servlet.http.HttpServlet; // Base class for HTTP servlets, extended to handle HTTP requests
import jakarta.servlet.http.HttpServletRequest; // Represents an HTTP request, used to retrieve session information
import jakarta.servlet.http.HttpServletResponse; // Represents an HTTP response, used to send redirects
import jakarta.servlet.http.HttpSession; // Manages user session data, used to invalidate the session during logout

// Maps this servlet to the "/LogoutServlet" URL pattern
@WebServlet(urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {
    // Handles POST requests for user logout
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieves the current session, if it exists; false prevents creating a new session
        HttpSession session = request.getSession(false);
        if (session != null) {
            // Invalidates the session, clearing all attributes (e.g., loginMessage, userEmail)
            session.invalidate();
        }
        // Redirects to the index.jsp page after logout
        response.sendRedirect("index.jsp");
    }
}