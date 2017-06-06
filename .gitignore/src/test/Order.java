package test;

import java.sql.Date;

public class Order {
	private String username;
	private String email;
	private Date date;
	private int time;
	private String building;
	private String name;
	private int roomNum;
	private int longterm;
	private String reason;
	public Order() {
	    super();
	}
	
	/* request */
	public Order(Date date, int time, String building, int roomNum, String username, 
					int longterm, String name, String email, String reason) {
	    super();	    
	    this.date = date;
	    this.time = time;
	    this.building = building;
	    this.roomNum = roomNum;
	    this.username = username;
	    this.longterm = longterm;
	    this.name = name;
	    this.email = email;
	    this.reason = reason;
	}
	
	/* reservation */
	public Order(Date date, int time, String building, int roomNum, String username, 
								  String name, String email, String reason) {
		super();	    
		this.date = date;
		this.time = time;
		this.building = building;
		this.roomNum = roomNum;
		this.username = username;
		this.name = name;
		this.email = email;
		this.reason = reason;
		}
	
	public String getUsername() {
	    return username;
	}
	public void setUsername(String username) {
	    this.username = username;
	}
	
	public String getEmail() {
	    return email;
	}
	public void setEmail(String email) {
	    this.email = email;
	}
	
	public Date getDate() {
	    return date;
	}
	public void setDate(Date date) {
	    this.date = date;
	}
	
	public int getTime() {
	    return time;
	}
	public void setTime(int time) {
	    this.time = time;
	}
	
	public String getBuilding() {
	    return building;
	}
	public void setBuilding(String building) {
	    this.building = building;
	}
	
	public int getRoomNum() {
	    return roomNum;
	}
	public void setRoomNum(int roomNum) {
	    this.roomNum = roomNum;
	}
	
	public int getLongterm() {
	    return longterm;
	}
	public void setLongterm(int longterm) {
	    this.longterm = longterm;
	}
	
	
	
	public String getReason() {
	    return reason;
	}
	public void setReason(String reason) {
	    this.reason = reason;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
