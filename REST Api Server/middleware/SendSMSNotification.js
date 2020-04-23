//Importing Nexmo to send sms
const Nexmo = require('nexmo');

//Importing Api Key and Secret key from config
const apiKey = require('../config/config').nexmoApiKey;
const apiSecret = require('../config/config').nexmoApiSecret;

//Since this is only a test version the message are only sent to the registered test numbers.
//So to test the number you first need to sign up to the nexmo using the link in the config file.


//Using the Nexmo Credentials to create the initial nexmo Object
const nexmo = new Nexmo({
    apiKey,
    apiSecret
});

//This function takes all the relavent details required to send a SMS
//This service can be used by anyone that provides the relavent information
module.exports = async (userMobileNumbersArray,from,text) => {
    //Taking all the Mobile Numbers provided one by one and sending the Message
    userMobileNumbersArray.map((value) => {
        //Sending the SMS
        nexmo.message.sendSms(from, value, text, (err, responseData) => {
            if (err) {
                console.log(err);
            } else {
                if (responseData.messages[0]['status'] === "0") {
                    console.log("Message sent successfully.");
                } else {
                    console.log(`Message failed with error: ${responseData.messages[0]['error-text']}`);
                }
            }
        });

    });
}

