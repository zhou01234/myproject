package servlet;

import java.io.*;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;
import test.*;
import java.util.*;


public class ListRoom extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		String target = "/admin_room_1#.jsp";
		
		request.setCharacterEncoding("utf-8");//请求编码(POST)
		response.setContentType("text/html;charset=utf-8");//响应编码
		
		String building = request.getParameter("building");//获取请求
        String roomNum = request.getParameter("roomNum");
        String available = request.getParameter("available");
        String rank = request.getParameter("rank");
        String cp = request.getParameter("cp");
        if(cp==null) cp="1";
        int currentpage = Integer.parseInt(cp);
    
        int pagesize = 15;
        int viewpages = 5;
        
		try {
			SQLDB db = new SQLDB();
			String q;
			if(!building.equals(""))
				db.execute("create temporary table temp1 select * from room where building='"+building+"'");
			else db.execute("create temporary table temp1 select * from room");
			
			if(!roomNum.equals(""))
				db.execute("create temporary table temp2 select * from temp1 where roomNum="+roomNum);
			else db.execute("create temporary table temp2 select * from temp1");
			
			if(!available.equals(""))
				db.execute("create temporary table temp3 select * from temp2 where available="+available);
			else db.execute("create temporary table temp3 select * from temp2");
			
			if(!rank.equals(""))
				q="select * from temp3 where rank="+rank;
			else q="select * from temp3";			
			
			
			//System.out.println(q);
			
			ResultSet c = db.query(q);
			
			int counts = 0;
			while(c.next()) 			
				counts++;
			c.close();
			//System.out.println(counts);
			
			List<Room> list=new ArrayList<Room>();
			q = q+" limit " + (currentpage - 1) * pagesize + "," + pagesize;
			ResultSet rs = db.query(q);
			Room room;
			while(rs.next())  {
				room = new Room(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
				list.add(room);
			}
			rs.close();
			
//			for(int i=0;i<list.size();i++) {
//				System.out.print(list.get(i).getBuilding()+",");
//				System.out.print(list.get(i).getRoomNum()+",");
//				System.out.print(list.get(i).getAvailable()+",");
//				System.out.println(list.get(i).getRange());
//			}
									
			db.execute("drop temporary table temp1,temp2,temp3;");
			db.close();
			
			Page page = new Page(counts, currentpage, pagesize, viewpages);
			
			request.setAttribute("LIST", list);
			request.setAttribute("PAGE", page);
			request.setAttribute("building", building);
			request.setAttribute("roomNum", roomNum);
			request.setAttribute("available", available);
			request.setAttribute("rank", rank);
			
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
