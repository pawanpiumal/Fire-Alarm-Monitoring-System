//importing required express modules
const express = require('express');
const router = express.Router();

//importing JSON Web Token for user account authorization
const jwt = require('jsonwebtoken');
//importing JSON Web Token Secret Key from config file
const secret = require('../../config/config').secret;

//importing user Mongoose Model 
const User = require('../../models/User');

//Adding a User Account
//HTTP POST Request
//requried details are UserName, Password, Email, Mobile Number
//The email and Mobile Number is required since there is a process to 
//inform the user in case of an emergency
//mobile number is 9 digits number without 0
//And this service is only valid for Sri lankan numbers since before sending +94 is added to the number
//Every detail is requred in order to make an user account
//If some details are not provided the system will provide a 400 Bad Request status and
//a message with the unavailavle parameters
//When signedUp the server provide an authorization token to the user.
//This token will be stored in the session storage in the browser
//In the case of a desktop application it will be stored in a Global Variable.
//The provided token expires in 4 hours after generation
router.post('/signup', (req, res) => {
    const data = req.body;
    if (data.userName && data.password && data.email && data.mobileNumber) {
        let UserData = {
            userName: data.userName,
            password: data.password,
            email: data.email,
            mobileNumber: data.mobileNumber
        }
        User.create(UserData, (err, savedData) => {
            if (err) {
                res.status(400).send({ success: false, msg: err });
            } else {
                let token = jwt.sign({ savedData }, secret, {
                    expiresIn: '4h'
                });
                res.status(201).send({ success: true, msg: `User ${savedData.userName} created.`, token })
            }
        });


    } else {
        return res.status(400).send({
            success: false,
            "userName": data.userName ? "Valid" : "Not Provided",
            "password": data.password ? "Valid" : "Not Provided",
            "email": data.email ? "Valid" : "Not Provided",
            "mobileNumber": data.mobileNumber ? "Valid" : "Not Provided",
        })
    }

});


//Login using admin credentials
//HTTP POST request
//Username and password must be provided
//if not error 400  Bad Request will be returned
//if the credentials are valid and a user exists in the user database
//a Authorization token will bre provided
router.post('/login', (req, res) => {
    const data = req.body;
    if (data.userName && data.password) {
        let userData = {
            userName: data.userName,
            password: data.password
        }
        User.findOne(userData, (err, dataFromDB) => {
            if (err) {
                res.status(404).send({ success:false, msg: err });
            } else {
                if (dataFromDB) {
                    let token = jwt.sign({ dataFromDB }, secret, {
                        expiresIn: '4h'
                    });
                    res.status(200).send({  success:true,msg: `User Name ${dataFromDB.userName} Logged in Successfully.`, token });
                } else {
                    res.status(404).send({  success:false,msg: "Not Found" });
                }
            }
        });

    } else {
        res.status(400).send({
            success:false,
            "userName": data.userName ? "Provided" : "Not Provided",
            "password": data.password ? "Provided" : "Not Provided"
        })
    }
})


module.exports = router;