package ca.ensf380.ucalgary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

/**
 * Referenced code:
 * @author BroCode (Youtube)
 */


public class Main {
	// Temporary holder for extracting map.csv
	public static void main(String[] args) { 
		// Implement GUI section
		BufferedReader reader = null;
		ArrayList<String[]> stations = new ArrayList<String[]>();;
		String line;

		try {
			// fetches Map.csv
			reader = new BufferedReader(new FileReader("Map.csv"));
			// While loop adds and edits the CSV for usage in String[] arrayList
			while ((line = reader.readLine()) != null) {
				String[] row = line.split(",");
				if (row.length > 8) {
					// removes quotes
					row[7] = row[7].replaceAll("\"", "");
					row[8] = row[8].replaceAll("\"", "");
				}
				// Removes leading whitespace
				row[4] = row[4].trim();
				stations.add(row);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		TrainDisplay nextStations = new TrainDisplay(null);
		
		
		
		
		
		
		
		
		
		
		JFrame frame = new JFrame();
		frame.setLayout(null);
		frame.setSize(1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		frame.setVisible(true);
		frame.setResizable(false);
		
		Timer timer = new Timer();
		boolean CheckFirstFile = false;
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
 
			}
		};
		
	}
	
}
