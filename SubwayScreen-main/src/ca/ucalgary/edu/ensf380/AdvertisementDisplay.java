package ca.ucalgary.edu.ensf380;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.*;

public class AdvertisementDisplay extends Display {
	private JLabel[] trains;
	private String currentTrain;
	private String[] trainList;
	private ArrayList<String[]> stationArray;
	private ArrayList<JLabel> stationLabel;
	private JLabel[] advertisement;
	private JLayeredPane map;
	public AdvertisementDisplay(String[] trainList, String currentTrain) {
		super();
		map = new JLayeredPane();
		stationLabel = new ArrayList<JLabel>();
		stationArray = SubwayScreen.stations;
		setAdvertisement(mySQL.getAds());
		String[] fileNames = {"TrainSymbolCurrentRed.png","TrainSymbolCurrentBlue.png","TrainSymbolCurrentGreen.png"
				,"TrainSymbolRed.png","TrainSymbolBlue.png","TrainSymbolGreen.png"};
		trains = new JLabel[12];
		for(int station = 1; station < stationArray.size();station++) {
			stationLabel.add(new JLabel(""));
			stationLabel.get(station - 1).setLocation((int) (648*(Double.parseDouble(stationArray.get(station)[5])/1600)), (int) (480*(Double.parseDouble(stationArray.get(station)[6])/1600)));
			stationLabel.get(station - 1).setIcon(resizeImage(new File("trainSymbols", "StationSymbol.png"), 5, 5));
			stationLabel.get(station - 1).setSize(10,10);
			map.add(stationLabel.get(station - 1), Integer.valueOf(0));
		}
		this.trainList = trainList;
		for (int index1 = 0; index1 < trainList.length; index1++) {
			if(trainList[index1].contains("R")) {
				trains[index1] = new JLabel();
				trains[index1].setIcon(resizeImage( new File("trainSymbols",fileNames[3]), 10, 10));
				extractStationCoords(trainList[index1], trains[index1]);
			}
			else if (trainList[index1].contains("G")){
				trains[index1] = new JLabel();
				trains[index1].setIcon(resizeImage( new File("trainSymbols",fileNames[5]), 10, 10));
				extractStationCoords(trainList[index1], trains[index1]);
			}
			else if(trainList[index1].contains("B")) {
				trains[index1] = new JLabel();
				trains[index1].setIcon(resizeImage( new File("trainSymbols",fileNames[4]), 10, 10));
				extractStationCoords(trainList[index1], trains[index1]);
			}
			trains[index1].setSize(10,10);
		}
		
		this.currentTrain = currentTrain;
		for (int index2 = 0; index2 < trainList.length; index2++) {
				if(trainList[index2].contains(currentTrain) && trainList[index2].contains("R")) {
					trains[index2].setIcon(resizeImage( new File("trainSymbols",fileNames[0]), 10, 10));
					extractStationCoords(trainList[index2], trains[index2]);
					break;
				}
				else if (trainList[index2].contains(currentTrain) && trainList[index2].contains("G")){
					trains[index2].setIcon(resizeImage( new File("trainSymbols",fileNames[2]), 10, 10));
					extractStationCoords(trainList[index2], trains[index2]);
					break;
				}
				else if(trainList[index2].contains(currentTrain) && trainList[index2].contains("B")) {
					trains[index2].setIcon(resizeImage( new File("trainSymbols",fileNames[1]), 10, 10));
					extractStationCoords(trainList[index2], trains[index2]);
					break;
				}
		}
		for(JLabel train: trains) {
			map.add(train, Integer.valueOf(1));
		}
		map.setLocation( 100, 125);
		map.setSize(648,480);
		display.setOpaque(true);
		display.setBounds(432,120,648,480);
		display.setBackground(Color.white);
		display.add(map);
	}

	@Override
	public void updateDisplay() {
		// TODO Auto-generated method stub
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
	public JLabel[] getTrains() {
		return trains;
	}

	public void setTrains(JLabel[] trains) {
		this.trains = trains;
	}

	public String[] getTrainList() {
		return trainList;
	}

	public void setTrainList(String[] trainList) {
		this.trainList = trainList;
	}

	public String getCurrentTrain() {
		return currentTrain;
	}

	public void setCurrentTrain(String currentTrain) {
		this.currentTrain = currentTrain;
	}

	public JLabel[] getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(JLabel[] advertisement) {
		this.advertisement = advertisement;
	}
}
