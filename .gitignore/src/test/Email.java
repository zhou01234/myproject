package test;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

 
public class Email {
	public String to = "";// �ռ��˵ĵ����ʼ� ID
    private String from = "zhou_01234@yeah.net";// �����˵ĵ����ʼ� ID
    private Properties props;
    private Session session;
    private MimeMessage message;
	
    public Email() {
    	props = new Properties();                // ���������ʼ��������Ĳ������ã������ʼ�ʱ����Ҫ�õ���
    	props.setProperty("mail.transport.protocol", "smtp");   // ʹ�õ�Э�飨JavaMail�淶Ҫ��
    	props.setProperty("mail.smtp.host", "smtp.yeah.net");   // �����˵������ SMTP ��������ַ
    	props.setProperty("mail.smtp.auth", "true");            // ��Ҫ������֤
    	
    	session= Session.getDefaultInstance(props); // ���ݲ������ã������Ự����Ϊ�˷����ʼ�׼���ģ�
        //session.setDebug(true);                                 // ����Ϊdebugģʽ, ���Բ鿴��ϸ�ķ��� log
    	
    	message = new MimeMessage(session);
    	
    }
    
    
  public boolean sendEmail(String to,String content) 
		  throws ServletException, IOException {
	  boolean flag = true;
      try {
      message.setFrom(new InternetAddress(from,"�����ҽ��ҹ���ϵͳ","UTF-8"));//�����ˣ�����������, ��ʾ���ǳ�, �ǳ��ַ�������
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to,"RMUser","UTF-8"));//�ռ���
      message.setSubject("ϵͳ֪ͨ");
      message.setContent(content, "text/html;charset=UTF-8");//�ʼ�����
      message.setSentDate(new Date());//��ʾʱ��
           
      Transport transport = session.getTransport();//���� Session ��ȡ�ʼ��������
      transport.connect(from, "client123");//�����ʼ�������
      transport.sendMessage(message, message.getAllRecipients());//�����ʼ�����ȡ�����ռ���, ������, ������
      transport.close();//�ر�����
      
      } catch (MessagingException mex) {
          mex.printStackTrace();
          flag = false;
          }
      return flag;      
   }
  
  public static void main(String[] args){
	  Email e = new Email();
	  try {
		e.sendEmail("1205843855@qq.com", "���");
	} catch (ServletException e1) {
		// TODO �Զ����ɵ� catch ��
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO �Զ����ɵ� catch ��
		e1.printStackTrace();
	}
  }
  
  
  
} 