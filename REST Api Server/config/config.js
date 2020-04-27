module.exports = {
    //Running of the System
    //If the port number is changed, there are many places to change the number in other applications
    //beacuse of that of port 5000 is in use, stop the other app using the port and use the port 5000
    //to start this app
    //if any case the port number is changed,
    //the user must change the HTTP Request URIs in following places
    //Website/src/Components/firealarm/fireAlarmMain.jsx - In axios.get() - change the 500 to the changed port
    //Desktop Application/Fire Alarm Sensor/src/fireAlarmSensor/FireAlarmImpl.java - find and replace all port 5000 to changed port
    //Desktop Application/Fire Alarm RMI Sensor/src/FireAlarmSensorImpl.java - find and replace all port 5000 to changed port
    //Desktop Application/User RMI Sensor/src/UserImpl.java - find and replace all port 5000 to changed port
    PORT : 5000,

    //mongoDB uri in the current system
    //the default port is 27017
    //if the system is changed, Change this in order to run the database
    mongodb: "mongodb://localhost:27017/fireAlarmSystem",

    //secret key to generate JSON Web Tokens
    secret: "firealertusertokensecret",

    //Gmail Credentials
    //First turn on Allow less secure apps in GMAIL account using the below link
    //https://myaccount.google.com/u/0/lesssecureapps
    userName: "gppagroup2@gmail.com",
    password: "ariyathilake",


    //Nexmo Credentials
    //Used to send SMS
    //First create an account in nexmo using the below link
    //https://dashboard.nexmo.com/sign-in
    nexmoApiKey: '26164d18',
    nexmoApiSecret: '8SnXdkqrR51J3kuS',

    //textit credentials
    //signup using the below link
    //http://textit.biz/signup_1.php
    textitID : '94712917257',
    textitPassword : '7410'
}