import React, { Component } from 'react';
import './header.css';

class header extends Component {
    render() {
        return (
            <div>
                <nav id="header-nav-bar" className="navbar ">
                    <h1 className="navbar-title">Fire Alarm System</h1>
                    {/* This Commented Part is to add User Registration and Login */}
                    {/* <ul className="nav nav-pills">
                        <li className="nav-item">
                            <a className="nav-link" href="/login">Login</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/signup">Signup</a>
                        </li>
                    </ul> */}
                </nav>
            </div>
        );
    }
}

export default header;