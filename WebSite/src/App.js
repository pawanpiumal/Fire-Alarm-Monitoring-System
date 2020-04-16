import React from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import './App.css';
import FireAlarmMain from './Components/firealarm/fireAlarmMain';
import Header from './Components/Header/header';

function App() {
  return (
    <Router>
      <div className="App">
        <Header/>
        <Route exact path="/" component={FireAlarmMain} />
      </div >
    </Router>
  );
}

export default App;
