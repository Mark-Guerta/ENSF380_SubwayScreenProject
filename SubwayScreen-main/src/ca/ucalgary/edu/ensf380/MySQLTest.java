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
	File testFile;
	@BeforeEach
	void setUp() throws Exception {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/png", "root", "ENSF380FinalProject");
		statement  = connection.createStatement();
		testFile = new File("ads", "AD1,png");
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
	@Test 
	void testResizeImage() {
		assertDoesNotThrow(() -> MySQL.resizeImage(new File("trainSymbols","TrainSymbolRed.png"), 1, 1));
	}

}
