package servlet;

import java.io.*;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;
import test.*;
import java.util.*;


public class RemoveUser extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		String target = "/admin_user_1.jsp";
		int flag=2;
		request.setCharacterEncoding("utf-8");//«Î«Û±‡¬Î(POST)
		response.setContentType("text/html;charset=utf-8");//œÏ”¶±‡¬Î
		
		String username = request.getParameter("username");
		
		
		try {
			SQLDB db = new SQLDB();			
			String q = "delete from user where username='"+username+"';";
			db.execute(q);
			db.close();
		} catch (Exception e) {  
			e.printStackTrace();		 
		}
		request.setAttribute("flag",flag);
		
		ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
        
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}