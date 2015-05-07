package edu.ycp.cs320.groupProject.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.groupProject.webapp.shared.model.Point;


public class PointTest {

private static final double DELTA = 0.00001;
	
	private Point origin;
	private Point p2;
	private Point p3;
	
	@Before
	public void setUp() {
		origin = new Point(0, 0);
		p2 = new Point(3, 4);
		p3 = new Point(-5, -12);
	}
	
	@Test
	public void testGetX() throws Exception {
		assertEquals(0, origin.getX());
		assertEquals(3, p2.getX());
		assertEquals(-5, p3.getX());
	}
	
	@Test
	public void testGetY() throws Exception {
		assertEquals(0, origin.getY());
		assertEquals(4, p2.getY());
		assertEquals(-12, p3.getY());
	}
	
	@Test
	public void testDistanceTo() throws Exception {
		assertEquals(5.0, origin.distanceTo(p2), DELTA);
		assertEquals(5.0, p2.distanceTo(origin), DELTA);
		assertEquals(13.0, origin.distanceTo(p3), DELTA);
		assertEquals(13.0, p3.distanceTo(origin), DELTA);
	}
}


