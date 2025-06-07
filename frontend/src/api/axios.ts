// src/api/axios.ts
import axios from "axios";

const baseHost = `http://${window.location.hostname}:8080`;

const axiosInstance = axios.create({
    baseURL: baseHost,
    headers: {
        "Content-Type": "application/json",
    },
    withCredentials: true,
});

export default axiosInstance;
