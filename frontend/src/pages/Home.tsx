import React, { useState } from 'react';
import axios from '../api/axios';
import { useNavigate } from 'react-router-dom';
import './Home.css';

interface Individual {
    id: number;
    studyId: string;
    clinicalDiagnosis: string;
    dateOfBirth: string;
    proband: boolean;
    sexLabel: string;
}

const Home = () => {
    const [searchQuery, setSearchQuery] = useState('');
    const [individuals, setIndividuals] = useState<Individual[]>([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const navigate = useNavigate();

    const handleSearch = async () => {
        if (!searchQuery) return;
        setLoading(true);
        setError(null);
        setIndividuals([]);

        try {
            const response = await axios.get(`/api/individuals/pedigrees/${searchQuery}`);
            console.log('📦 Individuals for pedigree:', response.data);

            if (response.data.length > 0) {
                setIndividuals(response.data);
            } else {
                setError('No individuals found for this pedigree.');
            }
        } catch (err) {
            console.error('❌ Failed to fetch individuals:', err);
            setError('Error fetching individuals for pedigree.');
        }

        setLoading(false);
    };

    return (
        <div className="home-container">
            <h1 className="main-title">Welcome to VCFriend</h1>
            <p className="centered-text">Your secure and user-friendly web application for genomic data analysis.</p>

            <div className="search-family mt-4 mb-4">
                <input
                    type="text"
                    placeholder="Search for Families by Pedigree ID"
                    value={searchQuery}
                    onChange={(e) => setSearchQuery(e.target.value)}
                />
                <button onClick={handleSearch} disabled={loading}>
                    {loading ? 'Searching...' : 'Search Families'}
                </button>
            </div>

            {error && <p className="text-red-600">{error}</p>}

            {individuals.length > 0 && (
                <>
                    <h3>Pedigree Members</h3>
                    <div className="grid grid-cols-3 gap-4 mt-4">
                        {individuals.map((ind) => (
                            <div
                                key={ind.id}
                                className="p-4 border rounded shadow hover:bg-gray-100 cursor-pointer"
                                onClick={() => navigate(`/individuals/${ind.id}`)}
                            >
                                <h4 className="text-lg font-semibold">Study ID: {ind.studyId}</h4>
                                <p>Sex: {ind.sexLabel}</p>
                                <p>Clinical Diagnosis: {ind.clinicalDiagnosis}</p>
                                <p className="text-sm text-gray-500">{ind.dateOfBirth}</p>
                                <p className="text-sm">{ind.proband ? 'Proband' : 'Not proband'}</p>
                            </div>
                        ))}
                    </div>
                </>
            )}

            <div className="bottom-banner">
                <img src="/hgrg-banner.png" alt="Human Genetics Research Group Logo" />
                <p className="banner-caption">Human Genetics Research Group, RCSI</p>
            </div>
        </div>
    );
};

export default Home;
