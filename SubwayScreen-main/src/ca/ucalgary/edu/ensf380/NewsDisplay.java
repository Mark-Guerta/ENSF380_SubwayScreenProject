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
public class NewsDisplay extends Display{
	private JLabel newsLabel;
	private Timer timer;
	private int parseIndex;
	
	/**
	 * Formats display and fetches data from newsApi class
	 */
	public NewsDisplay() {
		super();
		display.setOpaque(true);
		display.setBackground(Color.white);
		display.setBounds(0, 600, 1090, 120);
		display.setLayout(new GridBagLayout());
		// Add newsApi.newsMain() instead of substitute text
		
		newsLabel = new JLabel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
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
	/**
	private void scrollTimer(){
		timer = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent e){
				parseIndex--:
				if (parseIndex < newsLabel.width){
					parseIndex = newsDisplay.getwidth();
				}
				newsLabel.setLocation(parseIndex, newLabel.getY()):
					
			}

					});
		timwe.start();
		*/
	public void updateDisplay() {
		// Requirements: update the newsDisplay with using the description string variable in  invokeAPI of newsApi.
		newsLabel.setText("Test");
		newsLabel.revalidate();
	}
}
