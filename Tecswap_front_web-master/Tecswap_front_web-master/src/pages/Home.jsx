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
            setLoading(false);
        }, 2000);
    };

    // para buscar los productos
    const searchProductos = async () => {
        setLoading(true); 
        const result = await axios.get(`http://localhost:8080/productos/search?query=${query}`);
        setTimeout(() => {
            setProductos(result.data);
            setLoading(false); 
        }, 2000); 
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
                                width: '500px', 
                                padding: '10px', 
                                fontSize: '16px', 
                                borderRadius: '15px'
                            }}
                        />
                        <button className="btn custom-btn rounded-button mt-2" onClick={searchProductos}
                         style={{
                            width: '150px', 
                            padding: '10px', 
                            fontSize: '16px', 
                            borderRadius: '15px',
                            marginTop: '0', 
                            height: '40px' 
                        }}> 
                            Buscar
                            </button>
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
                                                <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 14 14">
                                                    <path fill="none" stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" d="M8.266 11.908a1.773 1.773 0 0 1-2.527 0L1.49 7.7c-2.84-2.842.87-9.12 5.511-4.478c4.634-4.633 8.344 1.644 5.511 4.478z"/>
                                                </svg>
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
