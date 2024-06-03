import axios from 'axios';
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditProducto() {
    let navigate = useNavigate();
    const { id } = useParams();

    const [producto, setProducto] = useState({
        titulo: "",
        descripcion: "",
        estado: "",
        cantidad: "",
        categoria_id: "",
        imagen: "",
    });

    const [selectedFile, setSelectedFile] = useState(null);

    const { titulo, descripcion, estado, cantidad, categoria_id, imagen } = producto;

    const onInputChange = (e) => {
        setProducto({ ...producto, [e.target.name]: e.target.value });
    };

    const onFileChange = (e) => {
        setSelectedFile(e.target.files[0]);
    };

    useEffect(() => {
        loadProducto();
    }, []);

    const onSubmit = async (e) => {
        e.preventDefault();
        
        if (selectedFile) {
            // Cargar la nueva imagen
            const formData = new FormData();
            formData.append('file', selectedFile);
            const uploadResult = await axios.post("http://localhost:8080/upload", formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });
            producto.imagen = uploadResult.data.url;
        }

        await axios.put(`http://localhost:8080/producto/${id}`, producto);
        navigate("/");
    };

    const loadProducto = async () => {
        const result = await axios.get(`http://localhost:8080/producto/${id}`);
        setProducto(result.data);
    };

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Edición de productos</h2>
                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className="mb-3">
                            <label htmlFor="Titulo" className="form-label">Título</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Ingrese el título"
                                name="titulo"
                                value={titulo}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="Descripcion" className="form-label">Descripción</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Ingrese la descripción"
                                name="descripcion"
                                value={descripcion}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="Estado" className="form-label">Estado</label>
                            <select
                                className="form-control"
                                name="estado"
                                value={estado}
                                onChange={(e) => onInputChange(e)}
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
                                placeholder="Ingrese la cantidad"
                                name="cantidad"
                                value={cantidad}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="Categoria" className="form-label">Categoría</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Ingrese la categoría"
                                name="categoria_id"
                                value={categoria_id}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="Imagen" className="form-label">Imagen</label>
                            <input
                                type="file"
                                className="form-control"
                                name="imagen"
                                onChange={(e) => onFileChange(e)}
                            />
                            {imagen && <img src={`http://localhost:8080${imagen}`} alt={titulo} style={{ width: '100px', height: '100px', marginTop: '10px' }} />}
                        </div>

                        <button type="submit" className="btn btn-outline-primary">Enviar</button>
                        <Link className="btn btn-outline-danger mx-2" to="/">Cancelar</Link>
                    </form>
                </div>
            </div>
        </div>
    )
}
