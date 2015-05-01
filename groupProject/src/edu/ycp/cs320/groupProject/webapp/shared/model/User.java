package edu.ycp.cs320.groupProject.webapp.shared.model;

import edu.ycp.cs320.groupProject.webapp.shared.controller.PlayerController;

public class User {
	rank rank;
	PlayerController control;
	int lives = 3;
	
	public User(PlayerController control){
		this.control = control;
	}
	
	public void setLives(int n){
		lives = n;
	}
	/**
	 * Lose a life
	 * @return boolean, true if more lives remain. False if elimination should occur
	 */
	public boolean loseALife(){
		lives -= 1;
		if(lives <= 0){
			return false;
		}
		else{
			return true;
		}
	}
	
	public PlayerController getController(){
		return control;
	}public int getLives(){
		return lives;
	}
	
}
