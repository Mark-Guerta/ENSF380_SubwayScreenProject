package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.text.Document;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		String[] newsDescription = NewsAPI.invokeApi(keyword);
		
		String[] expected = {"Calgary"};
		
		assertNotNull(newsDescription);
		assertArrayEquals(expected, newsDescription);
		
		
		
	}

	


}

