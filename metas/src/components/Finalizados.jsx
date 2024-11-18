import React from "react";
import "./Home.css";

const Finalizados = () => {
  const projetosFinalizados = [
    { id: 1, nome: "Projeto Finalizado 1", colaborador: "Colaborador A", dataConclusao: "10/10/2024" },
    { id: 2, nome: "Projeto Finalizado 2", colaborador: "Colaborador B", dataConclusao: "15/10/2024" },
  ];

  return (
    <div className="home-container">
      <header className="home-header">
        <h1 className="logo" onClick={() => (window.location.href = "/")}>
          Metas Consultoria
        </h1>
      </header>

      <div className="home-tabs">
        <button
          className="tab"
          onClick={() => (window.location.href = "/")}
        >
          ATIVOS
        </button>
        <button className="tab selected">FINALIZADOS</button>
      </div>

      <div className="projetos-list">
        {projetosFinalizados.map((projeto) => (
          <div key={projeto.id} className="projeto-card">
            <h2>{projeto.nome}</h2>
            <p>{projeto.colaborador}</p>
            <p>Concluído em: {projeto.dataConclusao}</p>
          </div>
        ))}
      </div>

      <footer className="home-footer">
        <p>andrezza.guimaraes@outlook.com</p>
        <p>(87) 99995-1608</p>
        <p>SUS - Ministério da Saúde</p>
      </footer>
    </div>
  );
};

export default Finalizados;