package servlet;

import java.io.*;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;
import test.*;
import java.util.*;


public class ListUserReservation extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		String target = "/user_reserv_3.jsp";
		
		request.setCharacterEncoding("utf-8");//«Î«Û±‡¬Î(POST)
		response.setContentType("text/html;charset=utf-8");//œÏ”¶±‡¬Î
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("USERNAME");
        
        String cp = request.getParameter("cp");
        if(cp==null) cp="1";
        int currentpage = Integer.parseInt(cp);

        
        int pagesize = 15;
        int viewpages = 5;
        
		try {
			SQLDB db = new SQLDB();
			String q;
			q="select * from reservation where username='"+username+"'";
			System.out.println(q);
		
			ResultSet c = db.query(q);
			int counts = 0;
			while(c.next()) 			
				counts++;
			c.close();
			
			System.out.println(counts);
			
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
							
			db.close();
			
			Page page = new Page(counts, currentpage, pagesize, viewpages);
			
			request.setAttribute("LIST", list);
			request.setAttribute("PAGE", page);
			
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