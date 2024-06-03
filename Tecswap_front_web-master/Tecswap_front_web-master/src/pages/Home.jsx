import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../App.css'; // Verifica que este archivo esté en la ubicación correcta
import StepsForTrueque from '../components/StepsForTrueque';
import Mensaje from '../components/Mensaje';

export default function Home() {
    const [productos, setProductos] = useState([]);
    const [query, setQuery] = useState("");
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        loadProductos();
    }, []);

    const loadProductos = async () => {
        setLoading(true);
        setError(null);
        try {
            const result = await axios.get("http://localhost:8080/productos");
            setProductos(result.data);
        } catch (error) {
            console.error("Error al cargar los productos:", error);
            setError("Error al cargar productos");
        } finally {
            setLoading(false);
        }
    };

    const searchProductos = async () => {
        setLoading(true);
        setError(null);
        try {
            if (query.trim() === "") {
                await loadProductos();
                return;
            }
            const result = await axios.get(`http://localhost:8080/productos/search?query=${query}`);
            setProductos(result.data);
        } catch (error) {
            console.error("Error al buscar productos:", error);
            setError("Error al buscar productos");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <div className="container"><br /><br />
                <Mensaje />
                <StepsForTrueque />
                <div className="container">
                    <div className="mb-4">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Buscar productos"
                            value={query}
                            onChange={(e) => setQuery(e.target.value)}
                        />
                        <button className="btn btn-dark mt-2" onClick={searchProductos}>Buscarrrrrrr</button>
                    </div>
                    <Link to="/addproducto" className="btn btn-dark my-4"> + Publicar</Link>
                </div>
                <div className="py-4">
                    <div className="row row-cols-1 row-cols-md-3 g-3">
                        {loading ? (
                            <div className="text-center">Cargando...</div>
                        ) : error ? (
                            <div className="alert alert-danger">{error}</div>
                        ) : (
                            productos.map((producto, index) => (
                                <div className="col" key={index}>
                                    <div className="card h-100">
                                        <img
                                            src={`http://localhost:8080/producto/files/${producto.imagen_id}`}
                                            className="card-img-top"
                                            alt={producto.titulo}
                                            style={{ height: '150px', objectFit: 'cover' }}
                                        />
                                        <div className="card-body">
                                            <h5 className="card-title">{producto.titulo}</h5>
                                            <p className="card-text text-truncate">{producto.descripcion}</p>
                                            <Link className="btn btn-dark btn-sm mx-2" to={`/viewproducto/${producto.id}`}>Ver</Link>
                                            <Link className="btn btn-dark btn-sm mx-2" to={`/ofrecer-producto`}>Intercambiar</Link>
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
