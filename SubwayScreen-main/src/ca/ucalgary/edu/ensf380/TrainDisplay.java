package ca.ucalgary.edu.ensf380;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.*;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
public class TrainDisplay {
	private int currentStation;
	private String Line;
	private String direction;
	private ArrayList<String[]> stationArray;
	private JLabel[] stations;
	private int redFirst = -1;
	private int blueFirst = -1;
	private int greenFirst = -1;
	private int redLast = -1;
	private int blueLast = -1;
	private int greenLast = -1;
	public TrainDisplay(String train, ArrayList<String[]> stationArray) {
		// Constructor initializes values based on map.csv
		this.stations = new JLabel[5];
		this.stationArray = stationArray;
		// Extracts station from train
		stationMatcher(train);
		try {
			// Sets train line colour
			if (train.contains("R"))
				Line = "R";
			else if (train.contains("B"))
				Line = "B";
			else if (train.contains("G"))
				Line = "G";
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
		stations[0].setBounds(-25, 0, 200, 180);
		stations[1].setBounds(210, 0, 200, 180);
		stations[2].setBounds(435, 0, 200, 180);
		stations[3].setBounds(670, 0, 200, 180);
		stations[4].setBounds(900, 0, 200, 180);
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

	// Formats train display
	public void formatTrainDisplay(JFrame frame) {
		// 1080x120 dimensions
		String file = "stations720p.png";
		JLayeredPane TrainPane = new JLayeredPane();
		BufferedImage myPicture = null;

		try {
			myPicture = ImageIO.read(new File(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Adds images and JLabels to JLayeredPane then to frame
		JLabel imgStations = new JLabel(new ImageIcon(myPicture));
		imgStations.setSize(imgStations.getPreferredSize());
		TrainPane.add(imgStations, JLayeredPane.DEFAULT_LAYER);
		TrainPane.setBounds(0, 0, 1080, 120);
		for (int i = 0; i < 5; i++)
			TrainPane.add(stations[i], JLayeredPane.PALETTE_LAYER);
		frame.add(TrainPane);
	}
	// Updates train stations on screen
	public void updateForward(String train) {
		stationMatcher(train);
		boolean check = false;
		for (int i = 1; i < 5; i++) {
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
		Announcer.playAnnouncer(stationArray.get(currentStation + 1)[4]);
	}
	// Updates train stations on screen
	public void updateBackward(String train) {
		stationMatcher(train);
		boolean check = false;
		for (int i = 1; i < 5; i++) {
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
				stations[i].setText(stationArray.get(currentStation - i)[4]);
				stations[i].revalidate();
			}
		}
		Announcer.playAnnouncer(stationArray.get(currentStation + 1)[4]);
	}
	// Sorts extracting station number from train
	public void stationMatcher(String train) {
		Pattern pattern = Pattern.compile("(\\d\\d)");
		Matcher matcher =  pattern.matcher(train);
		try {
			if (matcher.find())
				this.currentStation = Integer.parseInt(matcher.group(1));
			else
				throw new Exception("Matcher failed to find the station");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}


}
