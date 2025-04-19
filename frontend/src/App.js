import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './pages/HomePage';
import IndividualPage from './pages/IndividualPage';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/individuals/:id" element={<IndividualPage />} />
            </Routes>
        </Router>
    );
}

export default App;
