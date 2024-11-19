// src/components/projetos/ProjectsForms.js

import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { createProject, updateProject } from '../../service/ProjectService'; // Importando os métodos necessários
import './ProjectsForms.css';

const ProjectsForms = ({ project, isEdit = false }) => {
    const [formData, setFormData] = useState({
        name: project?.name || '',
        description: project?.description || '',
        city: project?.city || '',
        publicProject: project?.publicProject || false,
    });

    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: type === 'checkbox' ? checked : value,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            if (isEdit) {
                await updateProject(project.idProject, formData); // Atualizando o projeto
                alert('Projeto atualizado com sucesso!');
            } else {
                await createProject(formData); // Criando o projeto
                alert('Projeto criado com sucesso!');
            }
            navigate('/projects'); // Redirecionando para a lista de projetos
        } catch (err) {
            console.error('Erro ao salvar o projeto:', err);
            setError('Erro ao salvar o projeto. Tente novamente.');
        }
    };

    const handleCancel = () => {
        navigate('/projects'); // Redirecionando para a lista de projetos ao cancelar
    };

    return (
        <div className="project-form-container">
            <h1>{isEdit ? 'Editar Projeto' : 'Criar Novo Projeto'}</h1>
            {error && <p className="error-message">{error}</p>}
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="name">Nome do Projeto:</label>
                    <input
                        type="text"
                        id="name"
                        name="name"
                        value={formData.name}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="description">Descrição:</label>
                    <textarea
                        id="description"
                        name="description"
                        value={formData.description}
                        onChange={handleChange}
                        required
                    ></textarea>
                </div>
                <div className="form-group">
                    <label htmlFor="city">Cidade:</label>
                    <input
                        type="text"
                        id="city"
                        name="city"
                        value={formData.city}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>
                        <input
                            type="checkbox"
                            name="publicProject"
                            checked={formData.publicProject}
                            onChange={handleChange}
                        />
                        Projeto Público
                    </label>
                </div>
                <div className="form-actions">
                    <button type="submit" className="btn-primary">
                        {isEdit ? 'Atualizar' : 'Salvar'}
                    </button>
                    <button type="button" className="btn-secondary" onClick={handleCancel}>
                        Cancelar
                    </button>
                </div>
            </form>
        </div>
    );
};

export default ProjectsForms;
