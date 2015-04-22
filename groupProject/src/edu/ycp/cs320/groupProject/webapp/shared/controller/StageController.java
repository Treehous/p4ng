package edu.ycp.cs320.groupProject.webapp.shared.controller;

import java.util.Random;


import edu.ycp.cs320.groupProject.webapp.shared.model.Ball;
import edu.ycp.cs320.groupProject.webapp.shared.model.Paddle;
import edu.ycp.cs320.groupProject.webapp.shared.model.Point;
import edu.ycp.cs320.groupProject.webapp.shared.model.User;
import edu.ycp.cs320.groupProject.webapp.shared.model.Stage;

public class StageController {
	public void initModel(Stage model) {
		int initVel = 5;
		Random rnd = new Random();
		
		model.setPaddles(new Paddle(Stage.WIDTH/2, Stage.HEIGHT-30,false,false), //bottom
				new Paddle(10,Stage.HEIGHT/2, true,false), 	//left
				new Paddle(Stage.WIDTH/2, 10,false,false), 				//top
				new Paddle(Stage.WIDTH-30,Stage.HEIGHT/2, true,false));	// right
		double rand = rnd.nextDouble()*6.28;
		double xVel = Math.cos(rand);
		double yVel = Math.sin(rand);
		//create user and make him "you"
		model.addUser(new User(new PlayerController(model.getPaddles().get(0))));
		model.setSelf(model.getUsers().get(0));
		
		//Create dummy to test user case
		model.addUser(new User(new PlayerController(model.getPaddles().get(1))));
		model.setBall(new Ball(Stage.WIDTH/2, Stage.HEIGHT/2, xVel*initVel, yVel*initVel)  );
		//model.setBall(new Ball(Stage.WIDTH/2, Stage.HEIGHT/2, -4.5, 0.9)  );
		
		
	}
	public void resetStage(Stage model){
		//Hardcoded paddle reset
		model.getPaddles().get(0).setTopLeft(new Point(Stage.WIDTH/2, Stage.HEIGHT-30));
		model.getPaddles().get(1).setTopLeft(new Point(10,Stage.HEIGHT/2));
		model.getPaddles().get(2).setTopLeft(new Point(Stage.WIDTH/2, 10));
		model.getPaddles().get(3).setTopLeft(new Point(Stage.WIDTH-30,Stage.HEIGHT/2));
	
		int initVel = 5;
		Random rnd = new Random();
		double rand = rnd.nextDouble()*6.28;
		double xVel = Math.cos(rand);
		double yVel = Math.sin(rand);
		model.setBall(new Ball(Stage.WIDTH/2, Stage.HEIGHT/2, xVel*initVel, yVel*initVel)  );
	}
	//Update the model each tick
	public void timerTick(Stage model) {
		for(Paddle paddle: model.getPaddles()){
			if (!paddle.getPlayerControl()){
				PaddleAI temp = new PaddleAI(paddle);
				temp.PaddleControl(model.getBall());
			}
		}
		for(User user: model.getUsers()){
			user.getController().Control(model.getBall());
		}
		
		model.setBall(new Ball((int)(model.getBall().getX() + model.getBall().getDx()), 
				(int)(model.getBall().getY() + model.getBall().getDy()), model.getBall().getDx(), 
				model.getBall().getDy()));
		
		if(model.ballOffScreen()){
			int i = model.ballOutOn();
			for(User user: model.getUsers()){
				//If controlled paddle equals paddle responsible for outed ball
				if(user.getController().getControlledPaddle().equals(model.getPaddles().get(i))){
					boolean proceed = user.loseALife();
					if(proceed){
						//GWT.log(Level.INFO, "Player "+(model.getUsers().indexOf(user)+1)  + " lost a life, " + user.getLives() + " live(s) remain");
					}else{
						//TODO: Handle elimination
					}
					
				}
			}
			resetStage(model);
		}	
	}
}
