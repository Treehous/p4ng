package edu.ycp.cs320.groupProject.webapp.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;

import edu.ycp.cs320.groupProject.webapp.shared.controller.*;
import edu.ycp.cs320.groupProject.webapp.shared.model.Ball;
import edu.ycp.cs320.groupProject.webapp.shared.model.Paddle;
import edu.ycp.cs320.groupProject.webapp.shared.model.Stage;

public class GameView extends Composite {
	
	private Stage model;// = new Stage();
	private StageController controller;
	private Timer timer;
	private Canvas buffer;
	private Context2d bufCtx;
	private Canvas canvas;
	private Context2d ctx;
	//private Ball ball;
	
	public GameView() {
				FocusPanel panel = new FocusPanel();
				panel.setSize(Stage.WIDTH + "px", Stage.HEIGHT + "px");
				this.buffer = Canvas.createIfSupported();
				buffer.setSize(Stage.WIDTH + "px", Stage.HEIGHT + "px");
				buffer.setCoordinateSpaceWidth(Stage.WIDTH);
				buffer.setCoordinateSpaceHeight(Stage.HEIGHT);
				this.bufCtx = buffer.getContext2d();
				this.canvas = Canvas.createIfSupported();
				canvas.setSize(Stage.WIDTH + "px", Stage.HEIGHT + "px");
				canvas.setCoordinateSpaceWidth(Stage.WIDTH);
				canvas.setCoordinateSpaceHeight(Stage.HEIGHT);
				this.ctx = canvas.getContext2d();
				panel.add(canvas);
				
				initWidget(panel);
		
				this.timer = new Timer() {
					@Override
					public void run() {
						if (model != null) {
							controller.timerTick(model);
							paint();
						}
					}
				};
	}
	
	public void setController(StageController controller) {
		this.controller = controller;
		
		
	}
	
	public void startAnimation() {
		timer.scheduleRepeating(1000 / 30);
	}

	protected void handleTimerTick() {
		//controller.timerTick(model);
		paint();
	}
	
	public void setModel(Stage model) {
		this.model = model;
	}

	protected void paint() {
		
		
		bufCtx.setFillStyle("white");
		bufCtx.fillRect(0, 0, Stage.WIDTH, Stage.HEIGHT); // paint background		
		
		for(Paddle temp: model.getPaddles()){
			bufCtx.setFillStyle("gray");
			if (temp.isVertical()){
				bufCtx.fillRect(temp.getTopLeft().getX(), temp.getTopLeft().getY(), 
						temp.getHeight() , temp.getWidth());
			}else{
				bufCtx.fillRect(temp.getTopLeft().getX(), temp.getTopLeft().getY(), 
						temp.getWidth(),temp.getHeight() );
			}
			
		}
		
		Ball ball = model.getBall();
		
			bufCtx.setFillStyle("black");
			bufCtx.arc(ball.getX(), ball.getY(), 6, 90, 359); 		
			ctx.drawImage((CanvasElement) buffer.getElement().cast(), 0, 0);
			
	}	

}
