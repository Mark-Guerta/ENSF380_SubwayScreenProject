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
	private int currentStation;
	private String Line;
	private String direction;
	ArrayList<String[]> stationArray;
	private JLabel[] stations;

	public TrainDisplay(JLabel[] stations) {
		this.stations = stations; 
	}


	public int getCurrentTrain() {
		return currentStation;
	}


	public void setCurrentTrain(int currentTrain) {
		this.currentStation = currentTrain;
	}

	public JLabel[] getStation() {
		return stations;
	}


	public void setStation(JLabel[] station) {
		this.stations = station;
	}
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getLine() {
		return Line;
	}
	public void setLine(String line) {
		Line = line;
	}
	public void activateTrainDisplay(JFrame frame) {
		// 1080x180 resolution

		String file = "stations720p.png";
		JPanel nextStations = new JPanel();
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel imgStations = new JLabel(new ImageIcon(myPicture));
		nextStations.add(imgStations);
		nextStations.setBounds(0, 0, 1080, 180);
		frame.add(nextStations);
	}
	public void updateForward() {
		final int redLast = 44;
		final int blueLast = 88;
		final int greenLast = 120;
		boolean check = false;
		for (int i = 0; i < stations.length; i++) {
			if (currentStation == redLast || currentStation == blueLast || currentStation == greenLast) {
				check = true;
				stations[i].setText("");
				stations[i].revalidate();
			}
			else if(check) {
				stations[i].setText("");
				stations[i].revalidate();
			}
				
			else {
				stations[i].setText(stationArray.get(currentStation)[3]);
				stations[i].revalidate();
			}
				
		}
	}
	public void updateBackward() {
		final int redFirst = 1;
		final int blueFirst = 45;
		final int greenFirst = 89;
		boolean check = false;
		for (int i = 0; i < stations.length; i++) {
			if (currentStation == redFirst || currentStation == blueFirst || currentStation == greenFirst) {
				check = true;
				stations[i].setText("");
				stations[i].revalidate();
			}
			else if(check) {
				stations[i].setText("");
				stations[i].revalidate();
			}
				
			else {
				stations[i].setText(stationArray.get(currentStation)[3]);
				stations[i].revalidate();
			}

		}
		
	}



}
