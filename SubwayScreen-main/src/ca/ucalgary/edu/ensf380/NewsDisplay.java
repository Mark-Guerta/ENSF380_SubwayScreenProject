package ca.ucalgary.edu.ensf380;

import java.awt.Color;
import java.awt.GridBagLayout;


import javax.swing.*;

public class NewsDisplay {
	private JPanel display;
	private JLabel newsLabel;
	NewsDisplay(JFrame frame) {
		display = new JPanel();
		display.setBackground(Color.white);
		display.setBounds(0, 600, 1090, 120);
		display.setLayout(new GridBagLayout());
		// Add newsApi.newsMain() instead of substitute text
		newsLabel = new JLabel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
		display.add(newsLabel);
		frame.add(display);

	}
	public void updateNewsDisplay() {
		// Requirements: update the newsDisplay with using the description string variable in  invokeAPI of newsApi.
		newsLabel.setText("Insert news text here");
		newsLabel.validate();
		display.validate();
	}
}
