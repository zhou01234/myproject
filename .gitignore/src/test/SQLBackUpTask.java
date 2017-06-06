package test;

import java.util.*;
import java.util.Date;
import java.io.*;
import java.sql.*;

public class SQLBackUpTask extends TimerTask {

	public void run() {
		try {
            Runtime rt = Runtime.getRuntime();
 
            // ���� ����mysql�İ�װĿ¼������           
            Process child = 
            rt.exec("D:\\Program Files\\MySQL\\mysql-5.7.18-winx64\\bin\\mysqldump -h localhost -uroot -p123 rmdb");
            // ���õ�������Ϊutf-8�����������utf-8
            // �ѽ���ִ���еĿ���̨�����Ϣд��.sql�ļ����������˱����ļ���ע��������Կ���̨��Ϣ���ж�������ᵼ�½��̶����޷�����
            InputStream in = child.getInputStream();// ����̨�������Ϣ��Ϊ������
 
            InputStreamReader xx = new InputStreamReader(in, "utf-8");
            // �������������Ϊutf-8�����������utf-8����������ж����������
 
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            // ��Ͽ���̨�����Ϣ�ַ���
            BufferedReader br = new BufferedReader(xx);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();
 
            // Ҫ�����������õ�sqlĿ���ļ���
            FileOutputStream fout = 
            		new FileOutputStream("D:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\backup\\rmdb.sql");
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
            writer.write(outStr);
            writer.flush();
            in.close();
            xx.close();
            br.close();
            writer.close();
            fout.close();
 
            System.out.println("");
 
        } catch (Exception e) {
            e.printStackTrace();
        }
	}		
}
