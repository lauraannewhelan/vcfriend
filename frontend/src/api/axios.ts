import axios from "axios";

// Dynamically resolve backend host (assumes backend runs on port 8080)
const baseHost = `http://${window.location.hostname}:8080`;

// Secure Axios instance with session cookie support (for Spring Security)
const axiosInstance = axios.create({
    baseURL: baseHost,
    headers: {
        "Content-Type": "application/json",
    },
    withCredentials: true, // REQUIRED: Send cookies like JSESSIONID
});

export default axiosInstance;
