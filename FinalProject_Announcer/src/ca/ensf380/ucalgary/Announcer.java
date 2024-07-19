/**
 * 	Announcer.java
 * 	Referenced Code:
 * 	@author maxod (Youtube)
 * 
 */

package ca.ensf380.ucalgary;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Announcer {
	private String station;
	// Creates the name for the audio file
	public Announcer(String destination) {
		this.station = destination + ".wav";
	}
	public void playAnnouncer() {
			try {
				File audioPath = new File("StationAnnouncements", station);
				if (audioPath.exists()) {
					// Uses the audio file within the given path
					AudioInputStream audioInput = AudioSystem.getAudioInputStream(audioPath);
					// Gets the audio file from audioInput
					Clip clip = AudioSystem.getClip();
					clip.open(audioInput);
					clip.start();
					// Causes the current thread to sleep for a specific amount of time.
					// Basically, the program stops running while music is played until 
					// the timer runs out in milliseconds.
					Thread.sleep(11000);
				}
				else {
					System.out.println("Error");
				}
			}
			catch (Exception e) {
				System.out.println(e);
			}
	}
	public String getstation() {
		return station;
	}
	public void setstation(String destination) {
		this.station = destination + ".wav";
	}
}
