//Importing React and React.Component
import React, { Component } from 'react';
//StyleSheet required to fire alarm card
import './fireAlarm.css';


class fireAlarmCard extends Component {
    
    //getting the card classnames according to the co2 and smoke levels
    getClass = () => {
        if (this.props.data.status === 'Not Active') {
            return (
                'card not-active-card'
            )
        } else if (this.props.data.co2Level < 5 && this.props.data.smokeLevel < 5) {
            return (
                'card green-card'
            );
        } else if ((this.props.data.smokeLevel < 5 && this.props.data.co2Level === 5) || (this.props.data.smokeLevel === 5 && this.props.data.co2Level < 5)) {
            return (
                'card yello-card'
            );
        } else if (this.props.data.co2Level > 5 || this.props.data.smokeLevel > 5) {
            return (
                'card red-card'
            );
        }
    }


    render() {
        let lastUpdated = new Date(this.props.data.lastUpdated);
        lastUpdated = lastUpdated.toLocaleString();
        // lastUpdated = lastUpdated.getFullYear() + "-" + lastUpdated.getMonth() + "-" + lastUpdated.getDate() + " "
        //     + lastUpdated.getHours() + ":" + lastUpdated.getMinutes() + ":" + lastUpdated.getSeconds();
        return (
            <div className="col-sm-4 py-2 ">
                <div className={this.getClass()}>
                    <div className="card-body ">
                        <div className='card-text' >
                            <p>Status       : {this.props.data.status}</p>
                            <p>Room No      : {this.props.data.roomNo}</p>
                            <p>Floor No     : {this.props.data.floorNo}</p>
                            <p>CO2 Level    : {this.props.data.co2Level}</p>
                            <p>Smoke Level  : {this.props.data.smokeLevel}</p>
                            <p>Last Updated : {lastUpdated}</p>
                        </div>
                        {/* The Commented part is to add SIgnup and Login functions to the WebSite*/}
                        {/* {

                            false ?

                                <div className="btn-group taskRowButton" role="group">
                                    <button className="btn btn-dark" type="button">
                                        123
                            </button>
                                    <button className="btn btn-success" type="button"
                                    >
                                        <i className="fa fa-check"></i>
                                    </button>
                                    <button className="btn btn-danger rounded text-justify" type="button" >
                                        <i className="fa fa-trash-o"></i>
                                    </button>
                                </div>
                                :
                                ""


                        } */}
                    </div>
                </div>
            </div>
        );
    }
}

export default fireAlarmCard;