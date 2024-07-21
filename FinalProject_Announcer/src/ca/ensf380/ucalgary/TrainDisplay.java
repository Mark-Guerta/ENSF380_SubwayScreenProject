package ca.ensf380.ucalgary;

import java.util.ArrayList;
public final class TrainDisplay {
	private ArrayList<String[]> stations;
	private final static Announcer announcer = new Announcer();
	
	public TrainDisplay(ArrayList<String[]> stations) {
		this.stations = stations;
	}
	public TrainDisplay() {

	}
	public ArrayList<String[]> getStations() {
		return this.stations;
	}
	public void setStations(ArrayList<String[]> stations) {
		this.stations = stations;
	}
	public void playAnnouncer() {
		announcer.playAnnouncer();
	}
	public void TrainSim() {
		// Starts on blue line
		// Then red line
		// Finally green line
		boolean redLine = false;
		boolean blueLine = false;
		boolean greenLine = false;
		// 
		int currentPos = 1;
		final int redEndRow = 43;
		final int blueEndRow = 87;
		final int greenEndRow = 120;
		
		while (true) {
			// Resets train lines
			if (redLine && blueLine && greenLine) {
				redLine = false;
				blueLine = false;
				greenLine = false;
				break; // Use continue to keep the loop
			}
			// If red line ends, change to blue line start
			if (!redLine) {
				if (currentPos == blueEndRow)
					redLine = true;
			}
			// If blue line ends, change to green line start
			else if (!blueLine) {
				if (currentPos == redEndRow)
					blueLine = true;
			}
			// If green line ends, all lines reset to false
			else {
				if (currentPos == greenEndRow)
					greenLine = true;
			}
			// Plays "Next stop: ______ station"
			announcer.setstation(stations.get(currentPos)[4]);
			playAnnouncer();
			currentPos++;
		}
	}

}
