/*

* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license

* Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template

*/
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/Loginservlet"})

public class Loginservlet extends HttpServlet{
 
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "password123";
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

        // Retrieve values from form
        String email = request.getParameter("email");
        String password = request.getParameter("password");
 
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        String message = null;
 
        try{

            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
 
            //Database connection
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
 
            // Database query
            String sql = "SELECT * FROM students WHERE email = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            result = pstmt.executeQuery();
 
            if (result.next()){
      
                System.out.println("Login successful for email: " + email);
                HttpSession session = request.getSession();
                
                session.setAttribute("userEmail", email);
                session.setAttribute("userName", result.getString("name"));

                session.setAttribute("loginMessage", "Login successful! Welcome, " + result.getString("name") + ".");

                response.sendRedirect("dashboard.jsp");
                return;
            }else{
                
                message = "Incorrect email or password. Please try again.";

            }
 
        }catch(ClassNotFoundException e){

            message = "JDBC Driver not found.";
            e.printStackTrace();

        }catch (SQLException e){

            message = "Database error - " + e.getMessage();
            e.printStackTrace();

        }finally{
            try{

                if (result != null) result.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();

            }catch(SQLException e){

                e.printStackTrace();

            }

        }
 
        // Set message as request attribute and forward back to the login page
        request.setAttribute("message", message);
        request.getRequestDispatcher("login.jsp").forward(request, response);

    }
 
    @Override
    public String getServletInfo(){

        return "Login Servlet handles user authentication.";

    }

}
 