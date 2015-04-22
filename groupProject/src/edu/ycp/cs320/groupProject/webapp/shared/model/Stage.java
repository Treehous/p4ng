package edu.ycp.cs320.groupProject.webapp.shared.model;

import java.util.ArrayList;

public class Stage {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	protected boolean gameOver = false;
	
	ArrayList<User> userList = new ArrayList<User>();
	User self;
	ArrayList<Paddle> paddleList = new ArrayList<Paddle>();
	private Ball ball;
	
	public void setPaddles(Paddle paddle1, Paddle paddle2,Paddle paddle3, Paddle paddle4){
		paddleList.add(0, paddle1);
		paddleList.add(1, paddle2);
		paddleList.add(2, paddle3);
		paddleList.add(3, paddle4);
	}public void addUser(User user){
		userList.add(user);
		user.getController().getControlledPaddle().setPlayerControl(true);
	}
	
	public boolean ballOffScreen(){
		if(ball.getX() > WIDTH || ball.getX() < 0 ){
			return true;
		}
		if(ball.getY() < 0 || ball.getY() > HEIGHT ){
			return true;
		}
		return false;
	}
	
	/**
	 * Find who is responsible for ball going out of bounds (Doesn't really matter for AI control)
	 * @return 0-3, the index of paddle for which the ball went out on.
	 */
	public int ballOutOn(){
		if(ball.getY() > HEIGHT ){
			return 0;
		}if(ball.getX() < 0){
			return 1;
		}if(ball.getY() < 0 ){
			return 2;
		}if(ball.getX() > WIDTH ){
			return 3;
		}
		return -1;
	}
	
	//constructor
	public Stage(){
		
	}public void setSelf(User e){
		self = e;
	}public void setBall(Ball ball){
		this.ball = ball;
	}public Ball getBall(){
		return ball;
	}public ArrayList<Paddle> getPaddles(){
		return paddleList;
	}public ArrayList<User> getUsers(){
		return userList;
	}public User getSelf(){
		return self;
	}
	
}
