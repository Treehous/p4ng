package edu.ycp.cs320.groupProject.webapp.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.ycp.cs320.groupProject.webapp.shared.model.Ball;

@RemoteServiceRelativePath("gameState")
public interface GameStateService extends RemoteService {
	/**
	 * Check to see if there is an updated state for the Ball.
	 * 
	 * @return updated Ball, or null if there is no update
	 */
	public Ball getUpdatedBall();
}
