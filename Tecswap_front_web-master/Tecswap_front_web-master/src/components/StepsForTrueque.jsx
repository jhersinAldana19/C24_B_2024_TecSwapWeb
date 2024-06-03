import React from 'react';

const StepsForTrueque = () => {
  return (
    <div className="steps-container">
      <h2 className="title">¿Cómo funciona?</h2>
      <div className="steps">
        <div className="step">
          <div className="step-number">1</div>
          <div className="step-description">Sube un producto</div>
        </div>
        <div className="step">
          <div className="step-number">2</div>
          <div className="step-description">Busca un producto que desees</div>
        </div>
        <div className="step">
          <div className="step-number">3</div>
          <div className="step-description">Realiza el trueque</div>
        </div>
      </div>
      <style jsx>{`
        .steps-container {
          background-color: #00B1F7; /* Azul */
          color: white; /* Texto en blanco */
          padding: 20px;
          border-radius: 10px;
          display: flex;
          flex-direction: column;
          align-items: center;
          gap: 10px;
          margin-top: 20px;
        }

        .title {
          font-size: 1.8em;
          margin-bottom: 10px;
          font-family: 'Comic Sans MS', cursive; /* Cambia la familia tipográfica */
        }

        .steps {
          display: flex;
        }

        .step {
          display: flex;
          flex-direction: column;
          align-items: center;
          margin-right: 20px;
        }

        .step-number {
          font-size: 3em; /* Números más grandes */
          font-weight: bold; /* Números más gruesos */
          margin-bottom: 5px;
          font-family: 'Comic Sans MS', cursive; /* Cambia la familia tipográfica */
        }

        .step-description {
          font-size: 1.2em;
          text-align: center;
          font-family: 'Comic Sans MS', cursive; /* Cambia la familia tipográfica */
        }
      `}</style>
    </div>
  );
};

export default StepsForTrueque;
