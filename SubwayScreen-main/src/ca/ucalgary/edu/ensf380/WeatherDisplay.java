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
public class WeatherDisplay {
	private JPanel display;
	private JLabel timeLabel;
	/**
	 *  Formats and fetches weather data from weatherReportParse
	 */
	public WeatherDisplay() {
		display = new JPanel();
		display.setBackground(Color.white);
		display.setBounds(0, 120, 432, 480);
		display.setLayout(new GridBagLayout());
		timeLabel = new JLabel(LocalDateTime.now().toString());
		display.add(timeLabel);

		weatherReportParse.weatherMain();
	}
	/**
	 * Updates and fetches weather data from weatherReportParse
	 */
	public void updateWeatherDisplay() {
		timeLabel.setText(LocalDateTime.now().toString());
		timeLabel.revalidate();
		display.revalidate();
	}
	public JPanel getDisplay() {
		return display;
	}
	public void setDisplay(JPanel display) {
		this.display = display;
	}
	public JLabel getTimeLabel() {
		return timeLabel;
	}
	public void setTimeLabel(JLabel timeLabel) {
		this.timeLabel = timeLabel;
	}

}
