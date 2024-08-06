package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * 	NewsDisplayTest.java
 * 	@author Mark Guerta
 * 	@author Saif Youssef
 *  @version 1.0
 */
class NewsDisplayTest {
	NewsDisplay newsDisplay;
	String[] testNewsDescription;
	String[] args = {"1", "2"};;
	@BeforeEach
	void setUp() throws Exception {
		testNewsDescription = new String[] {
				"Calgary"
		};
		newsDisplay = new NewsDisplay(args); 
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testNewsDisplay() {
		assertNotNull(newsDisplay.getNewsLabel());
		assertNotEquals("", newsDisplay.getNewsLabel().getText());
	}

	@Test
	void testUpdateDisplay() {
		assertDoesNotThrow(() -> newsDisplay.updateDisplay());
	}
	
	@Test
	void testScrollNews() {
		assertDoesNotThrow(() -> newsDisplay.scrollNews());
	}
	

}
