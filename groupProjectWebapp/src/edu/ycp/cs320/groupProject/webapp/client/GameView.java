package edu.ycp.cs320.groupProject.webapp.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
				
				canvas.addKeyDownHandler(new KeyDownHandler() {
					@Override
					public void onKeyDown(KeyDownEvent event) {
						handleKeyDown(event);
					}
				});
				canvas.addKeyUpHandler(new KeyUpHandler() {
					@Override
					public void onKeyUp(KeyUpEvent event) {
						handleKeyUp(event);
					}
				});
				
				initWidget(panel);
		
				this.timer = new Timer() {
					@Override
					public void run() {
						if (model != null) {
							handleTimerTick();
						}
					}
				};
	}
	
	public void setController(StageController controller) {
		this.controller = controller;	
	}
	
	protected void handleKeyDown(KeyDownEvent event) {
		if (event.isLeftArrow() || event.isUpArrow()) {
			model.getSelf().getController().setMovingLeft(true);
		}
		if (event.isRightArrow() || event.isDownArrow()) {
			model.getSelf().getController().setMovingRight(true);
		}
	}

	protected void handleKeyUp(KeyUpEvent event) {
		if (event.isLeftArrow() || event.isUpArrow()) {
			model.getSelf().getController().setMovingLeft(false);
		}
		if (event.isRightArrow() || event.isDownArrow()) {
			model.getSelf().getController().setMovingRight(false);
		}
	}
	
	public void startAnimation() {
		timer.scheduleRepeating(1000 / 30);
	}

	private int tickCount = 0;
	
	protected void handleTimerTick() {
		
		// See if there is an updated Ball state
		if (tickCount % 3 == 0) {
			RPC.gameStateSvc.getUpdatedBall(new AsyncCallback<Ball>() {
				
				@Override
				public void onSuccess(Ball result) {
					if (result != null) {
						// Ball was updated
						model.setBall(result);
						GWT.log("BallUpdate");
					} else {
						// no update
						GWT.log("No ball status update");
					}
				}
				
				@Override
				public void onFailure(Throwable caught) {
					// Error getting updated Ball status
					GWT.log("Error getting ball status update", caught);
				}
			});
		}
		tickCount++;
		
		controller.timerTick(model);
		paint();
	}
	
	public void setModel(Stage model) {
		this.model = model;
	}

	protected void paint() {
		
		
		bufCtx.setFillStyle("white");
		bufCtx.fillRect(0, 0, Stage.WIDTH, Stage.HEIGHT); // paint background		
		
		for(Paddle temp: model.getPaddles()){
			if(temp.equals(model.getSelf().getController().getControlledPaddle())){
				bufCtx.setFillStyle("red");
			}else{
			bufCtx.setFillStyle("gray");
			}
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
			bufCtx.beginPath();
			bufCtx.arc(ball.getX(), ball.getY(), ball.getRadius(), 0, 2*Math.PI, false); 		
			bufCtx.closePath();
			bufCtx.fill();
			ctx.drawImage((CanvasElement) buffer.getElement().cast(), 0, 0);
			
	}	

}
