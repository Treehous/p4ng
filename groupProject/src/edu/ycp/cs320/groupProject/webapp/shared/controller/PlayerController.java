package edu.ycp.cs320.groupProject.webapp.shared.controller;

import edu.ycp.cs320.groupProject.webapp.shared.model.Ball;
import edu.ycp.cs320.groupProject.webapp.shared.model.Paddle;
import edu.ycp.cs320.groupProject.webapp.shared.model.Point;
import edu.ycp.cs320.groupProject.webapp.shared.model.Stage;

public class PlayerController{
	private boolean moveLeft, moveRight;
	Paddle ownedPaddle;
	
	PlayerController(Paddle paddle){
		ownedPaddle = paddle;
	}
	/**
	 * Function in charge of controlling the BOOLEAN value for moving right
	 */
	public void setMovingRight(boolean b){
		moveRight = b;
	}
	
	/**
	 * Function in charge of controlling the BOOLEAN value for moving left
	 */
	public void setMovingLeft(boolean b){
		moveLeft = b;
	}
	
	/**
	 * Function to return whether moving left or not
	 * @return Boolean, true if moving left
	 */
	public boolean getMovingLeft(){
		return moveLeft;
	}
	
	/**
	 * Function to return whether moving right or not
	 * @return Boolean, true if moving right
	 */
	public boolean getMovingRight(){
		return moveRight;
	}
	public Paddle getControlledPaddle(){
		return ownedPaddle;
	}
	
	public void Control(Ball targetBall){
		boolean collision = ownedPaddle.ballCollision(targetBall);
		if (moveRight == moveLeft){
			;//Don't do anything
		}
		else if (moveRight){
			moveRight();
		}else if (moveLeft){
			moveLeft();
		}
		targetBall.setCollision(collision);
	}
	public void moveRight(){
		if(ownedPaddle.isVertical()){
			if(ownedPaddle.getTopLeft().getY()+ ownedPaddle.getWidth() < Stage.HEIGHT){
				ownedPaddle.setTopLeft(new Point(ownedPaddle.getTopLeft().getX(),ownedPaddle.getTopLeft().getY()+ownedPaddle.getSpeed()));
			}
		}else{
			if(ownedPaddle.getTopLeft().getX()+ ownedPaddle.getWidth()< Stage.WIDTH){
				ownedPaddle.setTopLeft(new Point(ownedPaddle.getTopLeft().getX()+ownedPaddle.getSpeed(),ownedPaddle.getTopLeft().getY()));
			}
		}
	}
	
	public void moveLeft(){
		if(ownedPaddle.isVertical()){
			if(ownedPaddle.getTopLeft().getY() > 0){
				ownedPaddle.setTopLeft(new Point(ownedPaddle.getTopLeft().getX(),ownedPaddle.getTopLeft().getY()-ownedPaddle.getSpeed()));
			}
		}else{
			if(ownedPaddle.getTopLeft().getX() > 0){
				ownedPaddle.setTopLeft(new Point(ownedPaddle.getTopLeft().getX()-ownedPaddle.getSpeed(),ownedPaddle.getTopLeft().getY()));
			}
		}
	}
}
