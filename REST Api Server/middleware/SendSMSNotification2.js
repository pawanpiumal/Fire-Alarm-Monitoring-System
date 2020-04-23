//Importing http package to send sms via a REST Api
const http = require('http');

//importing textit account credentials
const id = require('../config/config').textitID;
const pass = require('../config/config').textitPassword;


//This function takes all the relavent details required to send a SMS
//This service can be used by anyone that provides the relavent information
module.exports = async (userMobileNumbersArray,text) => {
    //Encoding the Text to a URI format since TextIt uses the URI to get the mobile Number and the text message
    //The encoing type is the percentage encoding
    text = encodeURIComponent(text);

    //Uncomment the code to test the system
    // userMobileNumbersArray.map((value) => {
    //     http.get(`http://textit.biz/sendmsg/index.php?id=${id}&pw=${pass}&to=${value}&text=${text}`, (response) => {
    //         console.log(response.statusCode);
    //         if the returned status code is 200 the SMS is successfuly sent
    //     });
    // });

    console.log("Message Sent");
}

