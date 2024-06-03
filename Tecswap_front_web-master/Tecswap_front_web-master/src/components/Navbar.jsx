import React from "react";
import { Link } from "react-router-dom";
import "../assets/styles/Navbar.css";

export default function Navbar() {
    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-light">
                <div className="container-fluid justify-content-center"> {/* Agregamos la clase justify-content-center para centrar los elementos */}
                    <div className="d-flex"> {/* Agregamos esta clase para que los elementos sean flexibles */}
                        <Link className="navbar-brand" to="/">Wen de trueque</Link>
                        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"></span>
                        </button>
                    </div>
                    <div className="collapse navbar-collapse justify-content-center" id="navbarSupportedContent"> {/* Alineamos este div al centro */}
                        <ul className="navbar-nav">
                            <li className="nav-item">
                                <Link className="nav-link" to="/">Inicio</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/misproductos">Mis Productos</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/sobre">Sobre Nosotros</Link>
                            </li>
                        </ul>
                    </div>
                    <div> {/* Este div es opcional, puedes eliminarlo si no deseas añadir elementos al lado derecho */}
                        <Link className="btn btn-outline-light" to="/addproducto">Add Producto</Link>
                    </div>
                </div>
            </nav>
        </div>
    );
}
