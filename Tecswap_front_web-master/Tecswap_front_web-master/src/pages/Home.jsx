import React, { useEffect, useState } from 'react';
import { Link } from "react-router-dom";
import axios from 'axios';
import Mensaje from '../components/Mensaje';
import '../assets/styles/Home.css'; // Asegúrate de importar el archivo CSS
import StepsForTrueque from '../components/StepsForTrueque';

export default function Home() {
    const [productos, setProductos] = useState([]);
    const [query, setQuery] = useState("");

    useEffect(() => {
        loadProductos();
    }, []);

    const loadProductos = async () => {
        const result = await axios.get("http://localhost:8080/productos");
        setProductos(result.data);
    };

    const searchProductos = async () => {
        const result = await axios.get(`http://localhost:8080/productos/search?query=${query}`);
        setProductos(result.data);
    };

    return (
        <div className="container">
            <Mensaje />
            <StepsForTrueque />
            <div className="py-4">
                <div className="mb-4 search-bar">
                    <input
                        type="text"
                        className="form-control rounded-input"
                        placeholder="Buscar productos"
                        value={query}
                        onChange={(e) => setQuery(e.target.value)}
                    />
                    <button className="btn custom-btn rounded-button mt-2" onClick={searchProductos}>Buscar</button>
                </div>
                <div className="row">
                    {productos.map((producto, index) => (
                        <div className="col-md-4" key={index}>
                            <div className="card mb-4 shadow-sm">
                                <img src={`http://localhost:8080${producto.imagen}`} alt={producto.titulo} className="card-img-top" style={{ height: '200px', objectFit: 'cover' }} />
                                <div className="card-body">
                                    <h5 className="card-title">{producto.titulo}</h5>
                                    <p className="card-text">{producto.descripcion}</p>
                                    <div className="d-flex justify-content-between align-items-center">
                                        <div className="btn-group">
                                            <Link className="btn btn-sm custom-btn" to={`/viewproducto/${producto.id}`}>Ver</Link>
                                            <Link className="btn btn-dark btn-sm mx-2" to={`/ofrecer-producto`}>Intercambiar</Link>
                                        </div>
                                        <small className="text-muted">Cantidad: {producto.cantidad}</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    )
}
