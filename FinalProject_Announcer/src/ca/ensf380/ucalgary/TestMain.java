package ca.ensf380.ucalgary;
/**
 * Referenced code:
 * @author BroCode (Youtube)
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TestMain {
	// Temporary holder for extracting map.csv
	public static void main(String[] args) {
		BufferedReader reader = null;
		String line = "";
		ArrayList<String[]> Stations = new ArrayList<String[]>();
		try {
			// fetches Map.csv
			reader = new BufferedReader(new FileReader("Map.csv"));
			// While loop adds and edits the csv for usage in String[] collections
			while ((line = reader.readLine()) != null) {
				String[] row = line.split(",");
				if (row.length > 8) {
					// removes quotes
					row[7] = row[7].replaceAll("\"", "");
					row[8] = row[8].replaceAll("\"", "");
				}
				// Removes leading whitespaces
				row[4] = row[4].trim();
				Stations.add(row);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		TrainDisplay Display = new TrainDisplay();
		Display.setStations(Stations);
		Display.TrainSim();
	}

}
