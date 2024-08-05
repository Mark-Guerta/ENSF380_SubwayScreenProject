package ca.ucalgary.edu.ensf380;
import java.io.IOException;
import java.sql.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MySQL {

	public static ImageIcon[] getAds() {
		ImageIcon[] advertisement = new ImageIcon[8];
		try {
			Connection myconnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/png", "root", "ENSF380FinalProject");
			
			Statement myStmt = myconnect.createStatement();
			

			ResultSet myRs = myStmt.executeQuery("select * from png_files");
			int i = 0;
			while (myRs.next()) {
				String fileName = myRs.getString("filename");
				String filePath = myRs.getString("filepath");
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
	private static ImageIcon resizeImage(File imgFile, int x, int y) {
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
