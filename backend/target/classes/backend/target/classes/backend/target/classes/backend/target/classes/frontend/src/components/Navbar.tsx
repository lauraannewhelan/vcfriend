import React from 'react';
import { useAuth } from '../context/AuthContext';
import { useNavigate, Link } from 'react-router-dom';
import './Navbar.css';

const Navbar: React.FC = () => {
    const { isAuthenticated, logout } = useAuth();
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate('/');
    };

    if (!isAuthenticated) return null;

    return (
        <div className="navbar-wrapper">
            <div className="navbar-container">
                <div className="navbar-left">
                    <Link to="/home">Home</Link>
                    <Link to="/upload">Upload</Link>
                    <Link to="/variants">Variants</Link>
                    <Link to="/shortlist">Shortlist</Link>
                </div>
                <div className="navbar-right">
                    <button className="logout-button" onClick={handleLogout}>
                        Logout
                    </button>
                </div>
            </div>
        </div>
    );
};

export default Navbar;
