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
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;

import javax.swing.JFrame;

/**
 * @author  Mahdi Ansari
 *
 */
public class MyApp1 {
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
        String train;
        Pattern pattern = Pattern.compile("(T1\\(R\\d\\d, .\\))");
		
		JFrame frame = new JFrame();

        TrainDisplay trainDisplay = null;
        try {
        	while (true) {
        		line = reader.readLine();
        		if (line != null) {
        		Matcher matcher = pattern.matcher(line);
        			if(matcher.find()) {
        				train = matcher.group(1);
        				if (trainDisplay == null) {
        					trainDisplay = new TrainDisplay(train, readCSV("map.csv"));
        					trainDisplay.activateTrainDisplay(frame);
        					frame.setLayout(null);
        					frame.setSize(1080, 720);
        					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        					frame.setVisible(true);
        					frame.setResizable(false);
        				}
    					trainDisplay.updateForward(line);
        			}
        		}
        	}

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public static ArrayList<String[]> readCSV(String fileName) {
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
}
