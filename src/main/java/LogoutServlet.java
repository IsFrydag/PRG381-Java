/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
//instead of javax its jakarta due to me using Tomcat+
//in JakartaEE9+ all javax packages are renamed to jakarta for some reason
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author jana
 */
@WebServlet(urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //so if the session does exist this obtains it
        HttpSession session = request.getSession(false);
        if(session != null){
            //here it invalidates the session to log out the student(or who ever logs out)
            session.invalidate();
            
        }
        response.sendRedirect("login.jsp");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    processRequest(request, response);
    
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    processRequest(request, response);
    
    }
    @Override
    public String getServletInfo(){
        return "Handle the logout and redirects to login.jsp";
    
    
    }
}
