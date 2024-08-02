
package ca.ucalgary.edu.ensf380;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * 	Announcer.java
 * 	
 * 	@author Mark Guerta
 * 	@author Saif Youssef
 *  @version 0.9
 */

public class Announcer {
	/**
	 * Announcer constructor is unused
	 */
	private Announcer() {
		
	}
	/**
	 * Static method for playing audio files
	 * @param station The station's name
	 */
	public static final void playAnnouncer(String station) {
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
}
