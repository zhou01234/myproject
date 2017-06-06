package servlet;

import java.io.*;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;
import test.*;
import java.util.*;


public class SendRequest extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		String target = "/user_reserv_1.jsp";
		
		request.setCharacterEncoding("utf-8");//请求编码(POST)
		response.setContentType("text/html;charset=utf-8");//响应编码
		
		HttpSession session = request.getSession();
		
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String building = request.getParameter("building");
		String roomNum = request.getParameter("roomNum");
		String reason = request.getParameter("reason");
		String longterm = request.getParameter("longterm");
		String username = (String) session.getAttribute("USERNAME");
		String rankstr = (String) session.getAttribute("RANK");
		String email = (String) session.getAttribute("EMAIL");
		String name = (String) session.getAttribute("NAME");
		
		if(longterm==null) longterm="0";
		int rank = Integer.parseInt(rankstr);
		int flag = 0;		
		
		try {
			SQLDB db = new SQLDB();			
			String q;
			q="select * from room where building='"+building+"' and roomNum="+roomNum;
			ResultSet rs = db.query(q);
			if(!rs.next())
				flag=1;//房间不存在 
			
			else {
				int rr = rs.getInt(4);
				if(rank<rr)
					flag=2;//权限不够
				else
					if(rank>rr) {
						q = "insert into reservation "
						+ "values('"+date+"',"+time+",'"+building+"',"
						+ roomNum+",'"+username+"','"+name+"','"+email+"','"+reason+"')";
						//System.out.println(db.execute(q));
						db.execute(q);
						flag=3;//预约成功
					}
					else {
						q = "insert into request "
								+ "values('"+date+"',"+time+",'"+building+"',"
								+ roomNum+",'"+username+"',"+longterm+",'"+name+"','"+email+"','"+reason+"')";
						db.execute(q);
						flag=4;//成功发出申请
					}			
			}
			
			System.out.println(date+","+time+","+building+","+roomNum+","+reason+
					","+longterm+","+username+","+rank+","+email+","+name);
		
	        
		} catch (Exception e) { 
			flag=5;
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