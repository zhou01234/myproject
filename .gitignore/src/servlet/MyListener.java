package servlet;

import java.util.Timer;  
import test.*;
import javax.servlet.ServletContextEvent;  
import javax.servlet.ServletContextListener;  
import javax.servlet.http.HttpServlet;



public class MyListener extends HttpServlet implements ServletContextListener{
	Timer timer = new Timer();
	public void contextDestroyed(ServletContextEvent sce) {  
		timer.cancel(); 
	 } 
	
	public void contextInitialized(ServletContextEvent sce) {  
		  
		  // 当监听开始执行时,设置一个TIME 
		int second = 1000;
		int minute = 60*second;
		int hour = 60*minute;
		int day = 24*hour;
		System.out.println("-------Timer开始进行执行--------------");  
		SQLTimerTask task1 = new SQLTimerTask();  
		timer.schedule(task1, 0, 12*hour);  		    
		SQLBackUpTask task2 = new SQLBackUpTask();
		timer.schedule(task2, 0, 7*day);  
	}
	
	
}
