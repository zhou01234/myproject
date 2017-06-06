package servlet;

import java.io.*;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;
import test.*;
import java.util.*;


public class RemoveRequest extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		String target = "/admin_order_1.jsp";
		int flag=2;
		request.setCharacterEncoding("utf-8");//请求编码(POST)
		response.setContentType("text/html;charset=utf-8");//响应编码
		
		String building = request.getParameter("building");//获取请求
        String roomNum = request.getParameter("roomNum");
        String username = request.getParameter("username");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
		
		try {
			SQLDB db = new SQLDB();			
			String q = "delete from request where date = '" + date + 
					 "' and time = "+ time +
					 " and building= '" + building +
					 "' and roomNum = " + roomNum +
					 " and username = '" + username +
					 "';";
			db.execute(q);
			
		} catch (Exception e) { 
			flag=0;
			e.printStackTrace();		 
		}
		request.setAttribute("flag",flag);
		request.setAttribute("username",username);
		
		ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
        
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}