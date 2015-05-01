package edu.ycp.cs320.groupProject.webapp.shared.model;

import java.io.Serializable;

public class Ball implements Serializable {

	private static final long serialVersionUID = 1L;
	int x;
	int y;
	int radius = 13; 
	double dx;
	double dy;
	boolean collision;
	
	public Ball() {
		
	}
	
	public Ball(int x, int y, double dx, double dy){
		this.x =x;
		this.y =y;
		this.dx =dx;
		this.dy =dy;
	}
	
	public int getX(){
		return x;
	}public int getY(){
		return y;
	}public int getRadius(){
		return radius;
	}public double getDx(){
		return dx;
	}public double getDy(){
		return dy;
	}public boolean getCollision(){
		return collision;
	}public void setX(int x){
		this.x = x;
	}public void setY(int y){
		this.y = y;
	}public void setDx(double dx){
		this.dx = dx;
	}public void setDy(double dy){
		this.dy = dy;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}public void setCollision(boolean e){
		collision = e;
	}

}
