package ca.ucalgary.edu.ensf380;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * 	Displays advertisements and the train map by passing train map and all trains
 * 	@author Mark Guerta
 * 	@author Saif Youssef
 *  @version 1.0
 */
public final class AdvertisementDisplay extends Display {
	private JLayeredPane map;
	private JLabel adLabel;
	private JLabel[] trains;
	private String currentTrain;
	private String[] trainList;
	private ArrayList<String[]> stationArray;
	private ArrayList<JLabel> stationLabel;
	private ImageIcon[] adPhoto;
	private int adPosition;
	/**
	 *  Displays advertisements and train map
	 * @param trainList Contains all trains
	 * @param currentTrain The train currently being tracked
	 * @param stationArray	Contains all station details
	 */
	public AdvertisementDisplay(String[] trainList, String currentTrain, ArrayList<String[]>stationArray) {
		super();
		map = new JLayeredPane();
		stationLabel = new ArrayList<JLabel>();
		trains = new JLabel[12];
		adLabel = new JLabel();
		this.stationArray = stationArray;
		adPhoto = MySQL.getAds();
		adPosition = 0;
		String[] fileName = {"TrainSymbolCurrentRed.png","TrainSymbolCurrentBlue.png","TrainSymbolCurrentGreen.png"
				,"TrainSymbolRed.png","TrainSymbolBlue.png","TrainSymbolGreen.png"};
		
		// Adds all station to the JLayeredPane map
		for(int station = 1; station < stationArray.size();station++) {
			stationLabel.add(new JLabel(""));
			stationLabel.get(station - 1).setLocation((int) (648*(Double.parseDouble(stationArray.get(station)[5])/1600)), (int) (480*(Double.parseDouble(stationArray.get(station)[6])/1600)));
			stationLabel.get(station - 1).setIcon(resizeImage(new File("trainSymbols", "StationSymbol.png"), 5, 5));
			stationLabel.get(station - 1).setSize(10,10);
			map.add(stationLabel.get(station - 1), Integer.valueOf(0));
		}
		
		// Gives trains an imageIcon on the map and sets their position
		this.trainList = trainList;
		for (int index1 = 0; index1 < trainList.length; index1++) {
			if(trainList[index1].contains("R")) {
				trains[index1] = new JLabel();
				trains[index1].setIcon(resizeImage( new File("trainSymbols",fileName[3]), 10, 10));
				extractStationCoords(trainList[index1], trains[index1]);
			}
			else if (trainList[index1].contains("G")){
				trains[index1] = new JLabel();
				trains[index1].setIcon(resizeImage( new File("trainSymbols",fileName[5]), 10, 10));
				extractStationCoords(trainList[index1], trains[index1]);
			}
			else if(trainList[index1].contains("B")) {
				trains[index1] = new JLabel();
				trains[index1].setIcon(resizeImage( new File("trainSymbols",fileName[4]), 10, 10));
				extractStationCoords(trainList[index1], trains[index1]);
			}
			trains[index1].setSize(10,10);
		}
		
		// Changes one of the trains to the current train on the map pane and sets their position
		this.currentTrain = currentTrain;
		for (int index2 = 0; index2 < trainList.length; index2++) {
				if(trainList[index2].contains(currentTrain) && trainList[index2].contains("R")) {
					trains[index2].setIcon(resizeImage( new File("trainSymbols",fileName[0]), 10, 10));
					extractStationCoords(trainList[index2], trains[index2]);
					break;
				}
				else if (trainList[index2].contains(currentTrain) && trainList[index2].contains("G")){
					trains[index2].setIcon(resizeImage( new File("trainSymbols",fileName[2]), 10, 10));
					extractStationCoords(trainList[index2], trains[index2]);
					break;
				}
				else if(trainList[index2].contains(currentTrain) && trainList[index2].contains("B")) {
					trains[index2].setIcon(resizeImage( new File("trainSymbols",fileName[1]), 10, 10));
					extractStationCoords(trainList[index2], trains[index2]);
					break;
				}
		}
		// Adds trains to the map
		for(JLabel train: trains) {
			map.add(train, Integer.valueOf(1));
		}
		// Adds all visual elements to the display
		adLabel.setSize(648, 480);
		map.setLocation( 100, 125);
		map.setSize(648,480);
		display.setOpaque(true);
		display.setBounds(432,120,648,480);
		display.setBackground(Color.white);
		display.add(adLabel, Integer.valueOf(1));
		display.add(map, Integer.valueOf(0));
		adLabel.setIcon(adPhoto[adPosition++]);
		// Displays an ad for 10 seconds then display map for 5 seconds then displays another ad after.
		adTimer();
	}

	/**
	 * Updates the display contents and train positions
	 */
	@Override
	public void updateDisplay() {
		adTimer();
		for (int i = 0; i < trainList.length; i++) {
			if(trainList[i].contains("R")) {
				extractStationCoords(trainList[i], trains[i]);
			}
			else if (trainList[i].contains("G")){
				extractStationCoords(trainList[i], trains[i]);
			}
			else if(trainList[i].contains("B")) {
				extractStationCoords(trainList[i], trains[i]);
			}
		}
		display.revalidate();
	}
	
