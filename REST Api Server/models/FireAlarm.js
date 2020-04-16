const mongoose = require('mongoose');

const fireAlarmSchema = mongoose.Schema({
    status: {
        type:String,
        default:"Not Active"
    },
    floorNo: {
        type: Number,
        required: true
    },
    roomNo: {
        type: Number,
        required: true
    },
    smokeLevel:{
        type:Number,
        default:0
    },
    co2Level: {
        type:Number,
        default:0
    },
    lastUpdated:{
        type: Date,
        required : true
    }
});

module.exports = FireAlarm = mongoose.model('FireAlarm', fireAlarmSchema);