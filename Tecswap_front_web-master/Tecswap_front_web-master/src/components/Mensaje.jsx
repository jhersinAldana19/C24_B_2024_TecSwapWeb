import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../App.css'; // Asegúrate de que el archivo CSS esté importado

const Mensaje = () => {
    return (
        <div className="container text-center my-4">
            <h2 className="comic-sans">TecSwap</h2>
            <p>Intercambia cosas por cosas <strong>sin necesidad de dinero.</strong></p>
            <p> Sé parte de una comunidad de <strong>colaboración.</strong></p>
        </div>
    );
};

export default Mensaje;