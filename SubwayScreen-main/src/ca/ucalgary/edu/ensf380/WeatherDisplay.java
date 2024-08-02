package ca.ucalgary.edu.ensf380;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.time.*;

import javax.swing.*;
/**
 * WeatherDisplay.java
 * @author Mark Guerta
 * @author Saif Youssef
 */
public class WeatherDisplay extends Display{
	private JLabel timeLabel;
	/**
	 *  Formats and fetches weather data from weatherReportParse
	 */
	public WeatherDisplay() {
		super();
		display.setBounds(0, 120, 432, 480);
		display.setLayout(new GridBagLayout());
		display.setOpaque(true);
		display.setBackground(Color.white);
		timeLabel = new JLabel(LocalDateTime.now().toString());
		display.add(timeLabel);

		weatherReportParse.weatherMain();
	}
	/**
	 * Updates and fetches weather data from weatherReportParse
	 */
	public void updateDisplay() {
		timeLabel.setText(LocalDateTime.now().toString());
		timeLabel.revalidate();
		display.revalidate();
	}
	public JLabel getTimeLabel() {
		return timeLabel;
	}
	public void setTimeLabel(JLabel timeLabel) {
		this.timeLabel = timeLabel;
	}

}
