package ca.ensf380.ucalgary;
// 
public class TestAnnouncer {
	public static void main(String[] args) {
		String song = "Unicorn";
		Announcer station = new Announcer(song);
		station.playAnnouncer();
		System.out.println("Done");
	}
}