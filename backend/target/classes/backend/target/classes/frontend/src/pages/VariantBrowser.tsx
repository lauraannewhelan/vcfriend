import React, { useState } from 'react';
import axios from 'axios';

const VariantBrowser = () => {
    const [query, setQuery] = useState('');
    const [variants, setVariants] = useState<any[]>([]);
    const [error, setError] = useState<string | null>(null);

    const handleSearch = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/api/genomic-variants/search?referenceName=${query}`);
            if (response.data && response.data.length > 0) {
                setVariants(response.data);
                setError(null); // Clear previous errors
            } else {
                setError('No genomic variants found for this search.');
                setVariants([]);
            }
        } catch (err) {
            setError('Failed to fetch genomic variants.');
            setVariants([]);
        }
    };

    return (
        <div>
            <h2>Genomic Variant Browser</h2>
            <input
                type="text"
                value={query}
                onChange={(e) => setQuery(e.target.value)}
                placeholder="Search by Reference Name"
            />
            <button onClick={handleSearch}>Search</button>

            {error && <p>{error}</p>}

            {variants.length > 0 && (
                <div>
                    <h3>Search Results</h3>
                    <ul>
                        {variants.map((variant) => (
                            <li key={variant.id}>
                                <strong>{variant.referenceName}</strong>
                                <br />
                                Start Position: {variant.startPos}
                                <br />
                                End Position: {variant.endPos}
                                <br />
                                Reference Bases: {variant.referenceBases}
                                <br />
                                Alternate Bases: {variant.alternateBases}
                                <br />
                                Variant Type: {variant.variantType}
                                <br />
                            </li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
};

export default VariantBrowser;
