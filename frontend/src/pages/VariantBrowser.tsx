import React, { useState } from 'react';
import axios from '../api/axios';
import { Link } from 'react-router-dom';
import './VariantBrowser.css';

const VariantBrowser = () => {
    const [query, setQuery] = useState('');
    const [individuals, setIndividuals] = useState<number[]>([]);
    const [error, setError] = useState<string | null>(null);

    const handleSearch = async () => {
        const match = query.trim().match(/^(\w+)\s+(\d+)\s+(\S+)\s+(\S+)$/);
        if (!match) {
            setError("Invalid format. Use: <chromosome> <position> <ref> <alt>");
            setIndividuals([]);
            return;
        }

        const [_, chromosome, position, ref, alt] = match;

        try {
            const response = await axios.get(`/api/genomic-variants/individuals-by-variant`, {
                params: {
                    referenceName: chromosome,
                    startPos: position,
                    referenceBases: ref,
                    alternateBases: alt
                },
                withCredentials: true
            });

            if (response.data && response.data.length > 0) {
                setIndividuals(response.data);
                setError(null);
            } else {
                setIndividuals([]);
                setError("No individuals found for this variant.");
            }
        } catch (err) {
            console.error(err);
            setError("Failed to fetch individuals.");
            setIndividuals([]);
        }
    };

    return (
        <div className="variant-container">
            <div className="variant-card">
                <h2>Genomic Variant Browser</h2>
                <input
                    type="text"
                    value={query}
                    onChange={(e) => setQuery(e.target.value)}
                    placeholder="Search by: chromosome position ref alt"
                />
                <button onClick={handleSearch}>Search</button>

                {error && <p>{error}</p>}

                {individuals.length > 0 && (
                    <div>
                        <h4>Individuals with this variant:</h4>
                        <ul>
                            {individuals.map(id => (
                                <li key={id}>
                                    <Link to={`/individuals/${id}`} className="individual-link">
                                        Individual ID: {id}
                                    </Link>
                                </li>
                            ))}
                        </ul>
                    </div>
                )}
            </div>
        </div>
    );
};

export default VariantBrowser;
