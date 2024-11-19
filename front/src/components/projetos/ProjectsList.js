// src/components/project/ProjectList.js

import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import Sidebar from '../../components/sidebar/Sidebar';
import Modal from 'react-modal';
import { getAllProjects, deleteProject, finalizeProject } from '../../service/ProjectService';
import './ProjectsList.css';

Modal.setAppElement('#root'); // Para acessibilidade

const ProjectList = () => {
    const [projects, setProjects] = useState([]);
    const [filter, setFilter] = useState('');
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [modalMessage, setModalMessage] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchProjects = async () => {
            try {
                const projectsData = await getAllProjects();
                setProjects(projectsData);
            } catch (error) {
                setModalMessage('Erro ao carregar os projetos.');
                setIsModalOpen(true);
            }
        };

        fetchProjects();
    }, []);

    // Filtra os projetos com base no nome
    const filteredProjects = projects.filter(project =>
        project.name.toLowerCase().includes(filter.toLowerCase())
    );

    const handleDelete = async (id) => {
        if (window.confirm('Tem certeza que deseja excluir este projeto?')) {
            try {
                await deleteProject(id);
                setProjects(projects.filter(project => project.idProject !== id));
                setModalMessage('Projeto excluído com sucesso!');
                setIsModalOpen(true);
            } catch (error) {
                setModalMessage('Não é possível excluir o projeto no momento.');
                setIsModalOpen(true);
            }
        }
    };


    const closeModal = () => {
        setIsModalOpen(false);
    };

    return (
        <div className="project-list-page">
            <Sidebar />
            <div className="project-list-container">
                <div className="header">
                    <div className="header-buttons">
                        <div className="search-bar">
                            <input
                                type="text"
                                placeholder="Filtrar por nome"
                                value={filter}
                                onChange={(e) => setFilter(e.target.value)}
                            />
                        </div>
                        <Link to="/projects/novo">
                            <button className="btn-primary">Criar Novo Projeto</button>
                        </Link>
                    </div>
                </div>

                <div className="project-cards">
                    {filteredProjects.map(project => (
                        <div className="project-card" key={project.idProject}>
                            <h3>{project.name}</h3>
                            <p><strong>Descrição:</strong> {project.description}</p>
                            <p><strong>Cidade:</strong> {project.city?.name || 'Não especificada'}</p>
                            <p><strong>Data de Conclusão:</strong> {project.date ? project.date : 'Pendente'}</p>
                            <div className="actions">
                                <button
                                    className="btn-delete"
                                    onClick={() => handleDelete(project.idProject)}
                                >
                                    Excluir
                                </button>
                            </div>
                        </div>
                    ))}
                </div>

                {/* Modal para mensagens */}
                <Modal
                    isOpen={isModalOpen}
                    onRequestClose={closeModal}
                    className="modal"
                    overlayClassName="overlay"
                >
                    <div className="modal-content">
                        <p>{modalMessage}</p>
                        <button onClick={closeModal} className="btn-primary">Fechar</button>
                    </div>
                </Modal>
            </div>
        </div>
    );
};

export default ProjectList;
