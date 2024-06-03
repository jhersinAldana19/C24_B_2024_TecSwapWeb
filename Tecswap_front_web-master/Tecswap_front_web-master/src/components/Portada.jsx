import React from 'react';

const Portada = () => {
  return (
    <div className="portada" style={{ width: '100%' }}>
        <img 
            src="/images/sobreNosotros/portadita.png" 
            alt="Portada Sobre Nosotros" 
            style={{ width: '100%', height: 'auto', maxHeight: '400px', objectFit: 'cover', display: 'block' }} 
        />
    </div>
  );
};

export default Portada;
