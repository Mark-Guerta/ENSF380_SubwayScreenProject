package ca.ucalgary.edu.ensf380;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.time.*;

import javax.swing.*;
/**
 *  Uses the weatherParser to print weather on the console and displays the time on the screen.
 * 	@author Mark Guerta
 * 	@author Saif Youssef
 *  @version 1.0
 */
public final class WeatherDisplay extends Display{
	private JLabel timeLabel;
	/**
	 *  Formats and fetches weather data from weatherReportParse
	 */
	public WeatherDisplay(String[] args) {
		super();
		display.setBounds(0, 120, 432, 480);
		display.setLayout(new GridBagLayout());
		display.setOpaque(true);
		display.setBackground(Color.white);
		timeLabel = new JLabel(LocalDateTime.now().toString());
		display.add(timeLabel);

		WeatherReportParse.weatherMain(args);
	}
	/**
	 * Updates and fetches weather data from weatherReportParse
	 */
	public void updateDisplay() {
		timeLabel.setText(LocalDateTime.now().toString());
		timeLabel.revalidate();
		display.revalidate();
	}
	// Setters and getters

	/**
	 * Gets the time label.
	 *
	 * @return the time label.
	 */
	public JLabel getTimeLabel() {
	    return timeLabel;
	}

	/**
	 * Sets the time label.
	 *
	 * @param timeLabel the time label to set.
	 */
	public void setTimeLabel(JLabel timeLabel) {
	    this.timeLabel = timeLabel;
	}

}
