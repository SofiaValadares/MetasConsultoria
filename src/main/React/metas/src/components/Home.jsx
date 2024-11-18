import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // Import para navegação
import "./Home.css";

const Home = () => {
  const [selectedTab, setSelectedTab] = useState("ativos");
  const [menuOpen, setMenuOpen] = useState(false);
  const navigate = useNavigate(); // Hook para redirecionamento

  const projetosAtivos = [
    {
      id: 1,
      nome: "Projeto 1",
      colaborador: "Colaborador 1",
      ultimaReuniao: "01/11/2024",
      descricao: "Este é um exemplo de descrição para o Projeto 1.",
    },
    {
      id: 2,
      nome: "Projeto 2",
      colaborador: "Colaborador 2",
      ultimaReuniao: "03/11/2024",
      descricao: "Este é um exemplo de descrição para o Projeto 2.",
    },
    // Outros projetos
  ];

  const handleLogout = () => {
    console.log("Usuário deslogado");
    // Aqui você pode redirecionar para a página de login
  };

  return (
    <div className="home-container">
      {/* Cabeçalho */}
      <header className="home-header">
        <h1 className="logo" onClick={() => navigate("/")}>
          Metas Consultoria
        </h1>
        <div className="menu" onClick={() => setMenuOpen(!menuOpen)}>
          ☰
        </div>
        {menuOpen && (
          <div className="menu-dropdown">
            <button onClick={() => (window.location.href = "/calendario")}>Calendário</button>
            <button onClick={handleLogout}>Logout</button>
          </div>
        )}
      </header>

      {/* Tabs */}
      <div className="home-tabs">
        <button
          className={`tab ${selectedTab === "ativos" ? "selected" : ""}`}
          onClick={() => setSelectedTab("ativos")}
        >
          ATIVOS
        </button>
        <button
          className={`tab ${selectedTab === "finalizados" ? "selected" : ""}`}
          onClick={() => navigate("/finalizados")}
        >
          FINALIZADOS
        </button>
      </div>

      {/* Lista de Projetos */}
      <div className="projetos-list">
        {projetosAtivos.map((projeto) => (
          <div key={projeto.id} className="projeto-card">
            <h2>{projeto.nome}</h2>
            <p>{projeto.colaborador}</p>
            <p>Última reunião: {projeto.ultimaReuniao}</p>
            <button
              onClick={() =>
                navigate(`/projeto/${projeto.id}`, { state: { projeto } })
              }
            >
              MAIS DETALHES
            </button>
          </div>
        ))}
      </div>

      {/* Rodapé */}
      <footer className="home-footer">
        <p>andrezza.guimaraes@outlook.com</p>
        <p>(87) 99995-1608</p>
        <p>SUS - Ministério da Saúde</p>
      </footer>
    </div>
  );
};

export default Home;
