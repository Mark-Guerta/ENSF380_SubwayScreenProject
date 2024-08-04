# ENSF380_SubwayScreenProject
## Database Initialization
<p> 
  The database used for this program is mySQL 8.0.38. 
  <ol>
    <li>
       Install mySQL using the mySQL Installer. Link: https://dev.mysql.com/downloads/windows/installer/8.0.html
    </li>
    <li>
      Within the png.sql file. Please copy the absolute file path of each photo in SubwayScreen-main\ads and paste into the VALUE field of the sql script.
    </li>
    <li>
      Additionally, ensure that every single slash is doubled to ensure that the the program can find the files.
    </li>
    <li>
         Afterwards, run the png.sql script (found in SubwayScreen-main) in mySQL workbench and be sure to change the password in the MySQL.java file in src\ca\ucalgary\edu\ensf380. Look for: DriverManager.getConnection("jdbc:mysql://localhost:3306/png", "root", "changePassword")
    </li>
  </ol>
</p>

## Program Setup

### Create a Java project in Eclipse

<p>
  Create a new Java project in Eclipse then copy and paste the contents of SubwayScreen-main into the root folder.
</p>

### External Jar Files

<p>
  Right click on the project folder then hover over the build path and select Add External Archives. Then, add every jar file in SubwayScreen-main\lib.
</p>

### Command Line Arguments

<p>
  There are three paramaters you can set in this program. The first parameter is the train you wish to track. There are a total of 12 trains denoted by T1 - T12. This is a mandatory argument as without it the program will throw an InvalidArgumentException.
  The second paratemer is the city name. This is used to fetch the weather. This parameter is also mandatory. Finally, the third parameter is the news key word. Choose a topic to display or leave empty for random news.
  <br> Here are two examples:
  <br> T1 Calgary
  <br> T7 Ottawa Science
</p>

### JUnit Testing
<p>
  Download JUnit 5 on Eclipse using the intellisense tool. To run the JUnit tests, go to src\ca\ucalgary\edu\ensf380 and find the class files with test and then run each one independently.
</p>
