package test;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

 
public class Email {
	public String to = "";// 收件人的电子邮件 ID
    private String from = "zhou_01234@yeah.net";// 发件人的电子邮件 ID
    private Properties props;
    private Session session;
    private MimeMessage message;
	
    public Email() {
    	props = new Properties();                // 用于连接邮件服务器的参数配置（发送邮件时才需要用到）
    	props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
    	props.setProperty("mail.smtp.host", "smtp.yeah.net");   // 发件人的邮箱的 SMTP 服务器地址
    	props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
    	
    	session= Session.getDefaultInstance(props); // 根据参数配置，创建会话对象（为了发送邮件准备的）
        //session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
    	
    	message = new MimeMessage(session);
    	
    }
    
    
  public boolean sendEmail(String to,String content) 
		  throws ServletException, IOException {
	  boolean flag = true;
      try {
      message.setFrom(new InternetAddress(from,"会议室教室管理系统","UTF-8"));//发件人，参数：邮箱, 显示的昵称, 昵称字符集编码
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to,"RMUser","UTF-8"));//收件人
      message.setSubject("系统通知");
      message.setContent(content, "text/html;charset=UTF-8");//邮件正文
      message.setSentDate(new Date());//显示时间
           
      Transport transport = session.getTransport();//根据 Session 获取邮件传输对象
      transport.connect(from, "client123");//连接邮件服务器
      transport.sendMessage(message, message.getAllRecipients());//发送邮件，获取所有收件人, 抄送人, 密送人
      transport.close();//关闭连接
      
      } catch (MessagingException mex) {
          mex.printStackTrace();
          flag = false;
          }
      return flag;      
   }
  
  public static void main(String[] args){
	  Email e = new Email();
	  try {
		e.sendEmail("1205843855@qq.com", "你好");
	} catch (ServletException e1) {
		// TODO 自动生成的 catch 块
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO 自动生成的 catch 块
		e1.printStackTrace();
	}
  }
  
  
  
} 