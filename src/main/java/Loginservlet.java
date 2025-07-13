/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"Loginservlet"})
public class Loginservlet extends HttpServlet {
 
    //Database connection
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres"; 
    private static final String DB_PASSWORD = "password123";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        //retrieve values from form
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");

        try{
           
            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASSWORD);

            //database query
            String sql = "SELECT * FROM students WHERE email=? AND password=?";
            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1,email);
            query.setString(2,password);

            ResultSet result = query.executeQuery();

            if(result.next()){
                    
                    HttpSession session = request.getSession();
                    session.setAttribute("userEmail", email);
                    session.setAttribute("userName", result.getString("name"));
                    
                    response.sendRedirect("dashboard.jsp");
                    
            }else{

            request.setAttribute("message","Incorrect email or password.Please try again");
          request.getRequestDispatcher("login.jsp").forward(request,response);
          
           } 


           result.close();
           query.close();
           conn.close();

    } catch(ServletException e){
        
        request.setAttribute("message","Login error:" + e.getMessage());
        request.getRequestDispatcher("login.jsp").forward(request,response);
}
}
    
        
    }

    

   


