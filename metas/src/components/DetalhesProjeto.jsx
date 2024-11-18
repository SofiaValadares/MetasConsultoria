import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import "./Home.css";

const DetalhesProjeto = () => {
  const { id } = useParams();
  const [projeto, setProjeto] = useState(null);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {

    const fetchProjeto = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/projetos/${id}`);
        setProjeto(response.data);
        setLoading(false);
      } catch (error) {
        console.error("Erro ao buscar o projeto:", error);
        alert("Erro ao carregar os detalhes do projeto. Tente novamente mais tarde.");
        setLoading(false);
      }
    };

    fetchProjeto();
  }, [id]);

  if (loading) {
    return <div>Carregando...</div>;
  }

  if (!projeto) {
    return <div>Projeto não encontrado.</div>;
  }

  return (
    <div className="home-container">
      <header className="home-header">
        <h1 className="logo" onClick={() => navigate("/")}>
          Metas Consultoria
        </h1>
      </header>

      <div className="detalhes-projeto">
        <h2>{projeto.nome || "Nome do Projeto"}</h2>
        <p>Acompanhado por: {projeto.colaborador || "Nome Colaborador"}</p>

        <div className="descricao">
          <h3>DESCRIÇÃO</h3>
          <p>{projeto.descricao || "Descrição não disponível."}</p>
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
              {projeto.proximosPassos && projeto.proximosPassos.length > 0 ? (
                projeto.proximosPassos.map((passo, index) => (
                  <tr key={index}>
                    <td>{passo.data}</td>
                    <td>{passo.atividade}</td>
                    <td>
                      <select value={passo.status}>
                        <option value="pendente">Pendente</option>
                        <option value="concluido">Concluído</option>
                      </select>
                    </td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="3">Nenhum próximo passo definido.</td>
                </tr>
              )}
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
