// src/pages/Login.tsx

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
    const { login } = useAuth();

    const handleLogin = async (e: React.FormEvent) => {
        e.preventDefault();
        setError('');

        try {
            const response = await axios.post('/auth/login', {
                username,
                password,
            });

            console.log('🧪 Login response:', response.data);

            if (response.data === true) {
                login(); // Update auth context (if you track session state)
                navigate('/home');
            } else {
                setError('Invalid username or password.');
            }
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
