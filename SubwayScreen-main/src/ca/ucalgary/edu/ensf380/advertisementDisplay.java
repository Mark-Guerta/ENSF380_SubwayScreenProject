package ca.ucalgary.edu.ensf380;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class advertisementDisplay extends Display {
	private JLabel[] trains;
	private String currentTrain;
	private String[] trainList;
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
			}
			else if(trainList[i].contains("B\\d\\d")) {
				trains[i].setIcon(imageResize( new File("trainSymbols",fileNames[4])));
			}
			else{
				trains[i].setIcon(imageResize( new File("trainSymbols",fileNames[5])));
			}
		}
		this.currentTrain = currentTrain;
		for (int j = 0; j < trainList.length; j++) {
			if (trainList[j].contains(currentTrain)) {
				if(trainList[j].contains("R\\d\\d")) {
					trains[j].setIcon(imageResize( new File("trainSymbols",fileNames[0])));
					break;
				}
				else if(trainList[j].contains("B\\d\\d")) {
					trains[j].setIcon(imageResize( new File("trainSymbols",fileNames[1])));
					break;
				}
				else{
					trains[j].setIcon(imageResize( new File("trainSymbols",fileNames[2])));
					break;
				}
			}
		}
	}

	@Override
	public void updateDisplay() {
		// TODO Auto-generated method stub
		
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
}
