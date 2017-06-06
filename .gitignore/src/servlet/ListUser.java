package servlet;

import java.io.*;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;
import test.*;
import java.util.*;


public class ListUser extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		String target = "/admin_user_1#.jsp";
		
		request.setCharacterEncoding("utf-8");//请求编码(POST)
		response.setContentType("text/html;charset=utf-8");//响应编码
		
		String username = request.getParameter("username");//获取请求
        String rank = request.getParameter("rank");
        String cp = request.getParameter("cp");
        if(cp==null) cp="1";
        int currentpage = Integer.parseInt(cp);
    
        int pagesize = 2;
        int viewpages = 5;
        
		try {
			SQLDB db = new SQLDB();
			String q;
			if(!username.equals(""))
				db.execute("create temporary table temp1 select * from user where username='"+username+"'");
			else db.execute("create temporary table temp1 select * from user");
			
			if(!rank.equals(""))
				q="select * from temp1 where rank="+rank;
			else q="select * from temp1";			
			
			
			//System.out.println(q);
			
			ResultSet c = db.query(q);
			
			int counts = 0;
			while(c.next()) 			
				counts++;
			c.close();
			//System.out.println(counts);
			
			List<Person> list=new ArrayList<Person>();
			q = q+" limit " + (currentpage - 1) * pagesize + "," + pagesize;
			ResultSet rs = db.query(q);
			Person u;
			while(rs.next())  {
				u = new Person(rs.getString(1),rs.getInt(3),rs.getString(4),rs.getString(5));
				list.add(u);
			}
			rs.close();
			
//			for(int i=0;i<list.size();i++) {
//				System.out.print(list.get(i).getBuilding()+",");
//				System.out.print(list.get(i).getRoomNum()+",");
//				System.out.print(list.get(i).getAvailable()+",");
//				System.out.println(list.get(i).getRange());
//			}
									
			db.execute("drop temporary table temp1;");
			db.close();
			
			Page page = new Page(counts, currentpage, pagesize, viewpages);
			
			request.setAttribute("LIST", list);
			request.setAttribute("PAGE", page);
			request.setAttribute("username", username);
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
