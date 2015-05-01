package edu.ycp.cs320.groupProject.webapp;

public class UserPass {
	private int id;
	private String pass;
	private String user;
	
	public UserPass() {
		
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getUser() {
		return user;
	}
}