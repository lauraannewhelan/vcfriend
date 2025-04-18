// src/App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

import FamiliesPage from './pages/FamiliesPage';
import IndividualPage from './pages/IndividualPage';
import VariantPage from './pages/VariantPage';

const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<FamiliesPage />} />
                <Route path="/families" element={<FamiliesPage />} />
                <Route path="/individuals/:individualId" element={<IndividualPage />} />
                <Route path="/vcf/:vcfFileId/variants" element={<VariantPage />} />
                <Route path="*" element={<div className="container mt-5">404 Not Found</div>} />
            </Routes>
        </Router>
    );
};

export default App;
