package servlet;

import java.io.*;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;
import test.*;
import java.util.*;


public class RemoveRoom extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		String target = "/admin_room_1.jsp";
		int flag=2;
		request.setCharacterEncoding("utf-8");//�������(POST)
		response.setContentType("text/html;charset=utf-8");//��Ӧ����
		
		String building = request.getParameter("building");
		String roomNum = request.getParameter("roomNum");
		
		try {
			SQLDB db = new SQLDB();			
			String q = "delete from room where building='"+building+"' and roomNum='"+roomNum+"';";
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