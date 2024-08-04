package ca.ucalgary.edu.ensf380;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.*;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * 	TrainDisplay.java
 * 	@author Mark Guerta
 * 	@author Saif Youssef
 *  @version 1.0
 */
public final class TrainDisplay extends Display {
	private int currentStation;
	private String Line;
	private String direction;
	private ArrayList<String[]> stationArray;
	private JLabel[] stations;
	private Announcer announcer;
	// Variables below stay local to the object 
	private int redFirst;
	private int blueFirst;
	private int greenFirst;
	private int redLast;
	private int blueLast;
	private int greenLast;
	
	public TrainDisplay(String train, ArrayList<String[]> stationArray, Announcer announcer) {
		// Constructor initializes values based on map.csv
		super();
		this.stations = new JLabel[5];
		this.stationArray = stationArray;
		this.announcer = announcer;
		redFirst = -1;
		redLast = -1;
		blueFirst = -1;
		blueLast = -1;
		greenFirst = -1;
		greenLast = -1;
		// Extracts station from train
		stationMatcher(train);
		try {
			// Sets train line colour
			if (train.contains("R"))
				Line = "R";
			else if (train.contains("G"))
				Line = "G";
			else if (train.contains("B"))
				Line = "B";
			else
				throw new Exception("Unknown train line");
			// Sets train direction
			if (train.contains("F"))
				direction = "F";
			else if (train.contains("B"))
				direction = "B";
			else
				throw new Exception("Train direction error");
			// Sets train line start and end
			for (int i = 1;i < stationArray.size();i++) {
				if (stationArray.get(i)[1].contains("R") && redFirst == -1) {
					redFirst = Integer.parseInt(stationArray.get(i)[0]);		
				}
				else if(stationArray.get(i)[1].contains("B") && redLast == -1) {
					redFirst = Integer.parseInt(stationArray.get(i - 1)[0]);
					blueFirst = Integer.parseInt(stationArray.get(i)[0]);
				}
				else if(stationArray.get(i)[1].contains("G") && blueLast == -1 && stationArray.getLast()[1].contains("G")) {
					blueLast = Integer.parseInt(stationArray.get(i - 1)[0]);
					greenFirst = Integer.parseInt(stationArray.get(i)[0]);
					break;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		// Creates JLabel Stations to show on screen
		boolean check = false;
		stations[0] = new JLabel(stationArray.get(currentStation)[4]);
		for (int j = 1; j < 5; j++) {
			stations[j] = new JLabel("",SwingConstants.CENTER);
			if (direction.equals("F")) {
				if (currentStation == redLast || currentStation == blueLast || currentStation == greenLast) {
					check = true;
					stations[j].setText("");
				}
				else if(check) {
					stations[j].setText("");
				}
				else {
					stations[j].setText(stationArray.get(currentStation + j)[4]);
				}	
			}
			else {
				if (currentStation == redLast || currentStation == blueLast || currentStation == greenLast) {
					check = true;
					stations[j].setText("");
				}
				else if(check) {
					stations[j].setText("");
				}
				else {
					stations[j].setText(stationArray.get(currentStation - j)[4]);
				}	
			}
		}
		// Adjusts JLabel position
		stations[0].setBounds(15, 0, 200, 180);
		stations[1].setBounds(210, 0, 200, 180);
		stations[2].setBounds(435, 0, 200, 180);
		stations[3].setBounds(670, 0, 200, 180);
		stations[4].setBounds(900, 0, 200, 180);
		
		String file = "stations720p.png";
		BufferedImage myPicture = null;

		try {
			myPicture = ImageIO.read(new File(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Adds images and JLabels to JLayeredPane then to frame
		JLabel imgStations = new JLabel(new ImageIcon(myPicture));
		imgStations.setSize(imgStations.getPreferredSize());
		display.add(imgStations, JLayeredPane.DEFAULT_LAYER);
		display.setBounds(0, 0, 1080, 120);
		for (int i = 0; i < 5; i++)
			display.add(stations[i], JLayeredPane.PALETTE_LAYER);
	}

	// Updates train stations on screen
	public void updateDisplay() {
		boolean check = false;
		if(direction.equals("F")) {
			currentStation = currentStation + 1;
			for (int i = 0; i < 5; i++) {
				// Checks if at the end of line
				
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
					stations[i].setText(stationArray.get(currentStation + i)[4]);
					stations[i].revalidate();
				}	
			}
			announcer.setStation(stationArray.get(currentStation + 1)[4]);
			announcer.playAnnouncer();
		}
		else{
			currentStation = currentStation - 1;
			for (int j = 0; j < 5; j++) {	
				if (currentStation == redFirst || currentStation == blueFirst || currentStation == greenFirst) {
					check = true;
					stations[j].setText("");
					stations[j].revalidate();
				}
				else if(check) {
					stations[j].setText("");
					stations[j].revalidate(); 
				}	
				else {
					stations[j].setText(stationArray.get(currentStation - j)[4]);
					stations[j].revalidate();
				}
			}
			announcer.setStation(stationArray.get(currentStation - 1)[4]);
			announcer.playAnnouncer();
		}

	}
	// Sorts extracting station number from train
	private void stationMatcher(String train) {
		Pattern pattern = Pattern.compile("([RGB]\\d\\d)");
		Matcher matcher =  pattern.matcher(train);
		matcher.find();
		String stationCode = matcher.group(1);
		for (int i = 0; i < stationArray.size(); i++) {
			if (stationCode.equals(stationArray.get(i)[3])) {
				currentStation = Integer.parseInt(stationArray.get(i)[0]);
				break;
			}
		}
	}
	// Setters and Getters
	public int getCurrentStation() {
		return currentStation;
	}

	public void setCurrentStation(int currentStation) {
		this.currentStation = currentStation;
	}

	public String getLine() {
		return Line;
	}

	public void setLine(String line) {
		Line = line;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public ArrayList<String[]> getStationArray() {
		return stationArray;
	}

	public void setStationArray(ArrayList<String[]> stationArray) {
		this.stationArray = stationArray;
	}

	public JLabel[] getStations() {
		return stations;
	}

	public void setStations(JLabel[] stations) {
		this.stations = stations;
	}

	public Announcer getAnnouncer() {
		return announcer;
	}

	public void setAnnouncer(Announcer announcer) {
		this.announcer = announcer;
	}

	public int getRedFirst() {
		return redFirst;
	}

	public void setRedFirst(int redFirst) {
		this.redFirst = redFirst;
	}

	public int getBlueFirst() {
		return blueFirst;
	}

	public void setBlueFirst(int blueFirst) {
		this.blueFirst = blueFirst;
	}

	public int getGreenFirst() {
		return greenFirst;
	}

	public void setGreenFirst(int greenFirst) {
		this.greenFirst = greenFirst;
	}

	public int getRedLast() {
		return redLast;
	}

	public void setRedLast(int redLast) {
		this.redLast = redLast;
	}

	public int getBlueLast() {
		return blueLast;
	}

	public void setBlueLast(int blueLast) {
		this.blueLast = blueLast;
	}

	public int getGreenLast() {
		return greenLast;
	}

	public void setGreenLast(int greenLast) {
		this.greenLast = greenLast;
	}
	
}
