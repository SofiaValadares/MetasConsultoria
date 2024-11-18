/*import React from "react";
import Login from "./components/Login";

const App = () => {
  return <Login />;
};

export default App;*/

import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./components/Home";
import Finalizados from "./components/Finalizados";
import DetalhesProjeto from "./components/DetalhesProjeto";
import Calendario from "./components/Calendario";
import Login from "./components/Login";

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/" element={<Home />} />
        <Route path="/finalizados" element={<Finalizados />} />
        <Route path="/projeto/:id" element={<DetalhesProjeto />} />
        <Route path="/calendario" element={<Calendario />} />
      </Routes>
    </Router>
  );
};

export default App;

