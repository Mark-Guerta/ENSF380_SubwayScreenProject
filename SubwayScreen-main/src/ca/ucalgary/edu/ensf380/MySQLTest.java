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
/**
 *  Tests MySQL functionality
 * 	@author Mark Guerta
 * 	@author Saif Youssef
 *  @version 1.0
 */
public class MySQLTest {
	// establishes a connection with mySQL
	Connection connection;
	Statement statement;
	File testFile;
	@BeforeEach
	void setUp() throws Exception {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/png", "root", "ENSF380FinalProject");//change password for SQL
		statement  = connection.createStatement();
		testFile = new File("ads", "AD1,png");
        }

	@AfterEach
	void tearDown() throws Exception {
	}
// ensure ads are retrievd succesfully from database
	// checks frame dimension
	// verifies returned array is not null
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
	// tests the resize image method and ensures it resizes the image without throwing exception
	@Test 
	void testResizeImage() {
		assertDoesNotThrow(() -> MySQL.resizeImage(new File("trainSymbols","TrainSymbolRed.png"), 1, 1));
	}

}
