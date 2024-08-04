package ca.ucalgary.edu.ensf380;

import java.awt.Color;
import java.awt.GridBagLayout;


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
	/**
	 * Formats display and fetches data from newsApi class
	 * @param args 
	 */
	public NewsDisplay(String[] args) {
		super();
		display.setOpaque(true);
		display.setBackground(Color.white);
		display.setBounds(0, 600, 1090, 120);
		display.setLayout(new GridBagLayout());
		newsDescription = NewsAPI.mainNews(args);
		// Add newsDescription[newsPosition++] when testing API calls into the JLabel instead of Lorem ipsum
		// Test string: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
		newsLabel = new JLabel(newsDescription[newsPosition++]);
		display.add(newsLabel);
	}
	public JLabel getNewsLabel() {
		return newsLabel;
	}
	public void setNewsLabel(JLabel newsLabel) {
		this.newsLabel = newsLabel;
	}
	/**
	 * Updates display and fetches data from newsApi class
	 */
	public void updateDisplay() {
		while (newsDescription[newsPosition] == null) {
			if (newsPosition == newsDescription.length) {
				newsPosition = 0;
				break;
			}
			newsPosition++;
		}
		// add newsDescription[newsPosition] to setText instead of "Test"
		newsLabel.setText(newsDescription[newsPosition]);
		newsLabel.revalidate();
	}
}
