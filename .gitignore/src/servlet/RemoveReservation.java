package servlet;

import java.io.*;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;
import test.*;
import java.util.*;


public class RemoveReservation extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		String target = "/admin_order_2.jsp";
		int flag=1;
		request.setCharacterEncoding("utf-8");//�������(POST)
		response.setContentType("text/html;charset=utf-8");//��Ӧ����
		
		HttpSession session = request.getSession();
		
		String building = request.getParameter("building");//��ȡ����
        String roomNum = request.getParameter("roomNum");
        String username = request.getParameter("username");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String admin = (String) session.getAttribute("USERNAME");
        String adminname = (String) session.getAttribute("NAME");
        
		try {
			SQLDB db = new SQLDB();			
			String q = "delete from reservation where date = '" + date + 
					 "' and time = "+ time +
					 " and building= '" + building +
					 "' and roomNum = " + roomNum +
					 " and username = '" + username +
					 "';";
			db.execute(q);
			
			q = "select * from time where timeNum="+time;
			ResultSet t = db.query(q);
			t.next();
			Email e = new Email();
			
			String content = "����,"+name+"("+username+")"+"������ԤԼ��\n"
					+ "���ڣ�"+date+",ʱ�Σ�"+t.getString(2)+"~"+t.getString(3)+",¥����"+building+",����ţ�"+roomNum+"\n"
					+ "��ǿ��ȡ����";
			e.sendEmail(email, content);
			
			Log log = new Log();
			log.log_write("����Ա"+adminname+"("+admin+")"+"ǿ��ȡ�����û�"+name+"("+username+")"+"��ԤԼ��\n"
					+ "���ڣ�"+date+",ʱ�Σ�"+t.getString(2)+"~"+t.getString(3)+",¥����"+building+",����ţ�"+roomNum+"\n");
			
			t.close();
			db.close();
			
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