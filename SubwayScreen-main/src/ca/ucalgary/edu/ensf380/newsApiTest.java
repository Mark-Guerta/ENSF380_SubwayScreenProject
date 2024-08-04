package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class newsApiTest {
	String result;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testNewsApi() {
		fail("Not yet implemented");
	}

	@Test
	void testMainNews() {
		//fail("Not yet implemented");
	}
	@Test
	void testKeyword() {
		String keyword = "test";
	    result = newsApi.invokeApi(keyword);
	    
		assertEquals
	}


}
