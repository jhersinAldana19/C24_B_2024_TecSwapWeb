import axios from 'axios';
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function OfrecerProducto() {
    let navigate = useNavigate();

    const [producto, setProducto] = useState({
        titulo: "",
        descripcion: "",
        estado: "",
        cantidad: "",
        categoria: "",
    });

    const [file, setFile] = useState(null);

    const { titulo, descripcion, estado, cantidad, categoria } = producto;

    const onInputChange = (e) => {
        setProducto({ ...producto, [e.target.name]: e.target.value });
    };

    const onFileChange = (e) => {
        setFile(e.target.files[0]);
    };

    const onSubmit = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append("file", file);
        try {
            // Subir el archivo
            const response = await axios.post("http://localhost:8080/api/upload", formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });

            const imagen_id = response.data; // Asumiendo que el backend devuelve el nombre del archivo

            // Crear los datos del producto para enviar
            const dataToSend = {
                titulo,
                descripcion,
                estado,
                cantidad,
                categoria_id: categoria,
                imagen_id
            };

            // Enviar los datos del producto
            await axios.post("http://localhost:8080/api/ofertas", dataToSend, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            alert("Oferta enviada"); // Mostrar alerta después de enviar la oferta

            navigate("/"); // Redirigir a la página principal después de enviar la oferta
        } catch (error) {
            console.error('Error al enviar la oferta:', error.response ? error.response.data : error.message);
        }
    };

    return (
        <div className="container"><br /><br /><br /><br /><br />
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Ofrecer producto</h2>
                    <form onSubmit={onSubmit}>
                        <div className="mb-3">
                            <label htmlFor="Titulo" className="form-label">Nombre</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Agrega el nombre"
                                name="titulo"
                                value={titulo}
                                onChange={onInputChange}
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="Descripcion" className="form-label">Descripción</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Agrega la descripción"
                                name="descripcion"
                                value={descripcion}
                                onChange={onInputChange}
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="Estado" className="form-label">Estado</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Agrega el estado"
                                name="estado"
                                value={estado}
                                onChange={onInputChange}
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="Cantidad" className="form-label">Cantidad</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Agrega la cantidad"
                                name="cantidad"
                                value={cantidad}
                                onChange={onInputChange}
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="Categoria" className="form-label">Categoría</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Agrega la categoría"
                                name="categoria"
                                value={categoria}
                                onChange={onInputChange}
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="Imagen" className="form-label">Imagen</label>
                            <input
                                type="file"
                                className="form-control"
                                onChange={onFileChange}
                            />
                        </div>

                        <button type="submit" className="btn btn-outline-primary">Enviar</button>
                        <Link className="btn btn-outline-danger mx-2" to="/">Cancelar</Link>
                    </form>
                </div>
            </div>
        </div>
    );
}
