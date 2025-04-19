// src/api/getIndividuals.js
import axios from 'axios';

export const getIndividuals = async () => {
    try {
        const response = await axios.get('http://localhost:8080/api/individuals');
        return response.data;
    } catch (error) {
        console.error('Error fetching individuals:', error);
        return [];
    }
};
