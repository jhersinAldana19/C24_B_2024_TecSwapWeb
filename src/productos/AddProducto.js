import axios from 'axios';
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function AddProducto() {
    let navigate = useNavigate();

    const [producto, setProducto] = useState({
        titulo: "",
        descripcion: "",
        estado: "",
        cantidad: "",
        categoria_id: "",
        imagen: "", // Este campo se usará para almacenar la URL de la imagen una vez cargada
    });

    const [selectedFile, setSelectedFile] = useState(null);

    const { titulo, descripcion, estado, cantidad, categoria_id } = producto;

    const onInputChange = (e) => {
        setProducto({ ...producto, [e.target.name]: e.target.value });
    };

    const onFileChange = (e) => {
        setSelectedFile(e.target.files[0]);
    };

    const onSubmit = async (e) => {
        e.preventDefault();
        try {
            let imagenUrl = "";
            if (selectedFile) {
                const formData = new FormData();
                formData.append("file", selectedFile);
                const uploadResponse = await axios.post("http://localhost:8080/upload", formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data',
                    },
                });
                imagenUrl = uploadResponse.data.url;
            }
    
            const dataToSend = {
                titulo,
                descripcion,
                estado,
                cantidad,
                categoria_id,
                imagen: imagenUrl,
            };
            console.log('Data being sent:', dataToSend); // Verificar los datos antes de enviar
            const response = await axios.post("http://localhost:8080/producto", dataToSend, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });
            console.log(response.data); // Verificar la respuesta
            navigate("/");
        } catch (error) {
            console.error('Error al guardar el producto:', error.response ? error.response.data : error.message);
        }
    };

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Registro de productos</h2>
                    <form onSubmit={onSubmit}>
                        <div className="mb-3">
                            <label htmlFor="Titulo" className="form-label">Titulo</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter your titulo"
                                name="titulo"
                                value={titulo}
                                onChange={onInputChange}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Descripcion" className="form-label">Descripcion</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter your descripcion"
                                name="descripcion"
                                value={descripcion}
                                onChange={onInputChange}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Estado" className="form-label">Estado</label>
                            <select
                                className="form-control"
                                name="estado"
                                value={estado}
                                onChange={onInputChange}
                            >
                                <option value="">Seleccione un estado</option>
                                <option value="pendiente">Pendiente</option>
                                <option value="cancelado">Cancelado</option>
                                <option value="reservado">Reservado</option>
                            </select>
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Cantidad" className="form-label">Cantidad</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter your cantidad"
                                name="cantidad"
                                value={cantidad}
                                onChange={onInputChange}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Categoria" className="form-label">Categoria</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter your categoria"
                                name="categoria_id"
                                value={categoria_id}
                                onChange={onInputChange}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Imagen" className="form-label">Imagen</label>
                            <input
                                type="file"
                                className="form-control"
                                name="imagen"
                                onChange={onFileChange}
                            />
                        </div>
                        <button type="submit" className="btn btn-outline-primary">Enviar</button>
                        <Link type="submit" className="btn btn-outline-danger mx-2" to="/">Cancelar</Link>
                    </form>
                </div>
            </div>
        </div>
    )
}
