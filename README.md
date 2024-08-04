# ENSF380_SubwayScreenProject
## Database Initialization
<p> 
  The database used for this program is mySQL 8.0.38. Installer: https://dev.mysql.com/downloads/workbench/.
  Within the png.sql file. Please copy the absolute file path of each photo in SubwayScreen-main\ads and paste into the VALUE field of the sql script.
  Additionally, ensure that every single slash is doubled to ensure that the the program can find the files. Afterwards, run the png.sql script (found in SubwayScreen-main) in mySQL workbench and be sure to change
  the password in the MySQL.java file in src\ca\ucalgary\edu\ensf380. 
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
  The second paratemer is the keyword for the news
</p>
