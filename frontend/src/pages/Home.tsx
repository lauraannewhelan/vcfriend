import React, { useEffect, useState } from 'react';
import axios from '../api/axios'; // ✅ Use configured instance
import { useNavigate } from 'react-router-dom';
import './Home.css';

interface Pedigree {
    pedigreeId: string;
    disease: string;
    numSubjects: number;
}

const Home = () => {
    const [searchQuery, setSearchQuery] = useState('');
    const [results, setResults] = useState<Pedigree[]>([]);
    const [individuals, setIndividuals] = useState<any[]>([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const navigate = useNavigate();

    const handleSearch = async () => {
        if (!searchQuery) return;
        setLoading(true);
        try {
            const response = await axios.get(`/api/pedigrees/search`, {
                params: { query: searchQuery },
            });

            if (response.data) {
                setResults([response.data]);
                setError(null);
            } else {
                setError('No families found for this search.');
                setResults([]);
                setIndividuals([]);
            }
        } catch (err) {
            console.error('❌ Failed to fetch pedigrees:', err);
            setError('Failed to fetch pedigrees.');
            setResults([]);
            setIndividuals([]);
        }
        setLoading(false);
    };

    useEffect(() => {
        const fetchIndividuals = async () => {
            if (results.length > 0) {
                try {
                    const res = await axios.get(`/api/individuals/pedigrees/${results[0].pedigreeId}`);
                    console.log('📦 Raw individuals from API:', res.data);

                    const normalized = res.data.map((ind: any) => ({
                        id: ind.id,
                        name: ind.name,
                        clinicalDiagnosis: ind.clinicalDiagnosis,
                        dateOfBirth: ind.dateOfBirth,
                        proband: ind.proband,
                    }));

                    console.log('✅ Normalized individuals:', normalized);
                    setIndividuals(normalized);
                } catch (err) {
                    console.error('❌ Failed to fetch individuals:', err);
                    setIndividuals([]);
                }
            }
        };
        fetchIndividuals();
    }, [results]);

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

            {results.length > 0 && (
                <>
                    <h3>Pedigree Results</h3>
                    <p>
                        <strong>{results[0].pedigreeId}</strong>: {results[0].disease} ({results[0].numSubjects} subjects)
                    </p>

                    <h3 className="mt-6">Members</h3>

                    {individuals.length === 0 ? (
                        <p className="text-gray-600">No individuals found for this pedigree.</p>
                    ) : (
                        <div className="grid grid-cols-3 gap-4 mt-4">
                            {individuals
                                .filter(ind => ind.name && ind.clinicalDiagnosis && ind.dateOfBirth)
                                .map((ind) => (
                                    <div
                                        key={ind.id}
                                        className="p-4 border rounded shadow hover:bg-gray-100 cursor-pointer"
                                        onClick={() => navigate(`/individuals/${ind.id}`)}
                                    >
                                        <h4 className="text-lg font-semibold">{ind.name}</h4>
                                        <p>{ind.clinicalDiagnosis}</p>
                                        <p className="text-sm text-gray-500">{ind.dateOfBirth}</p>
                                        <p className="text-sm">{ind.proband ? 'Proband' : 'Not proband'}</p>
                                    </div>
                                ))}
                        </div>
                    )}
                </>
            )}
        </div>
    );
};

export default Home;
