package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * 	AnnouncerTest.java
 * 	@author Mark Guerta
 * 	@author Saif Youssef
 *  @version 1.0
 */
class AnnouncerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
// tests play announcer with a valid station name
	@Test
	void testPlayAnnouncerValid() {
		String validStation ="Ashland Station";
		Announcer announcer = new Announcer();
		announcer.setStation(validStation);
		assertDoesNotThrow(() -> announcer.playAnnouncer());
	}
	
	// tests the play announcer method with an invalid station name 
	@Test
	void testPlayAnnouncerInValid() {
		String inValidStation ="ENSF Station";
		Announcer announcer = new Announcer();
		announcer.setStation(inValidStation);
		assertDoesNotThrow(() -> announcer.playAnnouncer());
		
	
		
	}


}
