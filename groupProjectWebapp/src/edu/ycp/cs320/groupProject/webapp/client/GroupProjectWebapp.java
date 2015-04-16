package edu.ycp.cs320.groupProject.webapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import edu.ycp.cs320.groupProject.webapp.shared.controller.StageController;
import edu.ycp.cs320.groupProject.webapp.shared.model.Ball;
import edu.ycp.cs320.groupProject.webapp.shared.model.Stage;

public class GroupProjectWebapp implements EntryPoint {

	public void onModuleLoad() {
	
		Stage game = new Stage();
		
		StageController controller = new StageController();
		controller.initModel(game);
		
		GameView view = new GameView();
		view.setModel(game);
		view.setController(controller);
		
		FlowPanel panel = new FlowPanel();
		
		panel.setSize("0px", "0px");
		
		RootLayoutPanel.get().add(panel);
		RootLayoutPanel.get().add(view);
		
		view.startAnimation();
		
	}
}
