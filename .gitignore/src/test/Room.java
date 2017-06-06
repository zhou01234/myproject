package test;

public class Room {
	private String building;
	private int roomNum;
	private int available;
	private int rank;
	public Room() {
	    super();
	}
	public Room(String building, int roomNum, int available, int rank) {
	    super();
	    this.building = building;
	    this.roomNum = roomNum;
	    this.available = available;
	    this.rank = rank;
	}
	
	public Room(String building, int roomNum, int rank) {
	    super();
	    this.building = building;
	    this.roomNum = roomNum;
	    this.rank = rank;
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
	
	public int getAvailable() {
	    return available;
	}
	public void setAvailable(int available) {
	    this.available = available;
	}
	
	public int getRank() {
	    return rank;
	}
	public void setRank(int rank) {
	    this.rank = rank;
	}
}
