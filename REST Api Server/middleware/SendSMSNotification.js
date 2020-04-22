//Importing Nexmo to send sms
const Nexmo = require('nexmo');

//Importing Api Key and Secret key from config
const apiKey = require('../config/config').nexmoApiKey;
const apiSecret = require('../config/config').nexmoApiSecret;

//Since this is only a test version the message are only sent to the registered test numbers.
//So to test the number you first need to sign up to the nexmo using the link in the config file.



const nexmo = new Nexmo({
    apiKey,
    apiSecret
});

module.exports = async (userMobileNumbersArray,from,text) => {
    userMobileNumbersArray.map((value) => {
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

