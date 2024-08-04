/**
 * Copyright (c) 2022-2023 Mahdi Jaberzadeh Ansari and others.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *	
 *	The above copyright notice and this permission notice shall be
 *	included in all copies or substantial portions of the Software.
 *	
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *	EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *	MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *	NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 *	LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 *	OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 *	WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package ca.ucalgary.edu.ensf380;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import javax.swing.JFrame;

/**
 * SubwayScreen.java
 * Simulates and displays subway screen.
 * @author  Mahdi Ansari
 * @author Mark Guerta
 * @author Saif Youssef
 * @version 1.0
 */
public class SubwayScreen {

	/**
	 * Main method that displays a subway screen
	 * @param args Command Line Arguments
	 */
	public static void main(String[] args) {
        // Runs the simulator 
		Process process = null;        
        try {
        	String[] command = {"java", "-jar", "./exe/SubwaySimulator.jar", "--in", "./data/subway.csv", "--out", "./out"};
        	process = new ProcessBuilder(command).start();
        } catch (IOException e) {
        	System.err.println(e);
            e.printStackTrace();
        }
        final Process finalProcess = process;

        // It will destroy the simulator process at the end 
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (finalProcess != null) {
                finalProcess.destroy();
            }
        }));
        
        // Keep the application alive for 5 minutes 
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (finalProcess != null) {
                    finalProcess.destroy();
                }
                timer.cancel();
            }
        }, 5 * 60 * 1000); // 5 minutes in milliseconds

        // Prints simulator in the console. 
        // Just for test. Its while loop friezes the application.  
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String train = null;
        Pattern pattern = null;
        try {
        	// Checks if args[0] contains a train number
            if (args[0].matches("T[1-9]") || args[0].matches("T1[0-2]"))
        		 pattern = Pattern.compile("("+ args[0] +"\\([RGB]\\d\\d, [FB]\\))");
            else
            	throw new IllegalArgumentException("Invalid argument for train");
        }
        catch(IllegalArgumentException IA) {
        	IA.printStackTrace();
        	System.exit(1);
        }
        catch (Exception e) {
        	e.printStackTrace();
        	System.exit(1);
        } 
        
        ArrayList<String[]> stationArray;
        String[] trainLine = new String[4];
        String [] trainList;
        TrainDisplay trainDisplay = null;
        WeatherDisplay weatherDisplay = null;
        AdvertisementDisplay advertisementDisplay = null;
		NewsDisplay newsDisplay = null;
		Announcer announcer = new Announcer();
		stationArray = readMapCSV("map.csv");
		
		// Creates new window
        JFrame frame = new JFrame();
        try {
        	while (true) {
        		line = reader.readLine();
        		if (line != null) {
        			System.out.println(line);
        			for (int i = 0;i < 4; i++)
        				if (trainLine[i] == null) {
        					trainLine[i] = line;
        					break;
        				}
        		}
        		// Ensures that train line is not empty before processing
        		if (trainLine[3] == null)
        			continue;
        		trainList = extractTrains(trainLine);
    			// Formats and adds displays to the frame
    			if (trainDisplay == null) {
    				for (int i = 0; i < 12; i++) {
        				Matcher matcher = pattern.matcher(trainList[i]);
            			if(matcher.find()) {
            				train = matcher.group(1);
            			}
    				}
    				trainDisplay = new TrainDisplay(train, stationArray, announcer);
   					weatherDisplay = new WeatherDisplay();
   					newsDisplay = new NewsDisplay(args);
   					advertisementDisplay = new AdvertisementDisplay(trainList,train, stationArray);
   					// Checks if constructor failed
    				if (trainDisplay == null || newsDisplay == null || weatherDisplay == null || advertisementDisplay == null)
    					throw new Exception("Failed display construction");
    				// Adds displays to main screen and formats main screen
    				frame.add(trainDisplay.getDisplay());
   					frame.add(newsDisplay.getDisplay());
    				frame.add(weatherDisplay.getDisplay());
    				frame.add(advertisementDisplay.getDisplay());
    				frame.setLayout(null);
    				frame.setSize(1095, 720);
    				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    				frame.setVisible(true);
   					frame.setResizable(false);
   					// Activates announcer for the first time
   					if (trainDisplay.getDirection().equals("F")) {
   						announcer.setStation(stationArray.get(trainDisplay.getCurrentStation() + 1)[4]);
   						announcer.playAnnouncer();
   					}

   					else {
   						announcer.setStation(stationArray.get(trainDisplay.getCurrentStation() - 1)[4]);
   						announcer.playAnnouncer();
   					}

   	    			Arrays.fill(trainLine,null);
   	    			Arrays.fill(trainList,null);
   	    			continue;
    				}
    			// Updates display contents
    			// Only activates when the trainList is full initialized
    			if (trainList[11] != null) {
        			newsDisplay.updateDisplay();
        			weatherDisplay.updateDisplay();
        			advertisementDisplay.setTrainList(trainList);
        			advertisementDisplay.updateDisplay();
        			if (train.contains("F"))
        				trainDisplay.setDirection("F");
    				else
    					trainDisplay.setDirection("B");
        			trainDisplay.updateDisplay();
    	    		Arrays.fill(trainLine,null);
    	    		Arrays.fill(trainList,null);
    			}
        	}
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        catch (Exception e) {
        	e.printStackTrace();
        	System.exit(1);
        }
    }
	
	/**
	 * Extracts station data from map.csv
	 * @param fileName File path for csv file
	 * @return stations	Collection of station information
	 */
	public static ArrayList<String[]> readMapCSV(String fileName) {
		BufferedReader reader = null;
		ArrayList<String[]> stations = new ArrayList<String[]>();;
		String line;

		try {
			// fetches csv.file
			reader = new BufferedReader(new FileReader(fileName));
			// While loop adds and edits the CSV for usage in String[] arrayList
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
			return stations;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
			}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Gets the train from the substring ex: T1(R01, F)
	 * @param trainLine Contains the 3 trainLines
	 * @return train Contains all 12 trains
	 */
	public static String[] extractTrains(String[] trainLine) {
		Pattern trainPattern = Pattern.compile("T\\d+?\\([RGB]\\d\\d, [FB]\\)");

		String[] train = new String[12];
		for (int i = 1; i < 4; i++) {
			Matcher matcher = trainPattern.matcher(trainLine[i]);
			for (int j = 0; j < 4; j++) {
				matcher.find();
				if (i == 1) {
					train[j] = matcher.group();
				}
				else if (i == 2) {
					train[j + 4] = matcher.group();
				}
				else if (i == 3) {
					train[j + 8] = matcher.group();
				}
			}
		}
		return train;
	}
}
