package edu.ycp.cs320.groupProject.webapp.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.ycp.cs320.groupProject.webapp.client.GameStateService;
import edu.ycp.cs320.groupProject.webapp.shared.model.Ball;

public class GameStateServiceImpl extends RemoteServiceServlet implements GameStateService {
	private static final long serialVersionUID = 1L;

	@Override
	public Ball getUpdatedBall() {
		System.out.println("getUpdatedBall() called on server");
		
		//TODO: Find the correct way to get the paddle boolean to interact with this RPC
		return null;
	}

}
