// src/services/ProjectService.js

import axios from 'axios';

const API_BASE_URL = '/projects';

const ProjectService = {
    getAllProjects: async () => {
        try {
            const response = await axios.get(`${API_BASE_URL}/`);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar projetos:', error);
            throw error;
        }
    },

    getProjectById: async (id) => {
        try {
            const response = await axios.get(`${API_BASE_URL}/${id}`);
            return response.data;
        } catch (error) {
            console.error(`Erro ao buscar o projeto com id ${id}:`, error);
            throw error;
        }
    },

    createProject: async (project) => {
        try {
            const response = await axios.post(`${API_BASE_URL}/`, project);
            return response.data;
        } catch (error) {
            console.error('Erro ao criar projeto:', error);
            throw error;
        }
    },

    finalizeProject: async (id) => {
        try {
            const response = await axios.post(`${API_BASE_URL}/finalize/${id}`);
            return response.data;
        } catch (error) {
            console.error(`Erro ao finalizar o projeto com id ${id}:`, error);
            throw error;
        }
    },

    updateProject: async (id, project) => {
        try {
            const response = await axios.put(`${API_BASE_URL}/${id}`, project);
            return response.data;
        } catch (error) {
            console.error(`Erro ao atualizar o projeto com id ${id}:`, error);
            throw error;
        }
    },

    deleteProject: async (id) => {
        try {
            const response = await axios.delete(`${API_BASE_URL}/${id}`);
            return response.data;
        } catch (error) {
            console.error(`Erro ao deletar o projeto com id ${id}:`, error);
            throw error;
        }
    },
};

export default ProjectService;
