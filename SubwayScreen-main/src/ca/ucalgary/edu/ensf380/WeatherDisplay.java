package ca.ucalgary.edu.ensf380;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.time.*;

import javax.swing.*;

public class WeatherDisplay {
	private JPanel display;
	private JLabel timeLabel;

	public WeatherDisplay(JFrame frame) {
		display = new JPanel();
		display.setBackground(Color.white);
		display.setBounds(0, 120, 432, 480);
		display.setLayout(new GridBagLayout());
		timeLabel = new JLabel(LocalDateTime.now().toString());
		display.add(timeLabel);
		frame.add(display);
		weatherReportParse.weatherMain();
	}
	public void updateWeatherDisplay() {
		timeLabel.setText(LocalDateTime.now().toString());
		timeLabel.validate();
		display.validate();
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
