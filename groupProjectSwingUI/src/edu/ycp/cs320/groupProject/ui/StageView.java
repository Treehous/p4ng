package edu.ycp.cs320.groupProject.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import edu.ycp.cs320.groupProject.webapp.shared.controller.*;
import edu.ycp.cs320.groupProject.webapp.shared.model.Ball;
import edu.ycp.cs320.groupProject.webapp.shared.model.Paddle;
import edu.ycp.cs320.groupProject.webapp.shared.model.Stage;

public class StageView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5918784966455771417L;

	
	private Stage model;
	private StageController controller;
	private Timer timer;
	
	public StageView(Stage model) {
		this.model = model;
		setPreferredSize(new Dimension( Stage.WIDTH, Stage.HEIGHT));
		
		this.timer = new Timer(1000 / 30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTimerTick();
			}
		});
	}
	
	public void setController(StageController controller) {
		this.controller = controller;
	}
	
	public void startAnimation() {
		timer.start();
	}

	protected void handleTimerTick() {
		controller.timerTick(model);
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // paint background
		Ball ball = model.getBall();
		
		
		for(Paddle temp: model.getPaddles()){
			g.setColor(Color.DARK_GRAY);
			if (temp.isVertical()){
				g.fillRect(temp.getTopLeft().getX(), temp.getTopLeft().getY(), 
						temp.getHeight() , temp.getWidth());
			}else{
				g.fillRect(temp.getTopLeft().getX(), temp.getTopLeft().getY(), 
						temp.getWidth(),temp.getHeight() );
			}
			g.setColor(Color.BLUE);
			g.fillOval(temp.findNearest(ball).getX(),temp.findNearest(ball).getY(),6,6);
		}

		
		
		g.fillOval(ball.getX()-ball.getRadius(), ball.getY()-ball.getRadius(), ball.getRadius()*2, ball.getRadius()*2);
		//g.fillRect((int) square.getX(), (int) square.getY(), (int) square.getWidth(), (int) square.getHeight());
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Stage model = new Stage();
				StageController controller = new StageController();
				
				StageView view = new StageView(model);
				view.setController(controller);
				
				controller.initModel(model);
				
				JFrame frame = new JFrame("p4ng");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(view);
				frame.pack();
				frame.setVisible(true);
				
				view.startAnimation();
			}
		});
	}
}
