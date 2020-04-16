import React, { Component } from 'react';

import FireAlarmCard from './fireAlarmCard';

import axios from 'axios';
//Importing sweet alert and sweet alert dark theme
import Swal from 'sweetalert2';
import '@sweetalert2/theme-dark/dark.css';


class fireAlarmMain extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [],
        }
    }

    getData = () => {
        //Getting the FireAlarm data from the REST Api
        axios.get('http://localhost:5000/api/firealarm/')
            .then((res) => {
                if (res.data.success) {
                    this.setState({
                        data: res.data.data,
                    });
                } else {
                    //Showing a error dialog upon errors
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: res.data.msg,
                    })
                }
            })
            .catch(err => {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: err
                })
            });
    }

    
    //Refresing the webSite data in 40 second intervals
    componentDidMount() {
        this.getData();
        this.interval = setInterval(()=>this.getData(), 40*1000);
    }
    //Clearing the interval is the Component get Unmounted
    //This will avoid possible data leaks and memory use errors
    componentWillUnmount() {
        clearInterval(this.interval);
    }

    render() {
        return (
            <div className='container'>
                <div className="row">
                    {this.state.data.map((data, index, self) =>
                        <FireAlarmCard data={data} key={index} />
                    )}
                </div>
            </div>
        );
    }
}

export default fireAlarmMain;