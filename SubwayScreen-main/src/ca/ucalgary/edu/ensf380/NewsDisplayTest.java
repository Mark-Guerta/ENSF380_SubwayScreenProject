package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NewsDisplayTest {
	NewsDisplay newsDisplay;
	String[] testNewsDescription;

	@BeforeEach
	void setUp() throws Exception {
		testNewsDescription = new String[] {
				"Calgary"
		};
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void testNewsDisplay() {
		assertNotNull(newsDisplay.getNewsLabel());
		assertEquals(testNewsDescription[0], newsDisplay.getNewsLabel());
		
	}

	

}
