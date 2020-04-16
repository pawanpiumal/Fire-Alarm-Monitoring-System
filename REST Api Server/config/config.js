module.exports = {
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