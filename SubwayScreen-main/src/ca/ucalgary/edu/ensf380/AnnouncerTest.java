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
		try {
			Announcer.playAnnouncer(validStation);
		}catch(Exception e) {
			
		}
	}
	
	
	@Test
	void testPlayAnnouncerInValid() {
		String inValidStation ="ENSF Station";
		
		
		IOException thrownException = assertThrows(IOException.class, () -> { Announcer.playAnnouncer(inValidStation);});
			assertEquals("File does not exist", thrownException.getMessage());
		
			
		
	}


}
