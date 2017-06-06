package servlet;

import java.io.*;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;
import test.*;
import java.util.*;


public class QueryRoom extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		String target = "/user_query#.jsp";
		
		request.setCharacterEncoding("utf-8");//«Î«Û±‡¬Î(POST)
		response.setContentType("text/html;charset=utf-8");//œÏ”¶±‡¬Î
		
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String building = request.getParameter("building");
		String roomNum = request.getParameter("roomNum");
		
		String cp = request.getParameter("cp");
        if(cp==null) cp="1";
        int currentpage = Integer.parseInt(cp);
    
        int pagesize = 15;
        int viewpages = 5;
        
		try {
			SQLDB db = new SQLDB();			
			String q;
			q="create temporary table temp1 select * from reservation where date='"+date+"' and time="+time;
			//System.out.println(q);
			db.execute(q);
			
			ResultSet used = db.query("select * from temp1");
			String limit = "";
			while(used.next()) {
				limit = limit + "('"+used.getString(3)+"',"+used.getInt(4)+"),";
			}
			used.close();
			limit = limit + "('',null)";
			
			q="create temporary table temp2 "
					+ "select * from room where available=1 and !( (building,roomNum) in ("+limit+") )";			
			db.execute(q);
			
			
			if(!building.equals(""))
				db.execute("create temporary table temp3 select * from temp2 where building='"+building+"'");
			else db.execute("create temporary table temp3 select * from temp2");
			
			if(!roomNum.equals(""))
				q="select * from temp3 where roomNum="+roomNum;
			else q="select * from temp3";
			
			ResultSet c = db.query(q);			
			int counts = 0;
			while(c.next()) 			
				counts++;
			c.close();
			
			q = q+" limit " + (currentpage - 1) * pagesize + "," + pagesize;
			//System.out.println(counts);
			ResultSet rs = db.query(q);
			Room room;
			List<Room> list=new ArrayList<Room>();
			while(rs.next())  {
				room = new Room(rs.getString(1),rs.getInt(2),rs.getInt(4));
				list.add(room);
			}
			//System.out.println(counts);
			rs.close();
			db.execute("drop temporary table temp1,temp2,temp3;");
			db.close();
			
			Page page = new Page(counts, currentpage, pagesize, viewpages);
			
			request.setAttribute("LIST", list);
			request.setAttribute("PAGE", page);
			request.setAttribute("building", building);
			request.setAttribute("roomNum", roomNum);
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