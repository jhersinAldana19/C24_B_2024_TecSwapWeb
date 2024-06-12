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
                        <img src="./images/a1.png" alt="tecswap" style={{ height: '100px' }} />
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
                        <div className="d-flex align-items-center">
                            <Link className="btn btn-dark mx-2" to="/addproducto">
                                <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 20 20">
                                    <path fill="currentColor" d="M10.75 4.75a.75.75 0 0 0-1.5 0v4.5h-4.5a.75.75 0 0 0 0 1.5h4.5v4.5a.75.75 0 0 0 1.5 0v-4.5h4.5a.75.75 0 0 0 0-1.5h-4.5z"/>
                                </svg>
                                <span className="ms-2">Subir</span>
                            </Link>
                            <Link to="/favoritos" className="d-flex align-items-center text-dark mx-2">
                                <svg xmlns="http://www.w3.org/2000/svg" width="1.5em" height="1.5em" viewBox="0 0 14 14">
                                    <path fill="none" stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" d="M8.266 11.908a1.773 1.773 0 0 1-2.527 0L1.49 7.7c-2.84-2.842.87-9.12 5.511-4.478c4.634-4.633 8.344 1.644 5.511 4.478z"/>
                                </svg>
                                <span className="ms-2">Favoritos</span>
                            </Link>
                            {/* Ícono de Notificaciones */}
                            <Link to="/notificaciones" className="d-flex align-items-center text-dark mx-2">
                                <svg xmlns="http://www.w3.org/2000/svg" width="1.5em" height="1.5em" viewBox="0 0 24 24">
                                    <path fill="currentColor" d="M21 19v1H3v-1l2-2v-6c0-3.1 2.03-5.83 5-6.71V4a2 2 0 0 1 2-2a2 2 0 0 1 2 2v.29c2.97.88 5 3.61 5 6.71v6zm-7 2a2 2 0 0 1-2 2a2 2 0 0 1-2-2"/>
                                </svg>
                                <span className="ms-2">Notificaciones</span>
                            </Link>
                            <Link to="/login" className="d-flex align-items-center text-dark mx-2">
                                <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 24 24">
                                    <path stroke="currentColor" d="M21 12a8.958 8.958 0 0 1-1.526 5.016A8.991 8.991 0 0 1-12 21a8.991 8.991 0 0 1-7.474-3.984A9 9 0 1 1-21 12Z"/>
                                    <path fill="currentColor" d="M13.5 9a1.5 1.5 0 0 1-1.5 1.5v1A2.5 2.5 0 0 0-14.5 9zM12 10.5A1.5 1.5 0 0 1-10.5 9h-1a2.5 2.5 0 0 0-2.5 2.5zM10.5 9A1.5 1.5 0 0 1-12 7.5v-1A2.5 2.5 0 0 0-9.5 9zM12 7.5A1.5 1.5 0 0 1-13.5 9h1A2.5 2.5 0 0 0-12 6.5z"/>
                                </svg>
                                <span className="ms-2">Iniciar sesión</span>
                            </Link>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
    );
}
