//Importing express js required modules
const express = require('express');
const router = express.Router();

//Importing Fire Alarm Mongoose model
const fireAlarm = require('../../models/FireAlarm');

//importing User mongose modal
const user = require('../../models/User');

//Importing middleware to authorize admin login
const userAuthorization = require('../../middleware/FireAlarmsMiddleware').verifyUser;

//Importing SMS and Email Sender functions
const sendEmailNotification = require('../../middleware/SendEmailNotification');
const sendSMSNotification = require('../../middleware/SendSMSNotification2');
// const sendSMSNotification = require('../../middleware/SendSMSNotification');

//Adding a Fire Alram
//HTTP POST request
//Adding a firealram if Room number and floor number are provided.
//The default status value is not active and 
//CO2 level and Smoke Level is 0
//The last Updated time is the added time
//Only the admin can add fire alarms. JWT tokens take care of that
//returns a JSON with a attribute msg with the Fire Alarm details
//returns a JSON with the not provided Details if one of the details are not provided
router.post('/addFireAlarm', userAuthorization, (req, res) => {
    const data = req.body;
    if (data.roomNo && data.floorNo) {
        let fireAlarmData = {
            roomNo: data.roomNo,
            floorNo: data.floorNo,
            lastUpdated: new Date()
        }
        //Inserting data to MongoDB
        fireAlarm.create(fireAlarmData, (err, savedData) => {
            if (err) return res.status(400).send({ success: false, err: err });
            res.status(201).send({
                msg: `FireAlarm Created at Floor ${savedData.floorNo} Room ${savedData.roomNo} @${savedData.lastUpdated}.`,
                success: true,
                savedData
            })
        });

    } else {
        //sending a 400 (Bad Request) error when input data is not correct
        res.status(400).send({
            success: false,
            msg: "roomNo or floorNo or both are not provided",
            roomNo: data.roomNo ? "Valid" : "Empty",
            floorNo: data.floorNo ? "Valid" : "Empty"
        });
    }

});


//Update Fire Alarm Details
//HTTP PATCH request
//PATCH Req means only certain data are changed.
//Not the Whole Fire Alram
//Need Admin Authorization
//At least one parameter must be changed
//The Room Number, Floor Number can be changes.
//If the id didnt match any existing fire alarm ID returns a 404 not found error
router.patch('/update/:id', userAuthorization, async (req, res) => {
    const fireAlarmId = req.params.id;
    const data = req.body;
    if (data.roomNo || data.floorNo) {
        let error = false;
        //getting the previous data
        let fireAlarmPervioudData = await fireAlarm.findOne({ _id: fireAlarmId }).catch((err) => { error = true; return res.status(404).send({ success: false, err: err }); });
        if (error) {
            return;
        }
        //updating the required fields
        if (fireAlarmPervioudData) {
            let fireAlarmNewData = {
                "roomNo": data.roomNo ? data.roomNo : fireAlarmPervioudData.roomNo,
                "floorNo": data.floorNo ? data.floorNo : fireAlarmPervioudData.floorNo,
            }
            //updating the data in the mongoDB
            fireAlarm.updateOne({ _id: fireAlarmId }, fireAlarmNewData, (err) => {
                //sending a 400(Bad Request) error if data types doesn't match or any other mongo error
                if (err) {
                    return res.status(400).send({ success: false, err: err });
                }
                //returning a success message
                res.status(200).send({
                    success: true,
                    msg: 'Fire Alarm Updated.'
                });
            });
        } else {
            //returning 404 (Not found) error if the id is invalid
            res.status(404).send({ success: false, msg: "Not Found" });
        }
    } else {
        //returing 400 (Bad Request ) error if the input fields are not valid
        res.status(400).send({
            success: false,
            msg: "roomNo and floorNo are not provided. Provide one of the field or both.",
            roomNo: "Empty",
            floorNo: "Empty"
        });
    }
});

//Delete an Fire Alarm 
//HTTP DELETE request
//Need admin authentication
router.delete("/:id", userAuthorization, (req, res) => {
    const fireAlarmID = req.params.id;
    fireAlarm.findByIdAndDelete(fireAlarmID, (err, deletedData) => {
        if (err) {
            return res.status(400).send({ success: false, err: err });
        } else {
            if (deletedData) {
                //sending a 200 (Ok) if the firealarm deleted successfully
                res.status(200).send({ success: true, msg: `FireAlarm ID ${fireAlarmID} deleted successfully.`, deletedData });
            } else {
                //since the firealarm data is not existing in the DB the process is a success
                //This is the Idempotent property of the DELETE HTTP request
                res.status(200).send({ success: true, msg: "Already Deleted or Invalid firealarm ID" });
            }

        }

    });
});


