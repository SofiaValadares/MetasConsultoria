import React from 'react';
import ReactDOM from 'react-dom/client'; // Atualizado para 'react-dom/client'
import App from './App';
import './index.css';

// Removido o import e atribuição de 'process'

const root = ReactDOM.createRoot(document.getElementById('root')); // Usando createRoot
root.render(
    <React.StrictMode>
        <App />
    </React.StrictMode>
);
