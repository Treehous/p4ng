package edu.ycp.cs320.groupProject.webapp.shared.controller;

import java.util.Random;

import edu.ycp.cs320.groupProject.webapp.shared.model.Ball;
import edu.ycp.cs320.groupProject.webapp.shared.model.Paddle;
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
		int rand = rnd.nextInt(359);
		double xVel = Math.cos(rand);
		double yVel = Math.sin(rand);
		
		//xVel*initVel
		model.setBall(new Ball(Stage.WIDTH/2, Stage.HEIGHT/2, xVel*initVel, yVel*initVel)  );
		
		
	}

	public void timerTick(Stage model) {
		for(Paddle paddle: model.getPaddles()){
			if (!paddle.getPlayerControl()){
				PaddleAI temp = new PaddleAI(paddle);
				temp.PaddleControl(model.getBall());
			}else{
				
			}
		}
		/*
		PaddleAI paddle1 = new PaddleAI(model.getPaddles().get(0));
		PaddleAI paddle2 = new PaddleAI(model.getPaddles().get(1));
		PaddleAI paddle3 = new PaddleAI(model.getPaddles().get(2));
		PaddleAI paddle4 = new PaddleAI(model.getPaddles().get(3));
		
		paddle1.PaddleControl(model.getBall());
		paddle2.PaddleControl(model.getBall());
		paddle3.PaddleControl(model.getBall());
		paddle4.PaddleControl(model.getBall());
		*/
		
		model.setBall(new Ball((int)(model.getBall().getX() + model.getBall().getDx()), 
				(int)(model.getBall().getY() + model.getBall().getDy()), model.getBall().getDx(), 
				model.getBall().getDy()));
		
	}
}
