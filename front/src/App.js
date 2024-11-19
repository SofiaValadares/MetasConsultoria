import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Sidebar from "./components/sidebar/Sidebar";
import Dashboard from "./components/dashboard/Dashboard";
import ClientList from "./components/client/ClientList";
import ClientForm from "./components/client/ClientForm";

const App = () => {
    return (
        <Router future={{ v7_startTransition: true, v7_relativeSplatPath: true }}>
            <div className="app-container">
                <Sidebar />
                <div className="content">
                    <Routes>

                        

                        {/* Rota para a Dashboard */}
                        <Route path="/" element={<Dashboard />} />

                        {/* Rota para a tela de clientes */}
                        <Route path="/clients" element={<ClientList />} />
                        
                        <Route path="/clients/novo" element={<ClientForm />} />

                    </Routes>    
                </div>
            </div>
        </Router>
    );
};

export default App;
