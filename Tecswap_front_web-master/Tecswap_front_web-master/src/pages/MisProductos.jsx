import React, { useEffect, useState } from 'react';
import { Link } from "react-router-dom";
import axios from 'axios';

export default function MisProductos() {

    const [productos, setProducto] = useState([]);

    useEffect(() => {
        loadProductos();
    }, []);

    const loadProductos = async () => {
        try {
            const response = await axios.get("http://localhost:8080/productos"); // Endpoint para obtener los productos del usuario actual
            setProducto(response.data);
        } catch (error) {
            console.error('Error al cargar los productos:', error);
        }
    };

    const deleteProducto = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/producto/${id}`);
            loadProductos();
        } catch (error) {
            console.error('Error al eliminar el producto:', error);
        }
    }

    return (
        <div className="container my-5"><br></br><br></br><br></br><br></br>
            <div className="py-4">
                <h2 className="mb-4">Mis Productos</h2>
                <table className="table table-striped table-hover">
                    <thead className="thead-dark">
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
                                <td>{producto.categoria}</td>
                                <td>{producto.imagen}</td>
                                <td>
                                    <Link className="btn btn-primary btn-sm mx-1" to={`/viewproducto/${producto.id}`}>Ver</Link>
                                    <Link className="btn btn-outline-primary btn-sm mx-1" to={`/editproducto/${producto.id}`}>Editar</Link>
                                    <button className="btn btn-danger btn-sm mx-1" onClick={() => deleteProducto(producto.id)}>Eliminar</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}
