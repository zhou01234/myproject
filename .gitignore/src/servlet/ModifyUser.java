package servlet;

import java.io.*;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;
import test.*;
import java.util.*;


public class ModifyUser extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		String target = "/admin_user_1.jsp";
		int flag = 1;
		request.setCharacterEncoding("utf-8");//«Î«Û±‡¬Î(POST)
		response.setContentType("text/html;charset=utf-8");//œÏ”¶±‡¬Î
		
		String username = request.getParameter("username");
		String rank = request.getParameter("rank");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
        
		try {
			SQLDB db = new SQLDB();
			String q = "delete from user where username='"+username+"';";
			db.execute(q);
			
			q="insert into user values('"+username+"','123456',"+rank+",'"+email+"','"+name+"');";
			db.execute(q);
			
			
		} catch (Exception e) {
			flag=0;
			e.printStackTrace();
		}
		request.setAttribute("username",username);
		
		request.setAttribute("flag",flag);
		//System.out.println(flag);
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}