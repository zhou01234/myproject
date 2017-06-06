package test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLDB {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet result = null;
	public SQLDB() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
        
        connect = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/rmdb?useUnicode=true&characterEncoding=utf-8&useSSL=false",
                "root","123");
        //System.out.println("Connect OK.");
        
        statement =  connect.createStatement();
                  
	  }
	
	public ResultSet query(String str) throws SQLException {
		result = statement.executeQuery(str);
		return result;
	}
	
	public boolean execute(String str) throws SQLException {
		boolean flag = statement.execute(str);
		return flag;
	}
	
	public void close() throws SQLException {
		result.close();
		statement.close();
		connect.close();
	}
	
	public static void main(String[] args) throws Exception {
		SQLDB db = new SQLDB();
		String q;		
		q="create temporary table temp select * from room where building='A';";		
		db.execute(q);
				
		q="select * from temp where roomNum=101;";
		db.query(q);
		
		ResultSet rs = db.query(q);
		
		
		while(rs.next()) {
			System.out.println(rs.getObject(1));
			System.out.println(rs.getString(2));
			System.out.println(rs.getString(3));
			System.out.println(rs.getString(4));
		}
		
		
		q="drop temporary table temp;";
		db.execute(q);
	}
}
