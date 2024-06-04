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
                    <Link className="btn btn-dark" to="/addproducto">Publicar Producto</Link>
                </div>
            </nav>
        </div>
    );
}
