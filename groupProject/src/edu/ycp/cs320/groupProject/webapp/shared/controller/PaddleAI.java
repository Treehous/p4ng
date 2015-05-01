package edu.ycp.cs320.groupProject.webapp.shared.controller;

import edu.ycp.cs320.groupProject.webapp.shared.model.Ball;
import edu.ycp.cs320.groupProject.webapp.shared.model.Paddle;
import edu.ycp.cs320.groupProject.webapp.shared.model.Point;
import edu.ycp.cs320.groupProject.webapp.shared.model.Stage;

public class PaddleAI {
	Paddle ownedPaddle;

	public PaddleAI(Paddle paddle) {
		ownedPaddle = paddle;
	}

	public void PaddleControl(Ball targetBall) {
		//Run if distance from ball to paddle is less than 250
		if (ownedPaddle.getTopLeft().distanceTo(new Point(targetBall.getX(), targetBall.getY())) < 250) {
			if (ownedPaddle.isVertical()) {
				if (ownedPaddle.getTopLeft().getY() + ownedPaddle.getWidth()
						/ 2 > targetBall.getY()) {
					// move left(up)
					if (ownedPaddle.getTopLeft().getY() > 0) {
						ownedPaddle.setTopLeft(new Point(ownedPaddle
								.getTopLeft().getX(), ownedPaddle.getTopLeft()
								.getY() - ownedPaddle.getSpeed()));
					}
				} else if (ownedPaddle.getTopLeft().getY()
						+ ownedPaddle.getWidth() / 2 < targetBall.getY()) {
					// move right(down)
					if (ownedPaddle.getTopLeft().getY()
							+ ownedPaddle.getWidth() < Stage.HEIGHT) {
						ownedPaddle.setTopLeft(new Point(ownedPaddle
								.getTopLeft().getX(), ownedPaddle.getTopLeft()
								.getY() + ownedPaddle.getSpeed()));
					}
				}
				// Covers nonVertical paddles
			} else {
				if (ownedPaddle.getTopLeft().getX() + ownedPaddle.getWidth()
						/ 2 > targetBall.getX()) {
					// move left
					if (ownedPaddle.getTopLeft().getX() > 0) {
						ownedPaddle.setTopLeft(new Point(ownedPaddle
								.getTopLeft().getX() - ownedPaddle.getSpeed(),
								ownedPaddle.getTopLeft().getY()));
					}
				} else if (ownedPaddle.getTopLeft().getX()
						+ ownedPaddle.getWidth() / 2 < targetBall.getX()) {
					// move right(down)
					if (ownedPaddle.getTopLeft().getX()
							+ ownedPaddle.getWidth() < Stage.WIDTH) {
						ownedPaddle.setTopLeft(new Point(ownedPaddle
								.getTopLeft().getX() + ownedPaddle.getSpeed(),
								ownedPaddle.getTopLeft().getY()));
					}
				}
			}
		}
		ownedPaddle.ballCollision(targetBall);
	}
}
