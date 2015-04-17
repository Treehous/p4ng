package edu.ycp.cs320.groupProject.webapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;

import edu.ycp.cs320.groupProject.webapp.shared.controller.StageController;
import edu.ycp.cs320.groupProject.webapp.shared.model.Ball;
import edu.ycp.cs320.groupProject.webapp.shared.model.Stage;

public class GroupProjectWebapp implements EntryPoint {

	public void onModuleLoad() {
	
	      final FormPanel form = new FormPanel();
	      form.setAction("/myFormHandler");

	      form.setEncoding(FormPanel.ENCODING_MULTIPART);
	      form.setMethod(FormPanel.METHOD_POST);

	      VerticalPanel logpanel = new VerticalPanel();
	      logpanel.setSpacing(10);
	      form.setWidget(logpanel);

	      final TextBox tb = new TextBox();
	      tb.setWidth("220");
	      tb.setName("Username");
	      logpanel.add(tb);
	      
	      final PasswordTextBox pb = new PasswordTextBox();
	      tb.setWidth("220");
	      tb.setName("Password");
	      logpanel.add(pb);
	   
	      logpanel.add(new Button("Submit", new ClickHandler() {
	         @Override
	         public void onClick(ClickEvent event) {
	            form.submit();					
	         }
	      }));


	      form.addSubmitHandler(new FormPanel.SubmitHandler() {
	         @Override
	         public void onSubmit(SubmitEvent event) {
	            if (tb.getText().contentEquals("user") && pb.getText().contentEquals("pass")) {
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
	            else
	            {
	               Window.alert("User/Pass invalid. Refresh and try again.");
	               event.cancel();
	            }
	            }					
	         
	      });


	      DecoratorPanel decoratorPanel = new DecoratorPanel();
	      decoratorPanel.add(form);
	      RootPanel.get().add(decoratorPanel);

	}
}
