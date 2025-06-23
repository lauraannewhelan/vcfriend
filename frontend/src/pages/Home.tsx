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

interface Pedigree {
    pedigree_id: string;
    disease: string;
    num_subjects: number;
    genetic_diagnosis: string;
    individuals: Individual[];
}

const Home = () => {
    const [searchQuery, setSearchQuery] = useState('');
    const [results, setResults] = useState<Pedigree | null>(null);
    const [individuals, setIndividuals] = useState<Individual[]>([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const navigate = useNavigate();

    const handleSearch = async () => {
        if (!searchQuery) return;
        setLoading(true);

        try {
            const response = await axios.get('/api/pedigrees/search', {
                params: { query: searchQuery },
            });

            if (response.data) {
                console.log('📦 Full pedigree response:', response.data);
                setResults(response.data);
                setIndividuals(response.data.individuals || []);
                setError(null);
            } else {
                setError('No families found for this search.');
                setResults(null);
                setIndividuals([]);
            }
        } catch (err) {
            console.error('❌ Failed to fetch pedigrees:', err);
            setError('Failed to fetch pedigrees.');
            setResults(null);
            setIndividuals([]);
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

            {results && (
                <>
                    <h3>Pedigree Results</h3>
                    <p>Clinical Diagnosis: {results.disease}</p>
                    <p>Genetic Diagnosis: {results.genetic_diagnosis}</p>

                    <h3 className="mt-6">Members</h3>

                    {individuals.length === 0 ? (
                        <p className="text-gray-600">No individuals found for this pedigree.</p>
                    ) : (
                        <div className="grid grid-cols-3 gap-4 mt-4">
                            {individuals
                                .filter(ind => ind.studyId && ind.clinicalDiagnosis && ind.dateOfBirth)
                                .map((ind) => (
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
                    )}
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
