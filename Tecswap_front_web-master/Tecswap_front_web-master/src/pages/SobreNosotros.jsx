import React from 'react';
import Portada from '../components/Portada'; // Asegúrate de que la ruta sea correcta
import 'bootstrap/dist/css/bootstrap.min.css';
import '../App.css'; // Asegúrate de que el archivo CSS esté importado

const SobreNosotros = () => {
    return (
        <div>
            <Portada />
            <div className="container my-4">
                <h1 className="text-center comic-sans">Sobre nosotros</h1>
                <div className="row my-4">
                    <div className="col-md-6 d-flex align-items-center">
                        <p>
                            TecSwap es una plataforma dedicada al intercambio de productos, donde el dinero no es necesario como medio de cambio. Nuestro objetivo es crear una comunidad donde las personas puedan intercambiar bienes de manera justa y beneficiosa para ambas partes.
                        </p>
                    </div>
                    <div className="col-md-6">
                        <img src="./images/sobreNosotros/sobreNosotros.png" alt="Sobre nosotros" className="img-fluid rounded" style={{ maxHeight: '300px' }} />
                    </div>
                </div>

                <h2 className="text-center my-5 comic-sans">Nuestros servicios</h2>
                <div className="row text-center">
                    <div className="col-md-4 mb-4">
                        <div className="card h-100 shadow-sm">
                            <img src="/images/sobreNosotros/nuestrosServicios/servi1.svg" className="card-img-top img-fluid" alt="Servicio 1" style={{ maxHeight: '200px' }} />
                            <div className="card-body">
                                <h5 className="card-title">Intercambio de productos</h5>
                                <p className="card-text">Facilitamos el intercambio de una amplia variedad de productos entre usuarios.</p>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-4 mb-4">
                        <div className="card h-100 shadow-sm">
                            <img src="/images/sobreNosotros/nuestrosServicios/servi2.svg" className="card-img-top img-fluid" alt="Servicio 2" style={{ maxHeight: '200px' }} />
                            <div className="card-body">
                                <h5 className="card-title">Comunidad segura</h5>
                                <p className="card-text">Garantizamos la seguridad y confianza entre los usuarios de nuestra comunidad.</p>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-4 mb-4">
                        <div className="card h-100 shadow-sm">
                            <img 
                                src="/images/sobreNosotros/nuestrosServicios/servi3.svg" 
                                className="card-img-top img-fluid" 
                                alt="Servicio 3" 
                                style={{ maxHeight: '200px' }}
                            />
                            <div className="card-body">
                                <h5 className="card-title">Variedad de productos</h5>
                                <p className="card-text">Encuentra una amplia gama de productos disponibles para intercambiar, desde dispositivos electrónicos hasta libros de texto.</p>
                            </div>
                        </div>
                    </div>
                </div>

                <h2 className="text-center my-5 comic-sans">¿Por qué elegirnos?</h2>
                <ul className="list-unstyled text-center">
                    <li><i className="bi bi-check-circle-fill me-2"></i>Plataforma intuitiva y fácil de usar</li>
                    <li><i className="bi bi-check-circle-fill me-2"></i>Comunidad confiable y segura</li>
                    <li><i className="bi bi-check-circle-fill me-2"></i>Sin necesidad de dinero, promueve el intercambio justo</li>
                    <li><i className="bi bi-check-circle-fill me-2"></i>Soporte técnico y atención al cliente 24/7</li>
                    <li><i className="bi bi-check-circle-fill me-2"></i>Variedad de productos disponibles para intercambio</li>
                </ul>

                <h2 className="text-center my-5 comic-sans">Conoce más sobre nosotros</h2>
<div className="row">
    <div className="col-md-6">
        <div className="ratio ratio-16x9">
            <iframe 
                src="https://www.youtube.com/embed/eff5Ou4FYAc" // Reemplaza VIDEO_ID con el ID del video de YouTube
                title="YouTube video" 
                allowFullScreen
            />
        </div>
    </div>
    <div className="col-md-6 d-flex align-items-center">
        <div>
            <p>
                En TecSwap, nuestra misión es fomentar el intercambio justo y sostenible de productos. Creemos que cada objeto tiene un valor que puede ser apreciado por alguien más. A través de nuestra plataforma, queremos conectar a personas que buscan una manera alternativa de adquirir lo que necesitan, mientras promueven una economía circular y reducen el desperdicio.
            </p>
            <p>
                Nuestro equipo está dedicado a proporcionar una experiencia segura y confiable para todos nuestros usuarios. Con herramientas fáciles de usar y un enfoque en la comunidad, TecSwap se destaca como la mejor opción para aquellos que desean intercambiar productos de manera justa y sin complicaciones.
            </p>
        </div>
    </div>
</div>

            </div>
        </div>
    );
};

export default SobreNosotros;