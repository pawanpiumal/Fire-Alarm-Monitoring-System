//Importing JSON web token package to admin authorization
const jwt = require('jsonwebtoken');
//Importing the Secret Key required to generate/Verify the Token for a User
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
        //Checking if the Token is empty or not
        if(token){
            //Checking for the validity of the token and if valid get the token sliced
            if(token.startsWith('Bearer ')){
                token = token.slice(7,token.length);
            }
            //verifying the token using the secret key and getting the embedded user data
            jwt.verify(token,secret,(err,authData)=>{
                //Returining an error if the token is not valid
                //401 = Unauthorized access
                if(err){
                    return res.status(401).send({success:false,msg:"Token is not Valid"})
                }else{
                    //Sending the user data to the REST process for further function execution
                    //by embeding it to the req header
                    req.authData = authData;
                    next();
                }
            });
        }else{
            //Sending an Unauthorized access status if the Token is not provided
            return res.status(401).send({success:false,msg:"Authorization Token not provided. Login first."})
        }
    }
}