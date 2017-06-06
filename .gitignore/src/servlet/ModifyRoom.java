package servlet;

import java.io.*;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;
import test.*;
import java.util.*;


public class ModifyRoom extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		String target = "/admin_room_1.jsp";
		int flag = 1;
		request.setCharacterEncoding("utf-8");//请求编码(POST)
		response.setContentType("text/html;charset=utf-8");//响应编码
		
		String old_building = request.getParameter("old_building");//获取请求
		String old_roomNum = request.getParameter("old_roomNum");
		String building = request.getParameter("building");
		String roomNum = request.getParameter("roomNum");
		String available = request.getParameter("available");
		String rank = request.getParameter("rank");
        
		try {
			SQLDB db = new SQLDB();
			String q = "delete from room where building='"+old_building+"' and roomNum='"+old_roomNum+"';";
			db.execute(q);
			
			q="insert into room values('"+building+"',"+roomNum+","+available+","+rank+");";
			db.execute(q);
			
			
		} catch (Exception e) {
			flag=0;
			e.printStackTrace();
		}
		request.setAttribute("building",building);
		request.setAttribute("roomNum",roomNum);
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