//update firealarm details
//HTTP PATCH requets
//used to update CO2 Level, Smoke Level, Status of a fire alarm
//CO2 level, Smoke level are required
//Don't need authentication
//Will automatically update the lastupdated time
router.patch('/:id', async (req, res) => {
    let fireAlarmID = req.params.id;
    let data = req.body;
    let error = false;
    let fireAlarmPrevious = await fireAlarm.findById(fireAlarmID).catch((err) => { if (err) error = true; return res.status(404).send({ success: false, err: err }) });
    if (error) {
        return
    }
    if (fireAlarmPrevious) {
        if (data.status) {
            fireAlarm.findByIdAndUpdate(fireAlarmID, { status: data.status ,$inc:{__v:+1}}, (err) => {
                if (err) {
                    return res.status(400).send({ success: false, err: err });
                } else {
                    res.status(200).send({ success: true, msg: "Updated" });
                }
            });
        } else if (data.co2Level && data.smokeLevel) {
            if (data.co2Level <= 10 && data.smokeLevel <= 10 && data.co2Level >= 0 && data.smokeLevel >= 0) {
                let fireAlarmNew = {
                    "co2Level": data.co2Level,
                    "smokeLevel": data.smokeLevel,
                    "lastUpdated": new Date(),
                    $inc : {__v:+1}
                }
                if (data.co2Level > 5 || data.smokeLevel > 5) {
                    //sending the Email and the SMS only one time
                    //The email & sms is sent again only if the co2 level and smoke level
                    //droped under 5 and get increased again
                    if (fireAlarmPrevious.co2Level <= 5 && fireAlarmPrevious.smokeLevel <= 5) {
                        //Assign the previous data to the new data
                        //This is done because the new data should have the id, room no, floor no in order to send the
                        //SMS and the email
                        fireAlarmNew = fireAlarmPrevious;
                        //replace the co2 level and the smoke level with the new data
                        fireAlarmNew.co2Level = data.co2Level;
                        fireAlarmNew.smokeLevel = data.smokeLevel;
                        
                        let userEmailAndMobile = await user.find({}, { _id: 0, "email": 1, "mobileNumber": 1  }).catch((err) => res.status(500).send({ success: false, err: err }));
                        let userEmailsArray = userEmailAndMobile.map((value) => {
                            return value.email;
                        });
                        userEmailsArray = userEmailsArray.filter((value, index, self) => {
                            return self.indexOf(value) === index;
                        });

                        let EmailMessageHTML=`<h1 style="color: red;font-weight: bold;text-align:center">Fire Alarm Warning</h1>
                        <h4>Fire Alarm ID ${fireAlarmNew._id} in Floor ${fireAlarmNew.floorNo}, Room ${fireAlarmNew.roomNo}  has exceeded the CO2 or Smoke limit.</h4>
                        <p>CO2 Level : ${fireAlarmNew.co2Level}</p>
                        <p>Smoke Level : ${fireAlarmNew.smokeLevel}</p>`;
                        let EmailSubject = "Fire Alarm System";
                        sendEmailNotification([],[],userEmailsArray,EmailSubject,EmailMessageHTML);


                        let userMobileNumbersArray = userEmailAndMobile.map((value) => {
                            return ('94' + value.mobileNumber);
                        });
                        userMobileNumbersArray = userMobileNumbersArray.filter((value, index, self) => {
                            return self.indexOf(value) === index;
                        });
                        const from = 'Fire Alarm Warning';
                    
                        const text = `Fire Alarm Warning
                        Fire Alarm ID ${fireAlarmNew._id} in Floor ${fireAlarmNew.floorNo}, Room ${fireAlarmNew.roomNo}  has exceeded the CO2 or Smoke limit.
                          `;
                          
                        //This method is if the SMS is sent via Nexmo
                        // sendSMSNotification(userMobileNumbersArray,from,text);
                        //This method is if the SMS is sent via TextIt
                        sendSMSNotification(userMobileNumbersArray,text);
                    }
                }
                fireAlarm.findByIdAndUpdate(fireAlarmID, fireAlarmNew, (err) => {
                    if (err) {
                        return res.status(400).send({ success: false, err: err });
                    } else {
                        res.status(200).send({ success: true, msg: "Updated" });
                    }
                });
            } else {
                res.status(400).send({
                    success: false,
                    msg: "co2level and smokeLevel must be between 0 and 10",
                    co2Level: data.co2Level >= 0 && data.co2Level <= 10 ? "Valid" : "CO2 Level must be between 0 and 10",
                    smokeLevel: data.smokeLevel >= 0 && data.smokeLevel <= 10 ? "Valid" : "Smoke Level must be between 0 and 10",
                });
            }
        } else {
            res.status(400).send({
                success: false,
                msg: "co2level and smokeLevel or status must be provided",
                co2Level: data.co2Level ? "Valid" : "Empty",
                smokeLevel: data.smokeLevel ? "Valid" : "Empty",
                status: data.status ? "Valid" : "Empty",
            });
        }
    } else {
        res.status(400).send({ success: false, msg: "Invalid Fire Alarm ID." });
    }
});

//Get the fire Alarm Details
//HTTP GET Request
//no authorization needed
router.get("/", (req, res) => {
    fireAlarm.find((err, data) => {
        if (err) {
            return res.status(400).send({ success: false, err: err });
        } else {
            res.status(200).send({ success: true, data });
        }
    });

});

module.exports = router;