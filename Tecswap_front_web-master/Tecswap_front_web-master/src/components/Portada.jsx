import React from 'react';

const Portada = () => {
  return (
    <div className="portada" style={{ width: '100%' }}>
        <img 
            src="/images/fondoTecsup.png" 
            alt="Portada Sobre Nosotros" 
            style={{ width: '100%', height: 'auto', maxHeight: '550px', objectFit: 'cover', display: 'block' }} 
        />
    </div>
  );
};

export default Portada;
