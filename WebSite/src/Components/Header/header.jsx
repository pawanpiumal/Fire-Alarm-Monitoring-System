import React, { Component } from 'react';
import './header.css';

class header extends Component {
    render() {
        return (
            <div>
                <nav id="header-nav-bar" className="navbar ">
                    <h1 className="navbar-title">Fire Alarm System</h1>
                </nav>
            </div>
        );
    }
}

export default header;