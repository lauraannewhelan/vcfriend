// src/services/api.js
import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080', // Update if your backend runs elsewhere
});

export default api;
