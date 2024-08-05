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
 *  Displays a train line with the current station and the next upcoming stations
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
	/** 
	 * Sorts extracting station number from train
	 * @param train Train to extract station code
	 */
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
	/**
	 * Gets the current station index.
	 *
	 * @return the current station index.
	 */
	public int getCurrentStation() {
	    return currentStation;
	}

	/**
	 * Sets the current station index.
	 *
	 * @param currentStation the current station index to set.
	 */
	public void setCurrentStation(int currentStation) {
	    this.currentStation = currentStation;
	}

	/**
	 * Gets the line name.
	 *
	 * @return the line name.
	 */
	public String getLine() {
	    return Line;
	}

	/**
	 * Sets the line name.
	 *
	 * @param line the line name to set.
	 */
	public void setLine(String line) {
	    Line = line;
	}

	/**
	 * Gets the direction.
	 *
	 * @return the direction.
	 */
	public String getDirection() {
	    return direction;
	}

	/**
	 * Sets the direction.
	 *
	 * @param direction the direction to set.
	 */
	public void setDirection(String direction) {
	    this.direction = direction;
	}

	/**
	 * Gets the station array.
	 *
	 * @return the station array.
	 */
	public ArrayList<String[]> getStationArray() {
	    return stationArray;
	}

	/**
	 * Sets the station array.
	 *
	 * @param stationArray the station array to set.
	 */
	public void setStationArray(ArrayList<String[]> stationArray) {
	    this.stationArray = stationArray;
	}

	/**
	 * Gets the array of station labels.
	 *
	 * @return the array of station labels.
	 */
	public JLabel[] getStations() {
	    return stations;
	}

	/**
	 * Sets the array of station labels.
	 *
	 * @param stations the array of station labels to set.
	 */
	public void setStations(JLabel[] stations) {
	    this.stations = stations;
	}

	/**
	 * Gets the announcer.
	 *
	 * @return the announcer.
	 */
	public Announcer getAnnouncer() {
	    return announcer;
	}

	/**
	 * Sets the announcer.
	 *
	 * @param announcer the announcer to set.
	 */
	public void setAnnouncer(Announcer announcer) {
	    this.announcer = announcer;
	}

	/**
	 * Gets the index of the first red station.
	 *
	 * @return the index of the first red station.
	 */
	public int getRedFirst() {
	    return redFirst;
	}

	/**
	 * Sets the index of the first red station.
	 *
	 * @param redFirst the index of the first red station to set.
	 */
	public void setRedFirst(int redFirst) {
	    this.redFirst = redFirst;
	}

	/**
	 * Gets the index of the first blue station.
	 *
	 * @return the index of the first blue station.
	 */
	public int getBlueFirst() {
	    return blueFirst;
	}

	/**
	 * Sets the index of the first blue station.
	 *
	 * @param blueFirst the index of the first blue station to set.
	 */
	public void setBlueFirst(int blueFirst) {
	    this.blueFirst = blueFirst;
	}

	/**
	 * Gets the index of the first green station.
	 *
	 * @return the index of the first green station.
	 */
	public int getGreenFirst() {
	    return greenFirst;
	}

	/**
	 * Sets the index of the first green station.
	 *
	 * @param greenFirst the index of the first green station to set.
	 */
	public void setGreenFirst(int greenFirst) {
	    this.greenFirst = greenFirst;
	}

	/**
	 * Gets the index of the last red station.
	 *
	 * @return the index of the last red station.
	 */
	public int getRedLast() {
	    return redLast;
	}

	/**
	 * Sets the index of the last red station.
	 *
	 * @param redLast the index of the last red station to set.
	 */
	public void setRedLast(int redLast) {
	    this.redLast = redLast;
	}

	/**
	 * Gets the index of the last blue station.
	 *
	 * @return the index of the last blue station.
	 */
	public int getBlueLast() {
	    return blueLast;
	}

	/**
	 * Sets the index of the last blue station.
	 *
	 * @param blueLast the index of the last blue station to set.
	 */
	public void setBlueLast(int blueLast) {
	    this.blueLast = blueLast;
	}

	/**
	 * Gets the index of the last green station.
	 *
	 * @return the index of the last green station.
	 */
	public int getGreenLast() {
	    return greenLast;
	}

	/**
	 * Sets the index of the last green station.
	 *
	 * @param greenLast the index of the last green station to set.
	 */
	public void setGreenLast(int greenLast) {
	    this.greenLast = greenLast;
	}

	
}
