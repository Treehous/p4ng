package edu.ycp.cs320.groupProject.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.groupProject.webapp.shared.model.Ball;

public class BallTest {

	private static final double DELTA = 0.00001;
	
	private Ball ball;
	
	int x = 50;
	int y = 60;
	int radius = 13; 
	double dx = 1;
	double dy = -1;
	boolean collision = false;
	
	
	@Before
	public void setUp() throws Exception {
		
	ball = new Ball(x,y,dx,dy);
	ball.setRadius(radius);
	ball.setCollision(collision);
	}

	@Test
	public void testGetX() {
		assertEquals(50, ball.getX());	
	}
	
	@Test
	public void testGetY() {
		assertEquals(60, ball.getY());
	}
	
	@Test
	public void testGetRadius() {
		assertEquals(13, ball.getRadius());
	}
	
	@Test
	public void testgetdx() {
		assertEquals(1, ball.getDx(), DELTA);
	}
	
	@Test
	public void testgetdy() {
		assertEquals(-1, ball.getDy(), DELTA);
	}
	
	@Test
	public void testGetCollision() {
		assertEquals(false, ball.getCollision());
	}
	
	@Test
	public void testSetX() {
		ball.setX(10);
		assertEquals(10, ball.getX());
	}
	
	@Test
	public void testSetY() {
		ball.setY(14);
		assertEquals(14, ball.getY());
	}
	
	@Test
	public void testSetDx() {
		ball.setDx(-1);
		assertEquals(-1, ball.getDx(), DELTA);
	}
	
	@Test
	public void testSetDy() {
		ball.setDy(1);
		assertEquals(1, ball.getDy(), DELTA);
	}
	
	@Test
	public void testSetRadius() {
		ball.setRadius(30);
		assertEquals(30, ball.getRadius());
	}
	
	@Test
	public void testSetCollision() {
		ball.setCollision(true);
		assertEquals(true, ball.getCollision());
	}
	

}
