import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from '../api/axios';
import { useAuth } from '../context/AuthContext';
import './Login.css';

export default function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { login } = useAuth(); // assumes AuthContext handles session state

    const handleLogin = async (e: React.FormEvent) => {
        e.preventDefault();
        setError('');

        const formData = new URLSearchParams();
        formData.append('username', username);
        formData.append('password', password);

        try {
            const response = await axios.post('/login', formData, {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                withCredentials: true, // ensure cookie is stored
            });

            console.log('🧪 Login response:', response);

            // login success - update auth context and navigate
            login();
            navigate('/home');
        } catch (err: any) {
            if (err.response?.status === 401) {
                setError('Unauthorized. Please check your credentials.');
            } else {
                console.error('Login error:', err);
                setError('Login failed. Server unavailable or misconfigured.');
            }
        }
    };

    return (
        <div className="login-container">
            <div className="login-card">
                <h2>Login to VCFriend</h2>
                <form onSubmit={handleLogin}>
                    <input
                        type="text"
                        placeholder="Username"
                        value={username}
                        onChange={e => setUsername(e.target.value)}
                        required
                    />
                    <input
                        type="password"
                        placeholder="Password"
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                        required
                    />
                    <button type="submit">Log In</button>
                    {error && <p className="error-message">{error}</p>}
                </form>
            </div>
        </div>
    );
}
