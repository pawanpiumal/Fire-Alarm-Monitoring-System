//importing nodemailer package
const nodemailer = require('nodemailer');

//importing User mongose modal
const user = require('../models/User');

//import GMAIL credentials
const userName = require('../config/config').userName;
const password = require('../config/config').password;

//This function takes the data of the Fire Alarm that has exceeded the CO2 and Smoke Limit
//And send emails to all the admin users provided email accounts
module.exports = async (res, fireAlarmData) => {
    let userEmails = await user.find({}, { _id: 0, "email": 1 }).catch((err) => res.status(500).send({success:false, err: err }));
    let userEmailsArray = userEmails.map((value) => {
        return value.email;
    });
    userEmailsArray = userEmailsArray.filter((value, index, self) => {
        return self.indexOf(value) === index;
    });
    const transporter = await nodemailer.createTransport({
        service: "Gmail",
        auth: {
            user: userName,
            pass: password
        }
    });
    let info = await transporter.sendMail({
        from: 'Fire Alarm Sensor',
        bcc: userEmailsArray.toString(),
        subject: "Fire Alarm Sensor",
        html: `<h1 style="color: red;font-weight: bold;text-align:center">Fire Alarm Warning</h1>
                <h4>Fire Alarm ID ${fireAlarmData._id} in Floor ${fireAlarmData.floorNo}, Room ${fireAlarmData.roomNo}  has exceeded the CO2 or Smoke limit.</h4>
                <p>CO2 Level : ${fireAlarmData.co2Level}</p>
                <p>Smoke Level : ${fireAlarmData.smokeLevel}</p>`

    });
    console.log("Email Sent");
}

