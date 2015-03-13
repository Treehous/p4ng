package edu.ycp.cs320.groupProject.model;


public class Paddle {
	Point topleft;
	int width = 125;
	int length = 20;
	boolean vertical = false;
	int speed = 5;
	
	public Paddle(int x, int y, boolean vertical){
		topleft = new Point(x,y);
		this.vertical = vertical;
	}
	public void setTopLeft(Point point){
		topleft = point;
	}public void setSpeed(int speed){
		this.speed = speed;
	}
	public void scalePaddle(int percentageValue){
		double t = percentageValue/100;
		width += t*width;
	}public void restorePaddle(){
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
	}
	
	

	/**
	 * Return the nearest point on the paddle
	 * This will only work for rectangle on axis
	 * 
	 * @param ball 
	 * @return Point value of the nearest on paddle in relation to ball
	 */
	public Point findNearest(Ball ball){
		//X values for offset
		int xNearest = 0;
		if((ball.getX() >= topleft.getX() )&& (ball.getX()<=topleft.getX()+width)){
			if(ball.getDx() > 0){
				xNearest = ball.getX()+ ball.getRadius();
			}else{
				xNearest = ball.getX()- ball.getRadius();
			}
		}else if(ball.getX() < topleft.getX() ){
			xNearest = topleft.getX();
		}else if(ball.getX() > topleft.getX()+width ){
			xNearest = topleft.getX()+width;
		}
		//Y values for offset
		int yNearest = 0;
		if((ball.getY()>= topleft.getY() )&& (ball.getY()<=topleft.getY()+ length)){
			if(ball.getDy() > 0){
				yNearest = ball.getY()+ ball.getRadius();
			}else{
				yNearest = ball.getY()- ball.getRadius();
			}
		}else if(ball.getY() < topleft.getY() ){
			yNearest = topleft.getY();
		}else if(ball.getY() > topleft.getY()+length ){
			yNearest = topleft.getY()+length;
		}
		return new Point(xNearest,yNearest);
	}
	
	/**
	 * Returns whether ball has collided with paddle
	 * *also switches velocities of ball if true*
	 * 
	 * @param ball 
	 * @return True if ball collides
	 */
	public void ballCollision(Ball ball){
		Point near = findNearest(ball);
		if(near.distanceTo(new Point(ball.getX(),ball.getY())) <= ball.getRadius()*2){
			if(near.getX()>topleft.getX() && near.getX()< topleft.getX() + width){
				ball.setDx(-ball.getDx());
			}
			if(near.getY()>topleft.getY() && near.getY()< topleft.getY() + length){
				ball.setDy(-ball.getDy());
			}
			//return true;
		}
		//return false;
	}
}
