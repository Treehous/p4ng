package edu.ycp.cs320.groupProject.webapp.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.ycp.cs320.groupProject.webapp.client.GameStateService;
import edu.ycp.cs320.groupProject.webapp.shared.model.Ball;

public class GameStateServiceImpl extends RemoteServiceServlet implements GameStateService {
	private static final long serialVersionUID = 1L;

	@Override
	public Ball getUpdatedBall(Ball ball) {
		//System.out.println("getUpdatedBall() called on server");
		if(ball.getCollision()){
			System.out.println("Ball updated on server");
			return ball;
		}
		return null;
	}


}
