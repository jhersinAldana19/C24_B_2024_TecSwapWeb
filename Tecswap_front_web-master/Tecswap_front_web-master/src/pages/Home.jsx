import React, { useEffect, useState } from 'react';
import { Link } from "react-router-dom";
import axios from 'axios';
import Mensaje from '../components/Mensaje';
import '../assets/styles/Home.css'; 
import StepsForTrueque from '../components/StepsForTrueque';

export default function Home() {
    const [productos, setProductos] = useState([]);
    const [query, setQuery] = useState("");

    useEffect(() => {
        loadProductos();
    }, []);

    // para cargar los productos
    const loadProductos = async () => {
        const result = await axios.get("http://localhost:8080/productos");
        setProductos(result.data);
    };

    // para buscar los productos
    const searchProductos = async () => {
        const result = await axios.get(`http://localhost:8080/productos/search?query=${query}`);
        setProductos(result.data);
    };

    return (
        <div className="container"><br></br>
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
                            <div className="card mb-4 shadow-sm custom-card">
                                <img 
                                    src={`http://localhost:8080${producto.imagen}`} 
                                    alt={producto.titulo} 
                                    className="card-img-top" 
                                    style={{ 
                                        height: '200px', 
                                        objectFit: 'contain' 
                                    }} 
                                />
                                <div className="card-body">
                                    <h5 className="card-title custom-text">{producto.titulo}</h5>
                                    <p className="card-text custom-text">{producto.descripcion}</p>
                                    <p className="card-text custom-text">Estado: {producto.estado}</p>
                                    <p className="card-text custom-text">Cantidad: {producto.cantidad}</p>
                                    <div className="d-flex justify-content-between align-items-center">
                                        <div className="btn-group">
                                            <Link className="btn btn-sm custom-btn" to={`/viewproducto/${producto.id}`}>Ver</Link>
                                            <Link className="btn btn-dark btn-sm mx-2" to={`/ofrecer-producto`}>Intercambiar</Link>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}
