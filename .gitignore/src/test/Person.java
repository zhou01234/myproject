package test;

public class Person {
	private String username;
	private String password;
	private int rank;
	
	private String email;
	private String name;
	public Person() {
	    super();
	}
	public Person(String username, int rank, String email, String name) {
	    super();
	    this.username = username;
	    this.name = name;
	    this.rank = rank;
	    this.email = email;
	}
	
	public String getUsername() {
	    return username;
	}
	public void setUsername(String username) {
	    this.username = username;
	}
	
	public String getPassword() {
	    return password;
	}
	public void setPassword(String password) {
	    this.password = password;
	}
	
	public int getRank() {
	    return rank;
	}
	public void setRank(int rank) {
	    this.rank = rank;
	}
	
	public String getEmail() {
	    return email;
	}
	public void setEmail(String email) {
	    this.email = email;
	}
	
	public String getName() {
	    return name;
	}
	public void setName(String name) {
	    this.name = name;
	}
}

