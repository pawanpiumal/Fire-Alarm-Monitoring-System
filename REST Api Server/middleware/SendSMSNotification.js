//Importing Nexmo to send sms
const Nexmo = require('nexmo');

//importing User mongose modal
const user = require('../models/User');

//Importing Api Key and Secret key from config
const apiKey = require('../config/config').nexmoApiKey;
const apiSecret = require('../config/config').nexmoApiSecret;

//Since this is only a test version the message are only sent to the registered test numbers.
//So to test the number you first need to sign up to the nexmo using the link in the config file.



const nexmo = new Nexmo({
    apiKey,
    apiSecret
});

module.exports = async (res, fireAlarmData) => {
    let userMobileNumbers = await user.find({}, { _id: 0, "mobileNumber": 1 }).catch((err) => res.status(500).send({success:false, msg: err }));
    let userMobileNumbersArray = userMobileNumbers.map((value) => {
        return ('94' + value.mobileNumber);
    });
    userMobileNumbersArray = userMobileNumbersArray.filter((value, index, self) => {
        return self.indexOf(value) === index;
    });
    console.log(userMobileNumbersArray.toString());
    const from = 'Fire Alarm Warning';

    const text = `Fire Alarm Warning
    Fire Alarm ID ${fireAlarmData._id} in Floor ${fireAlarmData.floorNo}, Room ${fireAlarmData.roomNo}  has exceeded the CO2 or Smoke limit.
      `;

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

