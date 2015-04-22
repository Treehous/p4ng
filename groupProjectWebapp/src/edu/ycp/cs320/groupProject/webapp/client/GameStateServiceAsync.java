package edu.ycp.cs320.groupProject.webapp.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.ycp.cs320.groupProject.webapp.shared.model.Ball;

public interface GameStateServiceAsync {

	void getUpdatedBall(AsyncCallback<Ball> callback);

}
