package edu.ycp.cs320.groupProject.webapp.client;

import com.google.gwt.core.shared.GWT;

public class RPC {
	public static final GameStateServiceAsync gameStateSvc =
			GWT.create(GameStateService.class);
}
