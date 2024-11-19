import React from 'react';
import './Sidebar.css';

const Sidebar = () => {
  return (
    <aside className="sidebar">
      <div className="sidebar-logo">
        <h1>Metas Consultoria</h1>
      </div>
      <nav className="sidebar-nav">
        <ul>
          <li>
            <a href="/">
              <span className="icon"></span> Dashboard
            </a>
          </li>
          <li>
            <a href="/clients">
              <span className="icon"></span> Clientes
            </a>
          </li>
          <li>
            <a href="/projects">
              <span className="icon"></span> Projetos
            </a>
          </li>
          <li>
            <a href="/agenda">
              <span className="icon"></span> Colaborador
            </a>
          </li>
          <li>
            <a href="/municipio">
              <span className="icon"></span> Município
            </a>
          </li>
          <li>
            <a href="/logout">
              <span className="icon"></span> Logout
            </a>
          </li>
        </ul>
      </nav>
      <footer className="sidebar-footer">
        <p>andrezza.guimaraes@outlook.com</p>
        <p>(87) 99995-1608</p>
        <p>Ministério da Saúde</p>
      </footer>
    </aside>
  );
};

export default Sidebar;
