const express = require('express');
const app = express();
const mongoose = require('mongoose');
const cors = require('cors');

//Body Parser Middleware
app.use(express.json());
app.use(express.urlencoded({extended:false}));

//use cors middleware for Cross-Origin-Resource-Sharing
app.use(cors());


//api routes
const fireAlarm = require('./routes/api/fireAlrams');
const user = require('./routes/api/users.js');
app.use("/api/firealarm",fireAlarm);
app.use("/api/users",user);



//connecting to mongoDB database
const MongoUri = require('./config/config').mongodb;
mongoose.connect(MongoUri,{useUnifiedTopology:true,useNewUrlParser:true,useFindAndModify : false})
.then(()=>console.log('MongoDB Connected'))
.catch((err)=>console.error(err));


//port 
const PORT =   5000;

app.listen(PORT, ()=> console.log(`Server started on port ${PORT}`));