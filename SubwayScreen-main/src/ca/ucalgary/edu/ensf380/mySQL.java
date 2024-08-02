package ca.ucalgary.edu.ensf380;
import java.io.IOException;
import java.sql.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

public class mySQL {

	public static void main(String[] args) {
		try {
			Connection myconnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/pdf", "root", "QWER1234");
			
			Statement myStmt = myconnect.createStatement();
			
			
			ResultSet myRs = myStmt.executeQuery("select * from pdf_files");
			
			while (myRs.next()) {
				String fileName = myRs.getString("file_name");
				String filePath = myRs.getString("file_path");
				System.out.println(fileName + ", " + filePath);
				
				
				File adFile = new File(filePath);
				if (adFile.exists()) {
					JFrame frame = new JFrame(fileName);
					frame.setSize(800, 600);// adjust frame size
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
					
					ImageIcon imageIcon  = new ImageIcon(ImageIO.read(adFile));
					JLabel imageLabel = new JLabel(imageIcon);
					
					frame.add(imageLabel);
					frame.pack();
					frame.setVisible(true);
					
				}else {
					System.out.println(" file cannot be fetched");
				}
				
				
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

}
