/**
 * 	Announcer.java
 * 	Referenced Code:
 * 	@author maxod (Youtube)
 * 
 */

package ca.ucalgary.edu.ensf380;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Announcer {

	public Announcer() {
	}
	public static void playAnnouncer(String station) {
			try {
				station = station + ".wav";
				File audioPath = new File("StationAnnouncements", station);
				if (audioPath.exists()) {
					// Uses the audio file within the given path
					AudioInputStream audioInput = AudioSystem.getAudioInputStream(audioPath);
					// Gets the audio file from audioInput
					Clip clip = AudioSystem.getClip();
					clip.open(audioInput);
					clip.start();
					// A daemon thread is made. ()
					// A thread in Java is the direction 
					// or path that is taken while a program is being executed.
					// Basically, the program stops running while music is played until 
					// the timer runs out in milliseconds.
					Thread.sleep(5000);
				}
				else {
					System.out.println("Error: File does not exist");
				}
			}
			catch (Exception e) {
				System.out.println(e);
			}
	}
}
