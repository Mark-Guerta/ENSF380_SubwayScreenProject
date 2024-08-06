package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JLabel;
/**
 *  Tests weather display functionality
 * 	@author Mark Guerta
 * 	@author Saif Youssef
 *  @version 1.0
 */
public class WeatherDisplayTest {
	WeatherDisplay weatherDisplay;
	String[] args = {"T1", "Calgary"};
	/**
	 * Tests if WeatherDisplay is successful
	 */
	void testWeatherDisplay() {
		weatherDisplay = new WeatherDisplay(args);
		assertNotNull(weatherDisplay);
	}
	/**
	 * Tests for updateDisplay for exceptions
	 */
	void testUpdateDisplay() {
		assertDoesNotThrow(()->weatherDisplay.updateDisplay());
	}
	/**
	 * Tests TimeLabel getter is not null.
	 */
	void testGetTimeLabel() {
		assertNotNull(weatherDisplay.getTimeLabel());
	}
	/**
	 * Tests TimeLabel setter is not null.
	 */
	void testSetTimeLabel() {
		JLabel newLabel = new JLabel();
		weatherDisplay.setTimeLabel(newLabel);
		assertEquals(newLabel, weatherDisplay.getTimeLabel());
	}
}
