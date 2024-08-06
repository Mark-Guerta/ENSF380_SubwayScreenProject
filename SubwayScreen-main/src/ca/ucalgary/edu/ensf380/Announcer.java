
package ca.ucalgary.edu.ensf380;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * 	Plays audio files using the by the name of the station
 * 	@author Mark Guerta
 * 	@author Saif Youssef
 *  @version 1.0
 */

public class Announcer {
	private String station;
	/**
	 * Creates an empty Announcer
	 */
	public Announcer() {
		
	}
	/**
	 * Method for playing audio files
	 */
	public void playAnnouncer() {
			try {
				// Combines station name to create wav file
				station = station + ".wav";
				File audioPath = new File("StationAnnouncements", station);
				if (audioPath.exists()) {
					// Fetches and plays audio
					AudioInputStream audioInput = AudioSystem.getAudioInputStream(audioPath);
					Clip clip = AudioSystem.getClip();
					clip.open(audioInput);
					clip.start();
					// Allows audio to play for 5 seconds
					Thread.sleep(5000);
				}
				else {
					throw new IOException("File does not exist");
				}
			}
			catch (IOException ie) {
				ie.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	// Setters and getters
	/**
	 * Gets the station name.
	 *
	 * @return the station name.
	 */
	public String getStation() {
	    return station;
	}

	/**
	 * Sets the station name.
	 *
	 * @param station the station name to set.
	 */
	public void setStation(String station) {
	    this.station = station;
	}

}
