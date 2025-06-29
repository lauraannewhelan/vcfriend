import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Login from './pages/Login';
import Home from './pages/Home';
import IndividualPage from './pages/Individual';
import UploadVCF from './pages/UploadData';
import VariantBrowser from './pages/VariantBrowser';
import Shortlist from './pages/Shortlist';

import ProtectedRoute from './components/ProtectedRoute';
import Navbar from './components/Navbar'; // ✅ Add this import

function App() {
    return (
        <Router>
            <Navbar /> {/* ✅ Add this line to render Logout button */}
            <Routes>
                <Route path="/" element={<Login />} />

                <Route
                    path="/home"
                    element={
                        <ProtectedRoute>
                            <Home />
                        </ProtectedRoute>
                    }
                />

                <Route
                    path="/upload"
                    element={
                        <ProtectedRoute>
                            <UploadVCF />
                        </ProtectedRoute>
                    }
                />

                <Route
                    path="/variants"
                    element={
                        <ProtectedRoute>
                            <VariantBrowser />
                        </ProtectedRoute>
                    }
                />

                <Route
                    path="/shortlist"
                    element={
                        <ProtectedRoute>
                            <Shortlist />
                        </ProtectedRoute>
                    }
                />

                <Route
                    path="/individuals/:id"
                    element={
                        <ProtectedRoute>
                            <IndividualPage />
                        </ProtectedRoute>
                    }
                />
            </Routes>
        </Router>
    );
}

export default App;
