package edu.ycp.cs320.groupProject.webapp.shared.model;

import edu.ycp.cs320.groupProject.webapp.shared.controller.PlayerController;

public class User {
	rank rank;
	PlayerController control;
	
	User(PlayerController control){
		this.control = control;
	}
	
	PlayerController getController(){
		return control;
	}
	
}
