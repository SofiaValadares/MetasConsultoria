import axios from 'axios';

const API_URL = 'http://localhost:8080/dashboard'; 

export const getDashboardData = async () => {
    try {
        const response = await axios.get(API_URL);
        return response.data;
    } catch (error) {
        console.error("Erro ao buscar dados da dashboard", error);
        throw error;
    }
};