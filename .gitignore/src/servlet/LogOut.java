package servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

public class LogOut extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();  
		
        session.invalidate();      
        response.sendRedirect("login.html");  
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
