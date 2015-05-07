package edu.ycp.cs320.groupProject.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.groupProject.webapp.shared.model.Paddle;
import edu.ycp.cs320.groupProject.webapp.shared.model.Point;

public class PaddleTest {

	
		Paddle paddle;
		Point topleft;
		int width = 125;
		int length = 20;
		boolean vertical = false;
		int speed = 5;
		boolean playerControlled;	
		boolean movingLeft,movingRight;
		int x = 5;
		int y = 5;
		
	
	@Before
	public void setUp() throws Exception {
	playerControlled = true;	
	movingLeft = false;
	movingRight = false;
	topleft = new Point(x,y);	
	paddle = new Paddle(x, y, vertical, playerControlled);
	
	}

	@Test
	public void testGetTopLeft() {
		assertEquals(topleft.getX(), paddle.getTopLeft().getX());
		assertEquals(topleft.getY(), paddle.getTopLeft().getY());
	}
	
	@Test
	public void testGetSpeed() {
		assertEquals(speed, paddle.getSpeed());
	}
	
	@Test
	public void testGetPlayerControl() {
		assertEquals(playerControlled, paddle.getPlayerControl());
	}
	
	@Test
	public void testGetMovingRight() {
		assertEquals(movingRight, paddle.getMovingRight());
	}
	
	@Test
	public void testGetMovingLeft() {
		assertEquals(movingLeft, paddle.getMovingLeft());
	}
	
	@Test
	public void testisVertical() {
		assertEquals(vertical, paddle.isVertical());
	}
	
	@Test
	public void testSetTopLeft() {
		paddle.setTopLeft(topleft);
		assertEquals(topleft, paddle.getTopLeft());
	}
	
	@Test
	public void testsetSpeed() {
		paddle.setSpeed(speed);
		assertEquals(speed, paddle.getSpeed());
	}
	
	@Test
	public void testSetPlayerControl() {
		paddle.setPlayerControl(playerControlled);
		assertEquals(playerControlled, paddle.getPlayerControl());
	}
	
	@Test
	public void testSetMovingRight() {
		paddle.setMovingRight(movingRight);
		assertEquals(movingRight, paddle.getMovingRight());
	}
	
	@Test
	public void testSetMovingLeft() {
		paddle.setMovingLeft(movingLeft);
		assertEquals(movingLeft, paddle.getMovingLeft());
	}
	
}
