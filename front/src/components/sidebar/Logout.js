import React from 'react';
import { useNavigate } from 'react-router-dom';

const Logout = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('isAuthenticated'); // Remove o estado de autenticação
    navigate('/login'); // Redireciona para a página de login
  };

  return (
    <a href="#" onClick={(e) => {
      e.preventDefault(); // Evita a navegação padrão do link
      handleLogout();
    }}>
      <span className="icon">🚪</span> Logout
    </a>
  );
};

export default Logout;
