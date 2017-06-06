package servlet;

import java.io.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.servlet.*;
import javax.servlet.http.*;
import test.*;
import java.util.*;


public class PassRequest extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		String target = "/admin_order_1.jsp";
		int flag = 0;
		request.setCharacterEncoding("utf-8");//请求编码(POST)
		response.setContentType("text/html;charset=utf-8");//响应编码
		
		HttpSession session = request.getSession();
		
		String building = request.getParameter("building");//获取请求
        String roomNum = request.getParameter("roomNum");
        String username = request.getParameter("username");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String longterm = request.getParameter("longterm");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String reason = request.getParameter("reason");
        
        String admin = (String) session.getAttribute("USERNAME");
        String adminname = (String) session.getAttribute("NAME");
//        System.out.println(building);
//        System.out.println(roomNum);
//        System.out.println(username);
//        System.out.println(date);
//        System.out.println(time);
//        System.out.println(longterm);
		try {
			
			
			if(longterm.equals("0")) {
				flag=1;
			SQLDB db = new SQLDB();
			String q = "delete from request where date = '" + date + "'" +
											 " and time = "+ time +
											 " and building= '" + building + "'" + 
											 " and roomNum = " + roomNum +
											 ";";
			//System.out.println(q);
			db.execute(q);
			
			q = "insert into reservation values('"+date+"',"+time+",'"+building+"',"+roomNum+",'"
												+username+"','"+name+"','"+email+"','"+reason+"');";			
			db.execute(q);
			
			q = "select * from time where timeNum="+time;
			ResultSet t = db.query(q);
			t.next();
			Email e = new Email();
			
			String content = "您好,"+name+"("+username+")"+"，您的预约：\n"
					+ "日期："+date+",时段："+t.getString(2)+"~"+t.getString(3)+",楼名："+building+",房间号："+roomNum+"\n"
					+ "已通过。";
			e.sendEmail(email, content);
			
			Log log = new Log();
			log.log_write("管理员"+adminname+"("+admin+")"+"通过了用户"+name+"("+username+")"+"的预约：\n"
					+ "日期："+date+",时段："+t.getString(2)+"~"+t.getString(3)+",楼名："+building+",房间号："+roomNum+"\n");
			
			t.close();
			db.close();}
			
			else {
				SQLDB db = new SQLDB();
				
				String q = "delete from request where date = '" + date + "'" +
						 " and time = "+ time +
						 " and building= '" + building + "'" + 
						 " and roomNum = " + roomNum +
						 " and longterm = "+longterm+";";
				db.execute(q);
				
				boolean f = true;
				Date d=new Date();   
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				int week=1000*60*60*24*7;
				for(int i=0;i<8;i++) {
					String ldate = sdf.format(new Date(sdf.parse(date).getTime()+(long)week*i));
					q = "select count(1) from reservation where date = '" + ldate + "'" +
														  " and time = "+ time +
														  " and building= '" + building + "'" + 
														  " and roomNum = " + roomNum +
														  ";";
					ResultSet crs = db.query(q);
					crs.next();
					if(crs.getInt(1)>0) {
						f=false;break;}
				}
				if(f) {
					flag=3;
					String ldate = date;
					for(int i=0;i<8;i++) {
						ldate = sdf.format(new Date(sdf.parse(date).getTime()+(long)week*i));
						q = "insert into reservation values('"+ldate+"',"+time+",'"+building+"',"+roomNum+",'"
								+username+"','"+name+"','"+email+"','"+reason+"');";
						db.execute(q);
					}
					q = "select * from time where timeNum="+time;
					ResultSet t = db.query(q);
					t.next();
					Email e = new Email();
					
					String content = "您好,"+name+"("+username+")"+"，您的长期预约：\n"
							+ "日期："+date+"~"+ldate+",时段："+t.getString(2)+"~"+t.getString(3)+",楼名："+building+",房间号："+roomNum+"\n"
							+ "已通过。";
					e.sendEmail(email, content);
					
					Log log = new Log();
					log.log_write("管理员"+adminname+"("+admin+")"+"通过了用户"+name+"("+username+")"+"的长期预约：\n"
							+ "日期："+date+"~"+ldate+",时段："+t.getString(2)+"~"+t.getString(3)+",楼名："+building+",房间号："+roomNum+"\n");
					
					t.close();
					db.close();
				} 
			}

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
