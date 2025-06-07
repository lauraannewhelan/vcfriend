import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import IndividualPage from './pages/Individual'; // ✅ Import the correct one
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
                <Route path="/individuals/:id" element={<IndividualPage />} /> {/* ✅ Fixed */}
            </Routes>
        </Router>
    );
}

export default App;
