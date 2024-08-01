package ca.ucalgary.edu.ensf380;
import java.io.IOException;
import java.sql.*;

public class mySQL {

	public static void main(String[] args) {
		try {
			Connection myconnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersDB", "root", "QWER1234");
			
			Statement myStmt = myconnect.createStatement();
			
			
			ResultSet myRs = myStmt.executeQuery("select * from student");
			
			while (myRs.next()) {
				System.out.println(myRs.getString("lastname") + ", " + myRs.getString("firstname"));
				
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

}
