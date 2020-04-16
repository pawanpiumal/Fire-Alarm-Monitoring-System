//Importing http package to send sms via a REST Api
const http = require('http');

//importing User mongose modal
const user = require('../models/User');

//importing textit account credentials
const id = require('../config/config').textitID;
const pass = require('../config/config').textitPassword;



module.exports = async (res, fireAlarmData) => {
    let userMobileNumbers = await user.find({}, { _id: 0, "mobileNumber": 1 }).catch((err) => res.status(500).send({success:false, err: err }));
    let userMobileNumbersArray = userMobileNumbers.map((value) => {
        return ('94' + value.mobileNumber);
    });
    userMobileNumbersArray = userMobileNumbersArray.filter((value, index, self) => {
        return self.indexOf(value) === index;
    });


    let text = `Fire Alarm Warning
    Fire Alarm ID ${fireAlarmData._id} in Floor ${fireAlarmData.floorNo}, Room ${fireAlarmData.roomNo}  has exceeded the CO2 or Smoke limit.
      `;
    text = encodeURIComponent(text);

    //Comment the log and Uncomment the other code part to test the system
    console.log("Message Sent");
    // userMobileNumbersArray.map((value) => {
    //     http.get(`http://textit.biz/sendmsg/index.php?id=${id}&pw=${pass}&to=${value}&text=${text}`, (response) => {
    //         console.log(response.statusCode);
    //     });

    // });
}

