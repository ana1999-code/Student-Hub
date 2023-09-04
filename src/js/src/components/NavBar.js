import React from 'react';
import { NavLink, Link } from 'react-router-dom';

const NavBar = () => {
    return ( 
<nav className="navbar navbar-expand-lg navbar-light bg-light">
  <Link className="navbar-brand" to="/students">Student Hub</Link>
  <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span className="navbar-toggler-icon"></span>
  </button>
  <div className="collapse navbar-collapse" id="navbarNav">
    <ul className="navbar-nav">
      <li className="nav-item">
        <NavLink className="nav-link" to="/students">Students<span className="sr-only"></span></NavLink>
      </li>
      <li className="nav-item">
        <NavLink className="nav-link" to="/courses">Courses</NavLink>
      </li>
    </ul>
  </div>
</nav>
     );
}
 
 
export default NavBar;