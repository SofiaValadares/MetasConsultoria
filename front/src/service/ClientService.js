// Importações devem estar no topo do arquivo
import axios from 'axios';

const API_URL = 'http://localhost:8080/clients/';

// Obter todos os clientes
export const getAllClients = async () => {
    try {
        const response = await axios.get(API_URL);
        return response.data;
    } catch (error) {
        throw error;
    }
};

// Obter cliente por ID
export const getClientById = async (id) => {
    try {
        const response = await axios.get(`${API_URL}${id}`);
        return response.data;
    } catch (error) {
        throw error;
    }
};

// Adicionar um novo cliente
export const addClient = async (client) => {
    try {
        const response = await axios.post(API_URL, client);
        return response.data;
    } catch (error) {
        throw error;
    }
};

// Atualizar um cliente existente
export const updateClient = async (id, client) => {
    try {
        const response = await axios.put(`${API_URL}${id}`, client);
        return response.data;
    } catch (error) {
        throw error;
    }
};

// Deletar um cliente
export const deleteClient = async (id) => {
    try {
        const response = await axios.delete(`${API_URL}${id}`);
        return response.data;
    } catch (error) {
        throw error;
    }
};
