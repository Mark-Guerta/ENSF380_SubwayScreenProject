package ca.ucalgary.edu.ensf380;
import java.io.IOException;
import java.sql.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * 	Fetches images from mySQL server
 * 	@author Mark Guerta
 * 	@author Saif Youssef
 *  @version 1.0
 */
public class MySQL {
	/**
	 * Fetches and returns images in an ImageIcon array for displaying using JLabels
	 * @return advertisement ImageIcon[] array for JLabels
	 */
	public static ImageIcon[] getAds() {
		ImageIcon[] advertisement = new ImageIcon[8];
		try {
			Connection myconnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/pdf", "root", "QWER1234");
			
			Statement myStmt = myconnect.createStatement();
			

			ResultSet myRs = myStmt.executeQuery("select * from pdf_files");
			int i = 0;
			while (myRs.next()) {
				String fileName = myRs.getString("file_name");
				String filePath = myRs.getString("file_path");
				System.out.println(fileName + ", " + filePath);
				
				File adFile = new File(filePath);
				if (adFile.exists()) {
					advertisement[i++] = resizeImage(adFile, 648, 480);
				}else {
					System.out.println(" file cannot be fetched");
				}	
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		return advertisement;
	}
	
	/**
	 * Resizes images based x and y dimensions
	 * @param imgFile Image path
	 * @param x X dimension of the desired image
	 * @param y Y dimension of the desired image
	 * @return scaledIcon The resized photo as a ImageIcon
	 */
	public static ImageIcon resizeImage(File imgFile, int x, int y) {
        BufferedImage bufferImage = null;
       	try {
       		bufferImage = ImageIO.read(imgFile);
     	} catch (IOException e) {
       		// TODO Auto-generated catch block
       		e.printStackTrace();
      	}
		ImageIcon icon = new ImageIcon(bufferImage);
        Image img = icon.getImage();
		Image imgScale = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        return scaledIcon;
	}

}
