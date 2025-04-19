// src/App.tsx
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import PedigreeSearch from './pages/PedigreeSearch';  // Add the new route
import IndividualVariants from './pages/IndividualVariants';  // Existing pages
import UploadVCF from './pages/UploadVCF';
import VariantBrowser from './pages/VariantBrowser';
import Shortlist from './pages/Shortlist';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/upload" element={<UploadVCF />} />
                <Route path="/variants" element={<VariantBrowser />} />
                <Route path="/shortlist" element={<Shortlist />} />
                <Route path="/pedigree-search" element={<PedigreeSearch />} />  {/* New route for pedigree search */}
                <Route path="/individuals/:individualId" element={<IndividualVariants />} />
            </Routes>
        </Router>
    );
}

export default App;
