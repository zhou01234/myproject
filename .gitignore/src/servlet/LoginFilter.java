package servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;

//实现 Filter 类
public class LoginFilter implements Filter  {
	public void  init(FilterConfig config) throws ServletException {
		String site = config.getInitParameter("rm"); 
		System.out.println("欢迎登录: " + site);
	}
	public void  doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {
		String target = "/rm/login.html";
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		
		String currentURL = req.getRequestURI();
		String username = (String) session.getAttribute("USERNAME");
        if(username==null && !currentURL.equals(target)){
            resp.sendRedirect(target);
        }
        else{
        	chain.doFilter(request, response);
        }
            
        
	}
	public void destroy( ){
		/* 在 Filter 实例被 Web 容器从服务移除之前调用 */
	}
}
