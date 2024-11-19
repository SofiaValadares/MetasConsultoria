import React, { useEffect, useState } from 'react';
import './Dashboard.css';
import axios from 'axios';

const Dashboard = () => {
  const [dashboardData, setDashboardData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchDashboardData = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/dashboard'); 
        setDashboardData(response.data);
        setLoading(false);
      } catch (err) {
        setError('Erro ao carregar os dados.');
        setLoading(false);
      }
    };

    fetchDashboardData();
  }, []);

  if (loading) return <div>Carregando...</div>;
  if (error) return <div>{error}</div>;

  return (
    <div className="dashboard-container">
      <header className="dashboard-header">
        <h1>METAS CONSULTORIA</h1>
      </header>

      <main className="dashboard-content">
        <div className="top-cards">
          <div className="card">
            <h2>Clientes</h2>
            <p>{dashboardData.totalClients}</p>
          </div>
          <div className="card">
            <h2>Municípios</h2>
            <p>{dashboardData.totalMunicipalities}</p>
          </div>
          <div className="card">
            <h2>Projetos em Andamento</h2>
            <p>{dashboardData.ongoingProjects}</p>
          </div>
          <div className="card">
            <h2>Colaboradores</h2>
            <p>{dashboardData.activeEmployees}</p>
          </div>
        </div>

        <div className="bottom-cards">
          <div className="large-card">
            <h2>Receita do Mês</h2>
            <ul>
              {dashboardData.monthlyRevenue.map((item, index) => (
                <li key={index}>
                  Data: {item.date}, Receita: R$ {item.revenue.toFixed(2)}
                </li>
              ))}
            </ul>
          </div>
          <div className="large-card">
            <h2>Projetos do Mês</h2>
            <ul>
              {dashboardData.newProjectsThisMonth.map((project, index) => (
                <li key={index}>
                  Projeto: {project.name}, Município: {project.municipality}, Início: {project.startDate}
                </li>
              ))}
            </ul>
          </div>
        </div>
      </main>

      <footer className="dashboard-footer">
        <div className="footer-contact">
          <p>andrezza.guimaraes@outlook.com</p>
          <p>(87) 99995-1608</p>
        </div>
        <div className="footer-logo">
          <p>SUS</p>
          <p>MINISTÉRIO DA SAÚDE</p>
        </div>
      </footer>
    </div>
  );
};

export default Dashboard;
