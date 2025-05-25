// src/api/axios.ts
import axios from "axios";

// Create an Axios instance with default configuration
const axiosInstance = axios.create({
    baseURL: "http://localhost:8080/api", // Your backend URL
    headers: {
        "Content-Type": "application/json",
    },
});

export default axiosInstance;
