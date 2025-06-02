// src/App.tsx

import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Login from './pages/Login';             // ✅ Login page as root
import Home from './pages/Home';               // ✅ Home moved to /home
import IndividualPage from './pages/Individual';
import UploadVCF from './pages/UploadVCF';
import VariantBrowser from './pages/VariantBrowser';
import Shortlist from './pages/Shortlist';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Login />} />                       {/* Login = root */}
                <Route path="/home" element={<Home />} />                   {/* Home */}
                <Route path="/upload" element={<UploadVCF />} />
                <Route path="/variants" element={<VariantBrowser />} />
                <Route path="/shortlist" element={<Shortlist />} />
                <Route path="/individuals/:id" element={<IndividualPage />} />
            </Routes>
        </Router>
    );
}

export default App;
