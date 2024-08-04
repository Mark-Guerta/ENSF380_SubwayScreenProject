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
	public Announcer() {
		
	}
	/**
	 * Static method for playing audio files
	 * @param station The station's name
	 * @throws IOException 
	 */
	public static final void playAnnouncer(String station) throws IOException {
			try {
				// Combines station name to create wav file
				station = station + ".wav";
				File audioPath = new File("StationAnnouncements", station);
				if (audioPath.exists()) {
					
				  try {
					  AudioInputStream audioInput = AudioSystem.getAudioInputStream(audioPath);
						Clip clip = AudioSystem.getClip();
						clip.open(audioInput);
						clip.start();
						// Allows audio to play for 5 seconds
						Thread.sleep(5000);
				  }catch (Exception e) {
						e.printStackTrace();
					}
					
				}else {
					throw new IOException("File does not exist");
				}
				
		
		}finally {
			
		}
	}		
}