import React, { useState } from "react";
import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import "./Home.css";

const Calendario = () => {
  const [menuOpen, setMenuOpen] = useState(false);
  const [funcionarioId, setFuncionarioId] = useState(""); // Estado para o ID do funcionário

  const handleDateClick = (info) => {
    if (!funcionarioId) {
      alert("Por favor, selecione um funcionário antes de marcar o evento.");
      return;
    }

    const selectedDate = info.dateStr;

    const evento = {
      data: selectedDate,
      funcionarioId,
      descricao: "Reunião marcada",
    };

    // Enviar o evento para o backend
    fetch("http://localhost:8080/eventos", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(evento),
    })
      .then((response) => {
        if (response.ok) {
          alert("Evento salvo com sucesso!");
        } else {
          alert("Erro ao salvar o evento. Verifique os dados e tente novamente.");
        }
      })
      .catch((error) => {
        console.error("Erro ao salvar o evento:", error);
        alert("Erro ao conectar com o servidor. Tente novamente mais tarde.");
      });
  };

  return (
    <div className="home-container">
      {/* Cabeçalho */}
      <header className="home-header">
        <h1 className="logo" onClick={() => (window.location.href = "/")}>
          Metas Consultoria
        </h1>
        <div className="menu" onClick={() => setMenuOpen(!menuOpen)}>
          ☰
        </div>
        {menuOpen && (
          <div className="menu-dropdown">
            <button onClick={() => (window.location.href = "/")}>Home</button>
            <button onClick={() => (window.location.href = "/login")}>Logout</button>
          </div>
        )}
      </header>

      {/* Seleção de Funcionário */}
      <div className="funcionario-selector">
        <label htmlFor="funcionario">Selecione um Funcionário:</label>
        <select
          id="funcionario"
          value={funcionarioId}
          onChange={(e) => setFuncionarioId(e.target.value)}
        >
          <option value="">-- Escolha um Funcionário --</option>
          <option value="1">Funcionário 1</option>
          <option value="2">Funcionário 2</option>
          <option value="3">Funcionário 3</option>
        </select>
      </div>

      {/* Calendário */}
      <div className="App">
        <FullCalendar
          plugins={[dayGridPlugin, timeGridPlugin, interactionPlugin]}
          initialView="dayGridMonth"
          headerToolbar={{
            start: "",
            center: "title",
            end: "dayGridMonth,timeGridWeek,timeGridDay",
          }}
          height="80vh"
          dateClick={handleDateClick}
        />
      </div>
    </div>
  );
};

export default Calendario;