
# Fire Alarm Sensor System
## How to Install
1. Clone or Download the Project files
2. Check whether Node and NPM are installed. To check if they are installed, run the below command in the Command Prompt
```
node -- version
npm --version
```
3. If not Installed Install them.
4. Install a Java IDE
5. Install MongoDB
6. Run the following Commands in the Command Prompt. The path is the main directory of the downloaded file directory.
```
cd REST Api Server
npm install
cd ..
cd Website
npm install
```

### How to Configure
1. Go to REST Api Server/Config.
2. Open the Config.js file using a Text editor.
3. Add Gmail Credentials.
4. Create a TextIt Or Nexmo Account to test the SMS Service. The links are provided in the config file.
5. Don't change the port. If it is necessary to change you must also do the other instructions provided in the Config File. 
6. Go Back to the Main Directory
7. Go to Website.
8. Open the .env file using a text editor
9. If the Specified PORT (3001) is busy change the PORT Number
10. Go Back to the Main Directory
11. Open the Desktop Application Directory using the Java IDE
12. Check if the PORT 300 is busy in the system
```
netstat -na | find "3000"
```
If this command returns nothing the port is empty.
13. If Port 3000 is busy change the following files
```
Fire Alarm RMI Server / src / fireAlarmRMIServerMain / RMIServerMain.java - Chnage the PORT number to an empty port
Fire Alarm RMI Client / src / desktopClientGUI / FireAlarmClientMain.java - Change the PORT number to the same port as above
```
### How to run the System
1. Open 2 command prompts in the main directiory.
2. Run the following commands
```
cd REST Api Server
npm start
```
```
cd Website
npm start
```
3. Run the RMI Server
Run the Following java file using the Java IDE
```
Desktop Application / Fire Alarm RMI Server / src / fireAlarmRMIServerMain / RMIServerMain.java
```
4. Run the Desktop GUI
Run the Following java file using the Java IDE
```
Desktop Application / Fire Alarm RMI Client / src / desktopClientGUI / FireAlarmClientMain.java
```
5. Run Fire Alarm Sensor Dummy Apps
Run the Following Java file using the Java IDE. You can run multiple instances of this app.
```
Desktop Application / Fire Alarm Sensor / src / fireAlarmGUI/ FireAlarmMain.java

```
