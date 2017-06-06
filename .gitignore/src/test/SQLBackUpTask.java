package test;

import java.util.*;
import java.util.Date;
import java.io.*;
import java.sql.*;

public class SQLBackUpTask extends TimerTask {

	public void run() {
		try {
            Runtime rt = Runtime.getRuntime();
 
            // 调用 调用mysql的安装目录的命令           
            Process child = 
            rt.exec("D:\\Program Files\\MySQL\\mysql-5.7.18-winx64\\bin\\mysqldump -h localhost -uroot -p123 rmdb");
            // 设置导出编码为utf-8。这里必须是utf-8
            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
            InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
 
            InputStreamReader xx = new InputStreamReader(in, "utf-8");
            // 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
 
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            // 组合控制台输出信息字符串
            BufferedReader br = new BufferedReader(xx);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();
 
            // 要用来做导入用的sql目标文件：
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
