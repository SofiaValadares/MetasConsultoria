import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Sidebar from "./components/sidebar/Sidebar";
import Login from "./components/login/Login"; // Importe o componente de Login
import Dashboard from "./components/dashboard/Dashboard";
import ClientList from "./components/client/ClientList";
import ClientForm from "./components/client/ClientForm";
import ProjectsList from './components/projetos/ProjectsList';
import ProjectForm from './components/projetos/ProjectsForms';


// Componente ProtectedRoute
const ProtectedRoute = ({ children }) => {
    const isAuthenticated = localStorage.getItem("isAuthenticated") === "true";
    return isAuthenticated ? children : <Navigate to="/login" replace />;
};

const App = () => {
    const isAuthenticated = localStorage.getItem("isAuthenticated") === "true";

    return (
        <Router>
            <div className="app-container">
                {/* Exibe o Sidebar apenas se o usuário estiver autenticado */}
                {isAuthenticated && <Sidebar />}
                <div className="content">
                    <Routes>
                        {/* Rota para a página de Login */}
                        <Route path="/login" element={<Login />} />

                        {/* Rotas protegidas */}
                        <Route
                            path="/dashboard"
                            element={
                                <ProtectedRoute>
                                    <Dashboard />
                                </ProtectedRoute>
                            }
                        />

                        <Route
                            path="/clients"
                            element={
                                <ProtectedRoute>
                                    <ClientList />
                                </ProtectedRoute>
                            }
                        />

                        <Route
                            path="/clients/novo"
                            element={
                                <ProtectedRoute>
                                    <ClientForm />
                                </ProtectedRoute>
                            }
                        />

                        {/* Lista de projetos */}
                        <Route
                            path="/projects"
                            element={
                                <ProtectedRoute>
                                    <ProjectsList />
                                </ProtectedRoute>
                            }
                        />

                        {/* Criar novo projeto */}
                        <Route
                            path="/projects/novo"
                            element={
                                <ProtectedRoute>
                                    <ProjectForm />
                                </ProtectedRoute>
                            }
                        />

                        {/* Editar projeto existente */}
                        <Route
                            path="/projects/editar/:id"
                            element={
                                <ProtectedRoute>
                                    <ProjectForm isEdit={true} />
                                </ProtectedRoute>
                            }
                        />

                        {/* Rota padrão que redireciona com base na autenticação */}
                        <Route
                            path="/"
                            element={
                                isAuthenticated ? (
                                    <Navigate to="/dashboard" replace />
                                ) : (
                                    <Navigate to="/login" replace />
                                )
                            }
                        />
                    </Routes>
                </div>
            </div>
        </Router>
    );
};

export default App;
