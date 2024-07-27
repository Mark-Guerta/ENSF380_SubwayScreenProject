package ca.ensf380.ucalgary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
public class TrainDisplay {
	private String currentTrain;
	private ArrayList<String[]> stations;
	private Announcer announcer;
	public TrainDisplay() {
		BufferedReader reader = null;
		String line;
		this.stations = new ArrayList<String[]>();
		this.announcer = new Announcer();
		try {
			// fetches Map.csv
			reader = new BufferedReader(new FileReader("Map.csv"));
			// While loop adds and edits the csv for usage in String[] collections
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
	}

	public ArrayList<String[]> getStations() {
		return this.stations;
	}
	public void setStations(ArrayList<String[]> stations) {
		this.stations = stations;
	}
	public String getCurrentTrain() {
		return this.currentTrain;
	}
	public void setCurrentTrain(String currentTrain) {
		this.currentTrain = currentTrain;
	}

	public Announcer getAnnouncer() {
		return this.announcer;
	}
	public void setAnnouncer(Announcer announcer) {
		this.announcer = announcer;
	}
	public void activateTrainDisplay() {
		// 1920x1080 resolution
		EventQueue.invokeLater(() -> {
			String file = "Stations.png";
			JPanel stations = new JPanel();
			BufferedImage myPicture = null;
			String[] nextStations = new String[4];
			try {
				myPicture = ImageIO.read(new File(file));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JLabel station1 = new JLabel("Maplewood Station");
			JLabel station2 = new JLabel("Maplewood Station");
			JLabel station3 = new JLabel("Maplewood Station");
			JLabel station4 = new JLabel("Maplewood Station");
			JLabel station5 = new JLabel("Maplewood Station");
			JLabel imgStations = new JLabel(new ImageIcon(myPicture));
			stations.add(imgStations);
			stations.setBounds(0,0,1920,270);
			station1.setBounds(290,0,1920,270);
			station2.setBounds(595,0,1920,270);
			station3.setBounds(905,0,1920,270);
			station4.setBounds(1215,0,1920,270);
			station5.setBounds(1525,0,1920,270);

			JFrame frame = new JFrame("Test Frame");
			frame.setLayout(null);
			frame.setSize(1920, 1080);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.add(station1);
			frame.add(station2);
			frame.add(station3);
			frame.add(station4);
			frame.add(station5);
			frame.add(stations);
			frame.setResizable(false);
			updateNext();
			
		});

		
	}

	private void updateNext() {
		// TODO Auto-generated method stub
		
	}


}
