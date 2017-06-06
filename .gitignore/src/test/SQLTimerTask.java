package test;

import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;

public class SQLTimerTask extends TimerTask {

	@Override
	public void run() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dt = new Date();
			String today = sdf.format(dt);
			SQLDB db = new SQLDB();
			
			String q = "select date,time,building,roomNum,count(1) from request "
					+ "where date = '"+today+"' and longterm=0 "
					+ "group by date,time,building,roomNum";
			//System.out.println(q);
			ResultSet rs = db.query(q);
			ResultSet selected;
			String date;
			int time;
			String building;
			int roomNum;
			String username;		
			String name;
			String email;
			String reason;
			
			while(rs.next()) {
				SQLDB db1 = new SQLDB();
				int r = new Random().nextInt(rs.getInt(5));
				
				date = rs.getString(1);
				time = rs.getInt(2);
				building = rs.getString(3);
				roomNum = rs.getInt(4);
				
				q = "select * from request where date='"+date+"' and time="+time
						+ " and building='"+building+"' and roomNum="+roomNum+" and longterm=0 limit "+r+",1";
				
				selected = db1.query(q);
				selected.next();
				
				username = selected.getString(5);
				name = selected.getString(7);
				email = selected.getString(8);
				reason = selected.getString(9);
				
				q = "delete from request where date = '" + date + 
						 "' and time = "+ time +
						 " and building= '" + building +
						 "' and roomNum = " + roomNum + 
						 " and longterm=0";
				
				db1.execute(q);
				
				q = "insert into reservation values('"+date+"',"+time+",'"+building+"',"+roomNum+",'"
						+username+"','"+name+"','"+email+"','"+reason+"');";			
				db1.execute(q);
				
				q = "select * from time where timeNum="+time;
				ResultSet t = db1.query(q);
				t.next();
				Email e = new Email();
				
				String content = "您好,"+name+"("+username+")"+"，您的预约：\n"
						+ "日期："+date+",时段："+t.getString(2)+"~"+t.getString(3)+",楼名："+building+",房间号："+roomNum+"\n"
						+ "已通过。";
				e.sendEmail(email, content);
				
				
				Log log = new Log();
				log.log_write("系统自动通过了用户"+name+"("+username+")"+"的预约：\n"
						+ "日期："+date+",时段："+t.getString(2)+"~"+t.getString(3)+",楼名："+building+",房间号："+roomNum+"\n");
			
				t.close();
				db1.close();
			}
			rs.close();
			db.close();
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}		
}
