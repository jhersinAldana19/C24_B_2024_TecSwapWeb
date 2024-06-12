import React, { useEffect, useState } from 'react';
import { Link } from "react-router-dom";
import axios from 'axios';
import Skeleton from 'react-loading-skeleton';
import 'react-loading-skeleton/dist/skeleton.css';
import Mensaje from '../components/Mensaje';
import '../assets/styles/Home.css'; 
import StepsForTrueque from '../components/StepsForTrueque';

export default function Home() {
    const [productos, setProductos] = useState([]);
    const [query, setQuery] = useState("");
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        loadProductos();
    }, []);

    // para cargar los productos
    const loadProductos = async () => {
        setLoading(true);
        const result = await axios.get("http://localhost:8080/productos");
        setTimeout(() => {
            setProductos(result.data);
            setLoading(false); // set loading to false once data is loaded
        }, 2000); // Simulate 2 seconds delay
    };

    // para buscar los productos
    const searchProductos = async () => {
        setLoading(true); // set loading to true when searching
        const result = await axios.get(`http://localhost:8080/productos/search?query=${query}`);
        setTimeout(() => {
            setProductos(result.data);
            setLoading(false); // set loading to false once data is loaded
        }, 2000); // Simulate 2 seconds delay
    };

    return (
        <div className="container-fluid main-container">
            <div className="content-container">
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
                            style={{ 
                                width: '500px', // Ancho específico del input
                                padding: '10px', // Espaciado interno del input
                                fontSize: '16px', // Tamaño de la fuente
                                borderRadius: '15px' // Bordes redondeados
                            }}
                        />
                        <button className="btn custom-btn rounded-button mt-2" onClick={searchProductos}> Buscar</button>
                    </div>
                    <div className="row">
                        {loading ? (
                            // Skeleton Loader
                            Array.from({ length: 6 }).map((_, index) => (
                                <div className="col-md-4" key={index}>
                                    <div className="card mb-4 shadow-sm custom-card">
                                        <Skeleton height={200} />
                                        <div className="card-body">
                                            <h5 className="card-title custom-text"><Skeleton width={100} /></h5>
                                            <p className="card-text custom-text"><Skeleton count={3} /></p>
                                            <p className="card-text custom-text"><Skeleton width={80} /></p>
                                            <p className="card-text custom-text"><Skeleton width={60} /></p>
                                            <div className="d-flex justify-content-between align-items-center">
                                                <div className="btn-group">
                                                    <Skeleton width={50} height={30} />
                                                    <Skeleton width={70} height={30} className="mx-2" />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ))
                        ) : (
                            // Render products once they are loaded
                            productos.map((producto) => (
                                <div className="col-md-4" key={producto.id}>
                                    <div className="card mb-4 shadow-sm custom-card">
                                        <Link to={`/viewproducto/${producto.id}`}>
                                            <img 
                                                src={`http://localhost:8080${producto.imagen}`} 
                                                alt={producto.titulo} 
                                                className="card-img-top" 
                                                style={{ 
                                                    height: '200px', 
                                                    objectFit: 'contain' 
                                                }} 
                                            />
                                        </Link>
                                        <div className="card-body">
                                            <h5 className="card-title custom-text">{producto.titulo}</h5>
                                            <p className="card-text custom-text">{producto.descripcion}</p>
                                            <div className="d-flex justify-content-between align-items-center">
                                                <div className="btn-group">
                                                    <Link className="btn btn-dark btn-sm mx-2" to={`/ofrecer-producto`}>Intercambiar</Link>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ))
                        )}
                    </div>
                </div>
            </div>
        </div>
    );
}
