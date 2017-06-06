package servlet;

import java.io.*;
import java.net.URLEncoder;

import javax.servlet.*;
import javax.servlet.http.*;

import test.SQLDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginHandle extends HttpServlet {
	
	private String target;
	private ResultSet result = null;
	private SQLDB db = null;
	private String role = null;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");//�������(POST)
		response.setContentType("text/html;charset=utf-8");//��Ӧ����
		HttpSession session = request.getSession();
		
        String username = request.getParameter("username");//��ȡ����
        String password = request.getParameter("password");
        
        role = request.getParameter("role");
        
        List<String> message = new ArrayList<String>();
		try {
			if(!check(username,password,role)) {
				PrintWriter out = response.getWriter();				
				out.println("<script>"
						+ "alert('�û������������');"
						+ "document.location='login.html'</script>");
				return;
			}
			
							
		} catch (Exception e) {
			e.printStackTrace();}
		
		try {
			ResultSet msgrs = db.query("select * from message "
					+ "where username='"+ username + "';");			
			while (msgrs.next()) 
				message.add(msgrs.getString("message")); 
			
			ResultSet info = db.query("select * from "+role+" where username='"+username+"';");
			info.next();
						
			session.setAttribute("USERNAME",info.getString("username"));
			session.setAttribute("NAME",info.getString("name"));
			session.setAttribute("RANK",info.getString("rank"));
			session.setAttribute("EMAIL",info.getString("email"));
			session.setAttribute("MESSAGE",message);
			session.setAttribute("ROLE",role);
	        
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();}
		
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    private boolean check(String un,String pw,String r) throws Exception { 	
    	boolean flag = false;
    	db = new SQLDB();
    	
    	
    	switch(r) {
    	case "1":role="user";target="/user_index.jsp";break;
    	case "2":role="admin";target="/admin_index.jsp";break;    	
    	}
    	
    	result = db.query("select * from "+role+" where "  //��ѯ���ݿ���֤�û���������
        		+ "username='"+ un
        		+ "' and password='"+ pw
        		+ "' ;");
       
       if(result.next()) {
    	   flag=true;
    	   String e = result.getString("email");      //���û�Ϊ��д��������������ҳ֪ͨ�û�
    	   if(e.equals(""))                           //�������򲻸��£�������¼�¼
    		   db.execute("insert into message(username,message) "
    		   		+ "select '"+ un +"','��󶨵������䡣' "
    		   		+ "FROM dual where not exists "
    		   		+ "(select * from message where "
    		   		+ "username='"+ un +"' and message='��󶨵������䡣');");
    		   
       }
       return flag;
    }
}