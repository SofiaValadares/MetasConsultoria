import React from "react";
import { useParams, useLocation } from "react-router-dom";
import "./Home.css"; 

const DetalhesProjeto = () => {
  const { id } = useParams(); 
  const location = useLocation();
  const projeto = location.state?.projeto;

  return (
    <div className="home-container">
      <header className="home-header">
        <h1 className="logo" onClick={() => (window.location.href = "/")}>
          Metas Consultoria
        </h1>
      </header>

      <div className="detalhes-projeto">
        <h2>{projeto?.nome || "Nome do Projeto"}</h2>
        <p>Acompanhado por: {projeto?.colaborador || "Nome Colaborador"}</p>

        <div className="descricao">
          <h3>DESCRIÇÃO</h3>
          <p>
            {projeto?.descricao ||
              "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis rutrum urna sed elit fringilla sagittis. Etiam elementum purus nec vehicula egestas..."}
          </p>
        </div>

        <div className="tabela">
          <h3>PRÓXIMOS PASSOS</h3>
          <table>
            <thead>
              <tr>
                <th>DATA</th>
                <th>ATIVIDADE</th>
                <th>STATUS</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>20/11/2024</td>
                <td>Revisar documentação</td>
                <td>
                  <select>
                    <option value="pendente">Pendente</option>
                    <option value="concluido">Concluído</option>
                  </select>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <footer className="home-footer">
        <p>andrezza.guimaraes@outlook.com</p>
        <p>(87) 99995-1608</p>
        <p>SUS - Ministério da Saúde</p>
      </footer>
    </div>
  );
};

export default DetalhesProjeto;
