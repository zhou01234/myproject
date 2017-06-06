package servlet;

import java.io.*;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;
import test.*;
import java.util.*;


public class RemoveUserReservation extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		String target = "/user_reserv_3.jsp";
		request.setCharacterEncoding("utf-8");//�������(POST)
		response.setContentType("text/html;charset=utf-8");//��Ӧ����
		
		HttpSession session = request.getSession();
		
		String building = request.getParameter("building");//��ȡ����
        String roomNum = request.getParameter("roomNum");       
        String date = request.getParameter("date");
        String time = request.getParameter("time");
		String username = (String) session.getAttribute("USERNAME");
		try {
			SQLDB db = new SQLDB();			
			String q = "delete from reservation where date = '" + date + 
					 "' and time = "+ time +
					 " and building= '" + building +
					 "' and roomNum = " + roomNum +
					 " and username = '" + username +
					 "';";
			db.execute(q);
			
		} catch (Exception e) { 
			e.printStackTrace();		 
		}
		
		ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
        
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}