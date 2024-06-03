import React, { useEffect, useState } from 'react';
import { Link } from "react-router-dom";
import axios from 'axios';
import Mensaje from '../components/Mensaje';


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

    const deleteProducto = async (id) => {
        await axios.delete(`http://localhost:8080/producto/${id}`);
        loadProductos();
    };

    return (
        <div className="container">
            <Mensaje />
            <div className="py-4">
                <div className="mb-4">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Buscar productos"
                        value={query}
                        onChange={(e) => setQuery(e.target.value)}
                    />
                    <button className="btn btn-primary mt-2" onClick={searchProductos}>Buscar</button>
                </div>
                <table className="table border">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Titulo</th>
                            <th scope="col">Descripcion</th>
                            <th scope="col">Estado</th>
                            <th scope="col">Cantidad</th>
                            <th scope="col">Categoria</th>
                            <th scope="col">Imagen</th>
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        {productos.map((producto, index) => (
                            <tr key={index}>
                                <th scope="row">{index + 1}</th>
                                <td>{producto.titulo}</td>
                                <td>{producto.descripcion}</td>
                                <td>{producto.estado}</td>
                                <td>{producto.cantidad}</td>
                                <td>{producto.categoria_id}</td>
                                <td>
                                    <img src={`http://localhost:8080${producto.imagen}`} alt={producto.titulo} style={{ width: '100px', height: '100px' }} />
                                </td>
                                <td>
                                    <Link className="btn btn-primary mx-2" to={`/viewproducto/${producto.id}`}>Ver</Link>
                                    <Link className="btn btn-outline-primary mx-2" to={`/editproducto/${producto.id}`}>Editar</Link>
                                    <button className="btn btn-danger mx-2" onClick={() => deleteProducto(producto.id)}>Eliminar</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    )
}
