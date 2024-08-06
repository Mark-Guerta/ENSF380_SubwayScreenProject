package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.text.Document;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * 	NewsApiTest.java
 * 	@author Mark Guerta
 * 	@author Saif Youssef
 *  @version 1.0
 */
class NewsApiTest {
	String result;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testInvokeApi() {
		String keyword = "Calgary";
		//NewsAPI newsAPI = new NewsAPI();
		
		String[] newsDescriptions = NewsAPI.invokeApi(keyword);
		
		
		
		assertNotNull(newsDescriptions);
		assertTrue(newsDescriptions.length > 0);
		
		
		
	}

	


}

