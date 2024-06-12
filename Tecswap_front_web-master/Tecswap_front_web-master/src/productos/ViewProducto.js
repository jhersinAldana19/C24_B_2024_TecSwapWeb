import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import axios from "axios";
import '../assets/styles/ViewProducto.css';

export default function ViewProducto() {
    const [producto, setProducto] = useState({
        titulo: "",
        descripcion: "",
        estado: "",
        cantidad: "",
        categoria_id: "",
        imagen: "",
    });

    const { id } = useParams();

    useEffect(() => {
        loadProducto();
    }, []);

    const loadProducto = async () => {
        const result = await axios.get(`http://localhost:8080/producto/${id}`);
        setProducto(result.data);
    };

    return (
        <div className="container my-5">
            <div className="row justify-content-center">
                <div className="col-md-10 border rounded p-4 shadow content-container">
                    <h2 className="text-center mb-4">Detalles del producto</h2>
                    <div className="row">
                        <div className="col-md-6">
                            <ul className="list-group list-group-flush">
                                <li className="list-group-item">
                                    <b>Título:</b> {producto.titulo}
                                </li>
                                <li className="list-group-item">
                                    <b>Descripción:</b> {producto.descripcion}
                                </li>
                                <li className="list-group-item">
                                    <b>Estado:</b> {producto.estado}
                                </li>
                                <li className="list-group-item">
                                    <b>Cantidad:</b> {producto.cantidad}
                                </li>
                                <li className="list-group-item">
                                    <b>Categoría:</b> {producto.categoria_id}
                                </li>
                            </ul>
                        </div>
                        <div className="col-md-6 text-center">
                            <img 
                                src={`http://localhost:8080${producto.imagen}`} 
                                alt={producto.titulo} 
                                className="img-fluid rounded"
                            />
                        </div>
                    </div>
                    <div className="text-center mt-4">
                        <Link className="btn btn-primary" to="/">
                            Volver al inicio
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    );
}
