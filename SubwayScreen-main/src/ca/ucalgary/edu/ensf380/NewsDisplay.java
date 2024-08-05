package ca.ucalgary.edu.ensf380;

import java.awt.Color;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
/**
 * 	NewsDisplay.java
 * 	@author Mark Guerta
 * 	@author Saif Youssef
 *  @version 1.0
 */
public final class NewsDisplay extends Display{
	private JLabel newsLabel;
	private String[] newsDescription;
	private int newsPosition;
	private Timer timer;
	/**
	 * Formats display and fetches data from newsApi class
	 * @param args 
	 */
	public NewsDisplay(String[] args) {
		super();
		display.setOpaque(true);
		display.setBackground(Color.white);
		display.setBounds(0, 600, 1090, 120);
		newsDescription = NewsAPI.mainNews(args);
		// Add newsDescription[newsPosition++] when testing API calls into the JLabel instead of Lorem ipsum
		// Test string
		//newsLabel = new JLabel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
		newsLabel = new JLabel(newsDescription[newsPosition++]);
		newsLabel.setSize(newsLabel.getPreferredSize());
		newsLabel.setLocation(0, 30);
		display.add(newsLabel, Integer.valueOf(1));
	}
	/**
	 * Updates display and fetches data from newsApi class
	 */
	@Override
	public void updateDisplay() {
		while (newsDescription[newsPosition] == null) {
			if (newsPosition == newsDescription.length - 1) {
				newsPosition = 0;
				break;
			}
			newsPosition++;
		}
		// add newsDescription[newsPosition] to setText instead of "Test"
		newsLabel.setText(newsDescription[newsPosition]);
		newsLabel.revalidate();
	}
	/**
	 * Moves text in and out of the display
	 */
	public void scrollNews() {
		timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				if (newsLabel.getX() < -1000) {
					newsLabel.setLocation(1095, newsLabel.getY());
				}
				newsLabel.setLocation(newsLabel.getX() - 1, newsLabel.getY());
				display.repaint();
			}
		};
		timer.scheduleAtFixedRate(timerTask, 0, 10);
	}
	
	// Getter and Setters
	public JLabel getNewsLabel() {
		return newsLabel;
	}
	
	public void setNewsLabel(JLabel newsLabel) {
		this.newsLabel = newsLabel;
	}
	
	public String[] getNewsDescription() {
		return newsDescription;
	}
	
	public void setNewsDescription(String[] newsDescription) {
		this.newsDescription = newsDescription;
	}
	
	public int getNewsPosition() {
		return newsPosition;
	}
	
	public void setNewsPosition(int newsPosition) {
		this.newsPosition = newsPosition;
	}
	
}
