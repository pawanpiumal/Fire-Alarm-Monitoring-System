//Importing JSON web token package to admin authorization
const jwt = require('jsonwebtoken');
const secret = require('../config/config').secret;

//Exporting middleware functions
module.exports = {
    //Function to authenticate user based on the JSON web token
    //If the JSON Web token is expired or not provided it will provide a 
    //401 Unauthorized error status and a message
    //This middelware is used in every HTTP method where an admin authorizarion is requried
    verifyUser : (req,res,next)=>{
        //getting the authorization token from the HTTP Request
        let token = req.headers['authorization'];
        if(token){
            if(token.startsWith('Bearer ')){
                token = token.slice(7,token.length);
            }
            jwt.verify(token,secret,(err,authData)=>{
                if(err){
                    return res.status(401).send({success:false,msg:"Token is not Valid"})
                }else{
                    req.authData = authData;
                    next();
                }
            });
        }else{
            return res.status(401).send({success:false,msg:"Authorization Token not provided. Login first."})
        }
    }
}