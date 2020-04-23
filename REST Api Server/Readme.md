
# Fire Alarm Sensor System
This RESTful web services API is made to handle Fire Alarm Sensors in a building. The Fire Alarm Sensor locations are definded as Room Number and Floor Number. The administrators can register and login to the system. After login in the administrator can add/ edit/ delete fire alarm sensors. When a fire alarm sensor is added any user of the system can get the current status, smoke level, co2 level and the last updated time details of the fire alarm sensor.

## How to Install

First check if Node and Node Package Manager is installed using the follwing commands in the command prompt.
```
node --version
npm --version
```
If both of them are installed clone this directory and run the following command in the command prompt.
```
npm install
```
Then to run the server simple run the following command.
```
npm start
```
## How to test the System

You can use [Postman](https://www.postman.com/) or cURL to check the REST services. The interces and the required data to make an HTTP request is shown below. The URI is as this,

http://localhost: "PORT" / "REST web service URI"

Ex:-

```
http://localhost:5000/api/users/login
http://localhost:5000/api/firealarm/5e936dc8086cf25704c1306c
```

## REST web service URIs 

### /api/firealarm/addFireAlarm

#### POST
##### Summary:

Add Fire Alarm

##### Description:

Add a Fire Alarm by providing the room number and the floor number

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| Authorization | header | Authorization token given in User Login/Registration | Yes | string |
| body | body | Fire Alarm that need to be added | Yes | [AddFireAlarm](#addfirealarm) |

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 201 | Object Created | [AddFireAlarmSuccessResponse](#addfirealarmsuccessresponse) |
| 400 | Bad Request | [AddFireAlarmFailureResponse](#addfirealarmfailureresponse) |

### /api/firealarm/update/{Id}

#### PATCH
##### Summary:

Update Fire Alarm Sensor Location

##### Description:

Change the Room Number or the Floor Number of a Fire Alarm Sensor

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| Authorization | header | Authorization token given in User Login/Registration | Yes | string |
| Id | path | Fire Alarm Sensor ID provided by MongoDB | Yes | string |
| body | body |  | Yes | [UpdateFireAlarm](#updatefirealarm) |

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | [UpdateFireAlarmSuccessResponse](#updatefirealarmsuccessresponse) |
| 400 | Bad Request | [UpdateFireAlarmFailureResponse](#updatefirealarmfailureresponse) |
| 404 | Not Found | [UpdateFireAlarmFailureResponse](#updatefirealarmfailureresponse) |

### /api/firealarm/delete/{Id}

#### DELETE
##### Summary:

Delete Fire Alarm Sensor

##### Description:

Delete a Fire Alarm Sensor from the system

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| Authorization | header | Authorization token given in User Login/Registration | Yes | string |
| Id | path | Fire Alarm Sensor ID provided by MongoDB | Yes | string |

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | [DeleteFireAlarmSuccessResponse](#deletefirealarmsuccessresponse) |
| 400 | Bad Request | [DeleteFireAlarmFailureResponse](#deletefirealarmfailureresponse) |

### /api/firealarm/{Id}

#### PATCH
##### Summary:

Update Fire Alarm Sensor Details

##### Description:

Change the Status or Smoke Level and CO2 Level of a Fire Alarm Sensor.

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| Authorization | header | Authorization token given in User Login/Registration | Yes | string |
| Id | path | Fire Alarm Sensor ID provided by MongoDB | Yes | string |
| body | body | If status is present Other 2 parameters will be ignored. For Update the Other 2 paprameters remove status from the body. | Yes | [UpdateFireAlarmSensorDetailsStatus](#updatefirealarmsensordetailsstatus) |

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | [UpdateFireAlarmSensorSuccessDetails](#updatefirealarmsensorsuccessdetails) |
| 400 | Bad Request | [UpdateFireAlarmSensorFailureDetails](#updatefirealarmsensorfailuredetails) |
| 404 | Not Found | [UpdateFireAlarmSensorFailureDetails](#updatefirealarmsensorfailuredetails) |

### /api/firealarm/

#### GET
##### Summary:

Get all the Fire Alarm Sensor Details

##### Description:

Get All the Details of Every Fire Alarm Sensor present in the system. This method returns id, status, room number, floor number, CO2 level, Smoke level and last updated time per a fire alarm sensor.

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | [GetFireAlarmDetailsSuccess](#getfirealarmdetailssuccess) |
| 400 | Bad Request | [GetFireAlarmDetailsFailure](#getfirealarmdetailsfailure) |

### /api/users/signup

#### POST
##### Summary:

Register a User

##### Description:

Register a user by providing the required data.

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| body | body |  | Yes | [RegisterUser](#registeruser) |

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 201 | Object Created | [RegisterUserSuccess](#registerusersuccess) |
| 400 | Bad Request | [RegisterUserFailure](#registeruserfailure) |

### /api/users/login

#### POST
##### Summary:

User Login

##### Description:

User login by providing the User name and the Password

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| body | body |  | Yes | [UserLogin](#userlogin) |

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 201 | Object Created | [UserLoginSuccess](#userloginsuccess) |
| 400 | Bad Request | [UserLoginFailure](#userloginfailure) |

### Models


#### FireAlarm

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| _id | string |  | No |
| status | string |  | No |
| floorNo | integer |  | No |
| roomNo | integer |  | No |
| smokeLevel | integer |  | No |
| co2Level | integer |  | No |
| lastUpdated | dateTime |  | No |

#### AddFireAlarm

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| roomNo | integer |  | No |
| floorNo | integer |  | No |

#### AddFireAlarmSuccessResponse

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| success | boolean | true | No |
| msg | string |  | No |
| savedData | [FireAlarm](#firealarm) |  | No |

#### AddFireAlarmFailureResponse

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| success | boolean | false | No |
| msg | string | optional | No |
| err | object | optional | No |
| roomNo | string | optional | No |
| floorNo | string | optional | No |

#### UpdateFireAlarm

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| roomNo | integer |  | No |
| floorNo | integer |  | No |

#### UpdateFireAlarmSuccessResponse

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| success | boolean | true | No |
| msg | string |  | No |

#### UpdateFireAlarmFailureResponse

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| success | boolean | false | No |
| msg | string |  | No |
| err | object | optional | No |
| roomNo | string | optional | No |
| floorNo | string | optional | No |

#### DeleteFireAlarmSuccessResponse

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| success | boolean | true | No |
| msg | string |  | No |
| deletedData | [FireAlarm](#firealarm) | Optional | No |

#### DeleteFireAlarmFailureResponse

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| success | boolean | false | No |
| err | object |  | No |

#### UpdateFireAlarmSensorDetailsStatus

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| status | string |  | No |
| smokeLevel | integer |  | No |
| co2Level | integer |  | No |

#### UpdateFireAlarmSensorSuccessDetails

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| success | boolean | true | No |
| msg | string |  | No |

#### UpdateFireAlarmSensorFailureDetails

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| success | boolean | false | No |
| msg | string | optional | No |
| err | object | optional | No |
| smokeLevel | string | optional | No |
| co2Level | string | optional | No |
| satus | string | optional | No |

#### GetFireAlarmDetailsSuccess

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| success | boolean | true | No |
| data | [ [FireAlarm](#firealarm) ] |  | No |

#### GetFireAlarmDetailsFailure

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| success | boolean | false | No |
| err | object |  | No |

#### User

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| _id | string |  | No |
| email | string |  | No |
| userName | string |  | No |
| mobileNumber | integer |  | No |
| password | string |  | No |

#### RegisterUser

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| email | string |  | No |
| userName | string |  | No |
| mobileNumber | integer |  | No |
| password | string |  | No |

#### RegisterUserSuccess

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| success | boolean | true | No |
| token | string |  | No |
| msg | string |  | No |

#### RegisterUserFailure

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| success | boolean | false | No |
| err | object | Optional | No |
| msg | string | Optional | No |
| email | string | Optional | No |
| userName | string | Optional | No |
| mobileNumber | integer | Optional | No |
| password | string | Optional | No |

#### UserLogin

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| userName | string |  | No |
| password | string |  | No |

#### UserLoginSuccess

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| success | boolean | true | No |
| token | string |  | No |
| msg | string |  | No |

#### UserLoginFailure

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| success | boolean | false | No |
| err | object | Optional | No |
| msg | string | Optional | No |
| userName | string | Optional | No |
| password | string | Optional | No |
