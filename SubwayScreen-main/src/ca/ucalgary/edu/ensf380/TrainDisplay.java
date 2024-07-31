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
	private final int redFirst = 1;
	private final int blueFirst = 45;
	private final int greenFirst = 89;
	private final int redLast = 44;
	private final int blueLast = 88;
	private final int greenLast = 120;
	public TrainDisplay(String station, ArrayList<String[]> stationArray) {
		
		Pattern pattern = Pattern.compile("(\\d\\d)");
		Matcher matcher =  pattern.matcher(station);
		this.stationArray = stationArray;
		matcher.find();
		this.currentStation = Integer.parseInt(matcher.group(1));
		this.stations = new JLabel[5];
		for (int i = 0; i < 5; i++) {
			stations[i] = new JLabel(stationArray.get(currentStation + i)[4],SwingConstants.CENTER);
		}
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
	public void activateTrainDisplay(JFrame frame) {
		// 1080x120 resolution

		String file = "stations720p.png";
		JLayeredPane nextStations = new JLayeredPane();
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel imgStations = new JLabel(new ImageIcon(myPicture));
		imgStations.setSize(imgStations.getPreferredSize());
		nextStations.add(imgStations, JLayeredPane.DEFAULT_LAYER);
		nextStations.setBounds(0, 0, 1080, 120);
		for (int i = 0; i < 5; i++)
			nextStations.add(stations[i], JLayeredPane.PALETTE_LAYER);
		frame.add(nextStations);
	}
	public void updateForward(String station) {
		Pattern pattern = Pattern.compile("(\\d\\d)");
		Matcher matcher =  pattern.matcher(station);
		matcher.find();
		this.currentStation = Integer.parseInt(matcher.group(1));
		boolean check = false;
		for (int i = 0; i < 5; i++) {
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
	public void updateBackward(String station) {
		Pattern pattern = Pattern.compile("(\\d\\d)");
		Matcher matcher =  pattern.matcher(station);
		matcher.find();
		this.currentStation = Integer.parseInt(matcher.group(1));
		boolean check = false;
		for (int i = 0; i < 5; i++) {
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



}
