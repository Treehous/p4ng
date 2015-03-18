package edu.ycp.cs320.groupProject.webapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import edu.ycp.cs320.groupProject.webapp.shared.model.Paddle;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GroupProjectWebapp implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Label label = new Label("Hello, world!");
		
		// Demonstrate that model objects can be created
		Paddle paddle = new Paddle(100, 100, true);
		
		// For now, just a hard-coded UI
		
		FlowPanel panel = new FlowPanel();
		panel.add(label);
		
		RootLayoutPanel.get().add(panel);
		RootLayoutPanel.get().setWidgetLeftRight(panel, 10.0, Unit.PX, 10.0, Unit.PX);
		RootLayoutPanel.get().setWidgetTopBottom(panel, 10.0, Unit.PX, 10.0, Unit.PX);
	}
}
