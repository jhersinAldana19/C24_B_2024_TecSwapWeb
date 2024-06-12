import React from "react";
import { Link, useLocation } from "react-router-dom";
import "../assets/styles/Navbar.css";

export default function Navbar() {
    const location = useLocation();

    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div className="container-fluid">
                <Link to="/">
                    <img src="./images/a1.png" alt="tecswap" style={{ height: '100px'}} />
                </Link>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav mx-auto mb-2 mb-lg-0">
                            <li className="nav-item">
                                <Link className={`nav-link ${location.pathname === '/' ? 'active' : ''}`} to="/">Inicio</Link>
                            </li>
                            <li className="nav-item">
                                <Link className={`nav-link ${location.pathname === '/misproductos' ? 'active' : ''}`} to="/misproductos">Mis productos</Link>
                            </li>
                            <li className="nav-item">
                                <Link className={`nav-link ${location.pathname === '/sobre' ? 'active' : ''}`} to="/sobre">Sobre nosotros</Link>
                            </li>
                        </ul>
                    </div>
                    <Link className="btn btn-dark" to="/addproducto">
                    
                    <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 20 20"><path fill="currentColor" d="M10.75 4.75a.75.75 0 0 0-1.5 0v4.5h-4.5a.75.75 0 0 0 0 1.5h4.5v4.5a.75.75 0 0 0 1.5 0v-4.5h4.5a.75.75 0 0 0 0-1.5h-4.5z"/></svg>Subir
                    </Link>
                </div>
            </nav>
        </div>
    );
}
