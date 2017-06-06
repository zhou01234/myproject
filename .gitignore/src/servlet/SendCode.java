package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;

import test.Email;


public class SendCode extends HttpServlet {
    
    String target = "_person_3#.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//�������(POST)
		response.setContentType("text/html;charset=utf-8");//��Ӧ����
		
		HttpSession session = request.getSession();
		
		String to = request.getParameter("email");
		Email email = new Email();
		
		String code = RandomStringUtils.randomAlphanumeric(4);//����4λ����+��ĸ����ַ���
		String content =  "��ã�"
						+ "����ԭҳ�����룺\n"+code+"\n��";
		
		boolean a = email.sendEmail(to,content);
		session.setAttribute("CODE",code);                   //��¼��֤��
		session.setAttribute("EMAIL",to);                    //��¼��������
		
		String role = (String)session.getAttribute("ROLE");
		
		target= "/" + role + target;
		
		//session.setMaxInactiveInterval(5*60);
		
		//System.out.println("sd."+session.getAttribute("USERNAME"));
		//System.out.println("sd."+session.getAttribute("CODE"));
		ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
