//importing nodemailer package
const nodemailer = require('nodemailer');

//import GMAIL credentials
const userName = require('../config/config').userName;
const password = require('../config/config').password;

//This function takes all the required details tp send an Email
//This function can be used by any service that provides the relevent data needed to send an Email
module.exports = async (toEmailArray,ccEmailArray,bccEmailArray,subject,htmlMessage) => {
    //Creating a NodeMailer Transport using the Gmail credentials
    const transporter = await nodemailer.createTransport({
        service: "Gmail",
        auth: {
            user: userName,
            pass: password
        }
    });
    //Sending the Email
    let info = await transporter.sendMail({
        to: toEmailArray.toString(),
        cc: ccEmailArray.toString(),
        bcc: bccEmailArray.toString(),
        subject: subject,
        html:htmlMessage

    });
    console.log("Email Sent");
}

