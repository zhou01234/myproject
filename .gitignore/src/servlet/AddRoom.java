package servlet;

import java.io.*;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;
import test.*;
import java.util.*;


public class AddRoom extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		String target = "/admin_room_2.jsp";
		boolean flag=true;
		request.setCharacterEncoding("utf-8");//«Î«Û±‡¬Î(POST)
		response.setContentType("text/html;charset=utf-8");//œÏ”¶±‡¬Î
		
		String building = request.getParameter("building");
		String roomNum = request.getParameter("roomNum");
		String available = request.getParameter("available");
		String rank = request.getParameter("rank");
        
		try {
			SQLDB db = new SQLDB();
			String q;
			
			q="insert into room values('"+building+"',"+roomNum+","+available+","+rank+");";
			db.execute(q);
			
		} catch (Exception e) {
			flag=false;   
			e.printStackTrace();		 
		}
		
		request.setAttribute("flag",flag);
		request.setAttribute("building",building);
		request.setAttribute("roomNum",roomNum);
		ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
        
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}