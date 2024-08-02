<h1>Announcer.java Documentation</h1>
<h2>Class Announcer</h2>
<p>
Play an audio sound file prompted by the TrainDisplay class. The string argument must be the station name without any leading or trailing whitespace.
This method will play an audio file the same name as the passed parameter. It utilizes FileIO and javaFX to process and play the audio file. The thread class is used to prevent the audio playback from terminating by putting the main thread to sleep. 
</p>
<p>
A method that fetches and plays the audio file.
@param station      The full name of the station: "Rosewood Station"
public void playAnnouncer(String station)
</p>