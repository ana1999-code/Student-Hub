import React from 'react';
import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Students from './components/students';
import { BrowserRouter as Router, Navigate, Route, Routes } from 'react-router-dom';
import Courses from './components/courses';
import NavBar from './components/NavBar';


function App() {
  return (
    <React.Fragment>          
      <Router>
        <NavBar/>
          <Routes>
            <Route path="/students" element={<Students/>} />
            <Route path="/courses" element={<Courses/>} />
            <Route path="/" element={<Navigate to="/students" />}/>
          </Routes>
      </Router>
      </React.Fragment>
  );
}

export default App;
