package edu.ycp.cs320.groupProject.webapp.shared.model;


public class Paddle {
	Point topleft;
	int width = 125;
	int length = 20;
	boolean vertical = false;
	int speed = 5;
	boolean playerControlled;
	
	public Paddle(int x, int y, boolean vertical, boolean playerControlled){
		topleft = new Point(x,y);
		this.vertical = vertical;
		this.playerControlled = playerControlled;
	}
	public void setTopLeft(Point point){
		topleft = point;
	}public void setSpeed(int speed){
		this.speed = speed;
	}public void setPlayerControl(boolean b){
		playerControlled = b;
	}
	public void scalePaddle(int percentageValue){
		double t = percentageValue/100;
		width += t*width;
	}public void restorePaddleWidth(){
		width = 125;
	}public boolean isVertical(){
		if(vertical){
			return true;
		}
		return false;
	}public Point getTopLeft(){
		return topleft;
	}public int getSpeed(){
		return speed;
	}public int getWidth(){
		return width;
	}public int getHeight(){
		return length;
	}public boolean getPlayerControl(){
		return playerControlled;
	}
	
	

	/**
	 * Return the nearest point on the paddle
	 * This will only work for rectangle on axis
	 * 
	 * @param ball 
	 * @return Point value of the nearest on paddle in relation to ball
	 */
	public Point findNearest(Ball ball){
		int xNearest = 0, yNearest = 0;
		
		if (!vertical){
			//X values for offset
			if((ball.getX() >= topleft.getX() )&& (ball.getX()<=topleft.getX()+width)){
				if(ball.getDx() > 0){
					xNearest = ball.getX();
				}else{
					xNearest = ball.getX();
				}
			}else if(ball.getX() < topleft.getX() ){
				xNearest = topleft.getX();
			}else if(ball.getX() > topleft.getX()+width ){
				xNearest = topleft.getX()+width;
			}
			//Y values for offset
			if(ball.getDy() > 0){
				yNearest = topleft.getY();
			}else{
				yNearest = topleft.getY()+length;
			}
		}else{
			//Y values for offset
			if((ball.getY() >= topleft.getY() )&& (ball.getY()<=topleft.getY()+width)){
				if(ball.getDy() > 0){
					yNearest = ball.getY();
				}else{
					yNearest = ball.getY();
				}
			}else if(ball.getY() < topleft.getY() ){
				yNearest = topleft.getY();
			}else if(ball.getY() > topleft.getY()+width ){
				yNearest = topleft.getY()+width;
			}
			//X values for offset
			if(ball.getDx() > 0){
				xNearest = topleft.getX();
			}else{
				xNearest = topleft.getX()+length;
			}
		}
		
		return new Point(xNearest,yNearest);
	}
	
	/**
	 * Returns whether ball has collided with paddle
	 * *also switches velocities of ball if true*
	 * 
	 * @param ball 
	 */
	public void ballCollision(Ball ball){
		Point near = findNearest(ball);
		
		if(near.distanceTo(new Point(ball.getX(),ball.getY())) <= ball.getRadius()){
			if(vertical){
				if(near.getX()>topleft.getX() && near.getX()< topleft.getX() + length){
					
					ball.setDy(-ball.getDy());
				}
				if(near.getY()>topleft.getY() && near.getY()< topleft.getY() + width){
					ball.setDx(-ball.getDx());
				}
			}else{
				if(near.getX()>topleft.getX() && near.getX()< topleft.getX() + width){
					ball.setDy(-ball.getDy());
				}
				if(near.getY()>topleft.getY() && near.getY()< topleft.getY() + length){
					ball.setDx(-ball.getDx());
				}
			}

		}
		//return false;
	}
}
