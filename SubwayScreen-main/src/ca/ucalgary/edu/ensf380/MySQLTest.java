package ca.ucalgary.edu.ensf380;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MySQLTest {
	Connection connection;
	Statement statement;

	@BeforeEach
	void setUp() throws Exception {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pdf", "root", "QWER1234");
		
		statement  = connection.createStatement();
		File testFile = new File("path/to/test_image.jpg");
        if (!testFile.exists()) {
            // Create a dummy image file if it does not exist
            BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            ImageIO.write(bufferedImage, "png", testFile);
        }
		
		
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testGetAds() {
		ImageIcon[] ads = MySQL.getAds();
		assertNotNull(ads);
		assertTrue(ads.length > 0);
		
		assertNotNull(ads[0]);
		
		Image img = ads[0].getImage();
		assertEquals(648, img.getWidth(null));
		assertEquals(480, img.getHeight(null));
		
		
		
 	}

}
