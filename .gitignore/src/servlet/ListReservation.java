package servlet;

import java.io.*;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;
import test.*;
import java.util.*;


public class ListReservation extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		String target = "/admin_order_2#.jsp";
		
		request.setCharacterEncoding("utf-8");//请求编码(POST)
		response.setContentType("text/html;charset=utf-8");//响应编码
		
		String building = request.getParameter("building");//获取请求
        String roomNum = request.getParameter("roomNum");
        String username = request.getParameter("username");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String cp = request.getParameter("cp");
        if(cp==null) cp="1";
        int currentpage = Integer.parseInt(cp);
        
//        System.out.println(building);
//        System.out.println(roomNum);
//        System.out.println(username);
//        System.out.println(date);
//        System.out.println(time);
//        System.out.println(cp);
        
        
        int pagesize = 15;
        int viewpages = 5;
        
		try {
			SQLDB db = new SQLDB();
			String q;
			if(!building.equals(""))
				db.execute("create temporary table temp1 select * from reservation where building='"+building+"';");
			else db.execute("create temporary table temp1 select * from reservation");
			
			if(!roomNum.equals(""))
				db.execute("create temporary table temp2 select * from temp1 where roomNum="+roomNum);
			else db.execute("create temporary table temp2 select * from temp1");
			
			if(!username.equals(""))
				db.execute("create temporary table temp3 select * from temp2 where username='"+username+"';");
			else db.execute("create temporary table temp3 select * from temp2");
			
			if(!date.equals(""))
				db.execute("create temporary table temp4 select * from temp3 where date='"+date+"';");
			else db.execute("create temporary table temp4 select * from temp3");
			
			if(!time.equals(""))
				q="select * from temp4 where time="+time;
			else q="select * from temp4";
			
		
			ResultSet c = db.query(q);
			int counts = 0;
			while(c.next()) 			
				counts++;
			c.close();
			
			//System.out.println(counts);
			
			List<Order> list=new ArrayList<Order>();
			q = q+" limit " + (currentpage - 1) * pagesize + "," + pagesize;
			ResultSet rs = db.query(q);
			Order req;
			while(rs.next())  {
				req = new Order(rs.getDate(1),rs.getInt(2),rs.getString(3),rs.getInt(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
				list.add(req);
			}
			rs.close();
			
//			for(int i=0;i<list.size();i++) {
//				System.out.print(list.get(i).getBuilding()+",");
//				System.out.print(list.get(i).getRoomNum()+",");
//				System.out.print(list.get(i).getAvailable()+",");
//				System.out.println(list.get(i).getRange());
//			}
									
			db.execute("drop temporary table temp1,temp2,temp3,temp4;");
			db.close();
			
			Page page = new Page(counts, currentpage, pagesize, viewpages);
			
			request.setAttribute("LIST", list);
			request.setAttribute("PAGE", page);
			request.setAttribute("building", building);
			request.setAttribute("roomNum", roomNum);
			request.setAttribute("username", username);
			request.setAttribute("date", date);
			request.setAttribute("time", time);
			
			ServletContext context = getServletContext();
	        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
	        dispatcher.forward(request, response);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
     
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}