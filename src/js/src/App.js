import React from 'react';
import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Students from './components/students';
import { BrowserRouter as Router, Navigate, Route, Routes } from 'react-router-dom';


function App() {
  return (
    <React.Fragment>          
      <Router>
          <Routes>
            <Route path="/students" element={<Students/>} />
            <Route path="/" element={<Navigate to="/students" />}/>
          </Routes>
      </Router>
      </React.Fragment>
  );
}

export default App;
