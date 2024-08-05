package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnnouncerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testPlayAnnouncerValid() {
		String validStation ="Ashland Station";
		Announcer announcer = new Announcer();
		announcer.setStation(validStation);
		assertDoesNotThrow(() -> announcer.playAnnouncer());
	}
	
	
	@Test
	void testPlayAnnouncerInValid() {
		String inValidStation ="ENSF Station";
		Announcer announcer = new Announcer();
		announcer.setStation(inValidStation);
		assertDoesNotThrow(() -> announcer.playAnnouncer());
		
	
		
	}


}
