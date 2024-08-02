<h1>TrainDisplay.java Documentation</h1>
<h2>Class TrainDisplay</h2>
<p>
Formats and displays the current station and next stations onto the screen.
</p>
<p>
A constructor that initializes and prepares current station and next stations to be displayed on screen.
@param train      The train being current tracked
@param stationArray     Contains existing train stations with their identifiers
public TrainDisplay(String train, ArrayList<String[]> stationArray)
</p>
<p>
A method that adds the JLayeredPane with all the train station contents to the frame.
@param frame    The window where the content will be displayed
public void formatTrainDisplay(JFrame frame)
</p>
<p>
Updates the current station and next stations on the display if the direction is forward.
@param train    Contains information about the status of the train
public updateForward(String train)
</p>
<p>
Updates the current station and next stations on the display if the direction is backwards.
@param train    Contains information about the status of the train
public updateBackward(String train)
</p>
<p>
Extracts the station number from the train using regex.
@param train    Contains information about the status of the train
private void stationMatcher(String train)
</p>