import axios from "axios";
import React, { useEffect,useState } from "react";
import { Link, useParams } from "react-router-dom";

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
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Producto Details</h2>

          <div className="card">
            <div className="card-header">
              Details of user id : {producto.id}
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Titulo:</b>
                  {producto.titulo}
                </li>
                <li className="list-group-item">
                  <b>Descripcion:</b>
                  {producto.descripcion}
                </li>
                <li className="list-group-item">
                  <b>Estado:</b>
                  {producto.estado}
                </li>
                <li className="list-group-item">
                  <b>Cantidad:</b>
                  {producto.cantidad}
                </li>
                <li className="list-group-item">
                  <b>Categoria:</b>
                  {producto.categoria_id}
                </li>
                <li className="list-group-item">
                  <b>Imagen:</b>
                  {producto.imagen}
                </li>
              </ul>
            </div>
          </div>
          <Link className="btn btn-primary my-2" to={"/"}>
            Volver al inicio
          </Link>
        </div>
      </div>
    </div>
  );
}