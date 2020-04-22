//importing nodemailer package
const nodemailer = require('nodemailer');

//import GMAIL credentials
const userName = require('../config/config').userName;
const password = require('../config/config').password;

//This function takes the data of the Fire Alarm that has exceeded the CO2 and Smoke Limit
//And send emails to all the admin users provided email accounts
module.exports = async (toEmailArray,ccEmailArray,bccEmailArray,subject,htmlMessage) => {
    const transporter = await nodemailer.createTransport({
        service: "Gmail",
        auth: {
            user: userName,
            pass: password
        }
    });
    let info = await transporter.sendMail({
        to: toEmailArray.toString(),
        cc: ccEmailArray.toString(),
        bcc: bccEmailArray.toString(),
        subject: subject,
        html:htmlMessage

    });
    console.log("Email Sent");
}

