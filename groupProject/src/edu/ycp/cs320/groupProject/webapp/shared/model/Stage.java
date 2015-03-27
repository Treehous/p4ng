package edu.ycp.cs320.groupProject.webapp.shared.model;

import java.util.ArrayList;

public class Stage {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	protected boolean gameOver = false;
	
	ArrayList<User> userList = new ArrayList<User>();
	ArrayList<Paddle> paddleList = new ArrayList<Paddle>();
	private Ball ball;
	
	public void setPaddles(Paddle paddle1, Paddle paddle2,Paddle paddle3, Paddle paddle4){
		paddleList.add(0, paddle1);
		paddleList.add(1, paddle2);
		paddleList.add(2, paddle3);
		paddleList.add(3, paddle4);
	}public void addUser(User user){
		userList.add(user);
	}

	
	//constructor
	public Stage(){
		
	}
	public void setBall(Ball ball){
		this.ball = ball;
	}public Ball getBall(){
		return ball;
	}public ArrayList<Paddle> getPaddles(){
		return paddleList;
	}
}
