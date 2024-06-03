import React from 'react';
import { Link } from 'react-router-dom';
import '../assets/styles/Navbar.css';

export default function Navbar() {
    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-light">
                <div className="container-fluid">
                <Link to="/">
                    <img src="./images/LOGUITO-TECSWAP.png" alt="tecswap" style={{ height: '40px'}} />
                </Link>                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                </div>
                <div id="navbarSupportedContent">
                    <ul className="navbar-nav justify-content-around mb-2 mb-lg-0">
                        <li className="nav-item">
                            <Link className="nav-link active" aria-current="page" to="/">Inicio</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to="/misproductos">Mis productos</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to="/sobrenosotros">Sobre nosotros</Link>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    );
}
