package ca.ucalgary.edu.ensf380;

import java.awt.Color;
import java.awt.GridBagLayout;


import javax.swing.*;
/**
 * 	NewsDisplay.java
 * 	@author Mark Guerta
 * 	@author Saif Youssef
 *  @version 0.9
 */
public class NewsDisplay {
	private JPanel display;
	private JLabel newsLabel;
	/**
	 * Formats display and fetches data from newsApi class
	 */
	public NewsDisplay() {
		display = new JPanel();
		display.setBackground(Color.white);
		display.setBounds(0, 600, 1090, 120);
		display.setLayout(new GridBagLayout());
		// Add newsApi.newsMain() instead of substitute text
		newsLabel = new JLabel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
		display.add(newsLabel);
	}
	public JPanel getDisplay() {
		return display;
	}
	public void setDisplay(JPanel display) {
		this.display = display;
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
	public void updateNewsDisplay() {
		// Requirements: update the newsDisplay with using the description string variable in  invokeAPI of newsApi.
		newsLabel.setText("Insert news text here");
		newsLabel.revalidate();
	}
}
