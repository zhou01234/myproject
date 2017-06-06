package servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import test.SQLDB;

public class ModifyEmail extends HttpServlet {
	
	String target = "_person_1.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//«Î«Û±‡¬Î(POST)
		response.setContentType("text/html;charset=utf-8");//œÏ”¶±‡¬Î
		HttpSession session = request.getSession();
		
		String email = (String)session.getAttribute("EMAIL");
		String username = (String)session.getAttribute("USERNAME");
		String role = (String)session.getAttribute("ROLE");
		
		target= "/" + role + target;
		
		try {
			SQLDB db = new SQLDB();
			db.execute("update "+role+" set email='"+email+"' where username='"+username+"';");
			
			
		} catch (Exception e) {
			e.printStackTrace();}
		
		ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
