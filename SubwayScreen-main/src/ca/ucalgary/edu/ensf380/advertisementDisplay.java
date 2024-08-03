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

public class advertisementDisplay extends Display {
	private JLabel[] trains;
	private String currentTrain;
	private String[] trainList;
	private ArrayList<String[]> stationArray;
	public advertisementDisplay(String[] trainList, String currentTrain) {
		super();
		String[] fileNames = {"TrainSymbolCurrentRed.png","TrainSymbolCurrentBlue.png","TrainSymbolCurrentGreen.png"
				,"TrainSymbolRed.png","TrainSymbolBlue.png","TrainSymbolGreen.png"};
		trains = new JLabel[12];
		display.setBounds(432,120,648,480);
		this.trainList = trainList;
		for (int i = 0; i < trainList.length; i++) {
			if(trainList[i].contains("R\\d\\d")) {
				trains[i].setIcon(imageResize( new File("trainSymbols",fileNames[3])));
				extractStationCoords(trainList[i], trains[i]);
			}
			else if(trainList[i].contains("B\\d\\d")) {
				trains[i].setIcon(imageResize( new File("trainSymbols",fileNames[4])));
				extractStationCoords(trainList[i], trains[i]);
			}
			else{
				trains[i].setIcon(imageResize( new File("trainSymbols",fileNames[5])));
				extractStationCoords(trainList[i], trains[i]);
			}
		}
		this.setCurrentTrain(currentTrain);
		for (int j = 0; j < trainList.length; j++) {
			if (trainList[j].contains(currentTrain)) {
				if(trainList[j].contains("R\\d\\d")) {
					trains[j].setIcon(imageResize( new File("trainSymbols",fileNames[0])));
					extractStationCoords(trainList[j], trains[j]);
					break;
				}
				else if(trainList[j].contains("B\\d\\d")) {
					trains[j].setIcon(imageResize( new File("trainSymbols",fileNames[1])));
					extractStationCoords(trainList[j], trains[j]);
					break;
				}
				else{
					trains[j].setIcon(imageResize( new File("trainSymbols",fileNames[2])));
					extractStationCoords(trainList[j], trains[j]);
					break;
				}
			}
		}
		for(JLabel train: trains) {
			display.add(train);
		}
	}

	@Override
	public void updateDisplay() {
		// TODO Auto-generated method stub
		for (int i = 0; i < trainList.length; i++) {
			if(trainList[i].contains("R\\d\\d")) {
				extractStationCoords(trainList[i], trains[i]);
			}
			else if(trainList[i].contains("B\\d\\d")) {
				extractStationCoords(trainList[i], trains[i]);
			}
			else{
				extractStationCoords(trainList[i], trains[i]);
			}
		}
		display.revalidate();
	}
	public ImageIcon imageResize(File imgFile) {
        BufferedImage bufferImage = null;
       	try {
       		bufferImage = ImageIO.read(new File("Map.png"));
     	} catch (IOException e) {
       		// TODO Auto-generated catch block
       		e.printStackTrace();
      	}
		ImageIcon icon = new ImageIcon(bufferImage);
        Image img = icon.getImage();
		Image imgScale = img.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
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
				trainLabel.setLocation((int)(648*(Double.parseDouble(stationArray.get(i)[5])/1750)), (int)(480*(Double.parseDouble(stationArray.get(i)[6])/1750)));
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
}
