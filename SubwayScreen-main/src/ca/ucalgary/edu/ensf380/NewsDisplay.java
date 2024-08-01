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
		newsLabel = new JLabel("This a news article...");
		display.add(newsLabel);
		frame.add(display);

	}
	public void updateNewsDisplay() {
		
	}
}
