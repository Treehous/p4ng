package edu.ycp.cs320.groupProject.webapp.shared.model;

public class Ball {
	int x;
	int y;
	int radius = 13; 
	double dx;
	double dy;
	
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
	}public void setX(int x){
		this.x = x;
	}public void setY(int y){
		this.y = y;
	}public void setDx(double dx){
		this.dx = dx;
	}public void setDy(double dy){
		this.dy = dy;
	}

}
