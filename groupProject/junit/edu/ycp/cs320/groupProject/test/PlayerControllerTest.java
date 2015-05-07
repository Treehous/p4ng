package edu.ycp.cs320.groupProject.test;

import static org.junit.Assert.*;
//import edu.ycp.cs320.groupProject.webapp.shared.controller;
import edu.ycp.cs320.groupProject.webapp.shared.model.Paddle;
import org.junit.Before;
import org.junit.Test;

public class PlayerControllerTest {

	private Paddle paddle;
	private Paddle paddle2;

	@Before
	public void setUp() {
		paddle = new Paddle(20, 30, false, true);
		paddle2 = new Paddle(30, 20, true, false);
		paddle.setMovingLeft(true);
		paddle.setMovingRight(false);
	}

	@Test
	public void testGetMovingRight() {
		assertEquals(false, paddle.getMovingRight());
	}

	@Test
	public void testGetMovingLeft() {
		assertEquals(true, paddle.getMovingLeft());
	}

	@Test
	public void testSetMovingRight() {
		paddle.setMovingRight(true);
		assertEquals(true, paddle.getMovingRight());
		paddle2.setMovingRight(false);
		assertEquals(false, paddle2.getMovingRight());

	}

	@Test
	public void testSetMovingLeft() {
		paddle.setMovingLeft(false);
		assertEquals(false, paddle.getMovingLeft());
		paddle2.setMovingLeft(true);
		assertEquals(true, paddle2.getMovingLeft());
	}

}
