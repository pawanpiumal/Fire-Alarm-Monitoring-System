//Importing http package to send sms via a REST Api
const http = require('http');

//importing textit account credentials
const id = require('../config/config').textitID;
const pass = require('../config/config').textitPassword;



module.exports = async (userMobileNumbersArray,text) => {
    text = encodeURIComponent(text);

    //Uncomment the code to test the system
    // userMobileNumbersArray.map((value) => {
    //     http.get(`http://textit.biz/sendmsg/index.php?id=${id}&pw=${pass}&to=${value}&text=${text}`, (response) => {
    //         console.log(response.statusCode);
    //     });
    // });

    console.log("Message Sent");
}

