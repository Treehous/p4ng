package edu.ycp.cs320.groupProject.webapp.shared.model;

import edu.ycp.cs320.groupProject.webapp.shared.controller.PlayerController;

public class User {
	rank rank;
	PlayerController control;
	int lives = 3;
	
	User(PlayerController control){
		this.control = control;
	}
	
	public void setLives(int n){
		lives = n;
	}
	
	public PlayerController getController(){
		return control;
	}public int getLives(){
		return lives;
	}
	
}