	/**
	 * Resizes the image based on desired dimensions
	 * @param imgFile Image path
	 * @param x Dimension width
	 * @param y Dimension height
	 * @return ImageIcon To be added to a JLabel
	 */
	public ImageIcon resizeImage(File imgFile, int x, int y) {
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
	/**
	 * Gets the train station coordinates using station code
	 * @param train Train to get coordinates from
	 * @param trainLabel JLabel to update position
	 */
	public void extractStationCoords(String train, JLabel trainLabel) {
		Pattern patternStationCode = Pattern.compile("([RGB]\\d\\d)");
		Matcher matcher = patternStationCode.matcher(train);
		String stationCode = null;
		try {
			if (matcher.find())
				stationCode = matcher.group(1);
			else
				throw new Exception("Station code not found");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		for (int i = 0; i < stationArray.size(); i++) {
			if (stationArray.get(i)[3].equals(stationCode)) {
				trainLabel.setLocation((int)(648*(Double.parseDouble(stationArray.get(i)[5])/1600)), (int)(480*(Double.parseDouble(stationArray.get(i)[6])/1600)));
				break;
			}
		}
	}
	/**
	 * Displays ad for 10 seconds then displays train map for 5 seconds. Afterwards, an ad is displayed again.
	 */
	public void adTimer() {
		Timer timer5 = new Timer();
		TimerTask task5 = new TimerTask() {
			@Override
			public void run() {
				if (adPosition < adPhoto.length)
					adLabel.setIcon(adPhoto[adPosition++]);
				else {
					adPosition = 0;
					adLabel.setIcon(adPhoto[adPosition++]);
				}
				adLabel.setVisible(true);
			}
		};
		
		Timer timer10 = new Timer();
		TimerTask task10 = new TimerTask() {
			@Override
			public void run() {
				adLabel.setVisible(false);
				timer5.schedule(task5, 5000);
			}
		};
		timer10.schedule(task10, 10000);
	}
	// Setters and Getters

	/**
	 * Gets the map layered pane.
	 *
	 * @return the map layered pane.
	 */
	public JLayeredPane getMap() {
	    return map;
	}

	/**
	 * Sets the map layered pane.
	 *
	 * @param map the map layered pane to set.
	 */
	public void setMap(JLayeredPane map) {
	    this.map = map;
	}

	/**
	 * Gets the advertisement label.
	 *
	 * @return the advertisement label.
	 */
	public JLabel getAdLabel() {
	    return adLabel;
	}

	/**
	 * Sets the advertisement label.
	 *
	 * @param adLabel the advertisement label to set.
	 */
	public void setAdLabel(JLabel adLabel) {
	    this.adLabel = adLabel;
	}

	/**
	 * Gets the array of train labels.
	 *
	 * @return the array of train labels.
	 */
	public JLabel[] getTrains() {
	    return trains;
	}

	/**
	 * Sets the array of train labels.
	 *
	 * @param trains the array of train labels to set.
	 */
	public void setTrains(JLabel[] trains) {
	    this.trains = trains;
	}

	/**
	 * Gets the current train identifier.
	 *
	 * @return the current train identifier.
	 */
	public String getCurrentTrain() {
	    return currentTrain;
	}

	/**
	 * Sets the current train identifier.
	 *
	 * @param currentTrain the current train identifier to set.
	 */
	public void setCurrentTrain(String currentTrain) {
	    this.currentTrain = currentTrain;
	}

	/**
	 * Gets the list of train identifiers.
	 *
	 * @return the list of train identifiers.
	 */
	public String[] getTrainList() {
	    return trainList;
	}

	/**
	 * Sets the list of train identifiers.
	 *
	 * @param trainList the list of train identifiers to set.
	 */
	public void setTrainList(String[] trainList) {
	    this.trainList = trainList;
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
	 * Gets the list of station labels.
	 *
	 * @return the list of station labels.
	 */
	public ArrayList<JLabel> getStationLabel() {
	    return stationLabel;
	}

	/**
	 * Sets the list of station labels.
	 *
	 * @param stationLabel the list of station labels to set.
	 */
	public void setStationLabel(ArrayList<JLabel> stationLabel) {
	    this.stationLabel = stationLabel;
	}

	/**
	 * Gets the array of advertisement photos.
	 *
	 * @return the array of advertisement photos.
	 */
	public ImageIcon[] getAdPhoto() {
	    return adPhoto;
	}

	/**
	 * Sets the array of advertisement photos.
	 *
	 * @param adPhoto the array of advertisement photos to set.
	 */
	public void setAdPhoto(ImageIcon[] adPhoto) {
	    this.adPhoto = adPhoto;
	}

	/**
	 * Gets the position of the advertisement.
	 *
	 * @return the position of the advertisement.
	 */
	public int getAdPosition() {
	    return adPosition;
	}

	/**
	 * Sets the position of the advertisement.
	 *
	 * @param adPosition the position of the advertisement to set.
	 */
	public void setAdPosition(int adPosition) {
	    this.adPosition = adPosition;
	}

	
}
