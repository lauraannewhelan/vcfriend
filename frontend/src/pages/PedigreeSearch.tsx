import React, { useState } from 'react';
import axios from 'axios';

interface Pedigree {
    pedigree_id: string;
    disease: string;
    numSubjects: number;
    members: string;
}

const PedigreeSearch = () => {
    // Define state hooks
    const [query, setQuery] = useState('');               // For the search query (pedigree_id)
    const [results, setResults] = useState<Pedigree[]>([]); // To store the fetched results
    const [error, setError] = useState<string | null>(null); // To store any errors

    // Fetch pedigrees based on the search query (pedigree_id)
    const handleSearch = async () => {
        try {
            // Correct URL to match query parameter approach
            const response = await axios.get(
                `http://localhost:8080/api/pedigrees/search?query=${query}`  // Correct query parameter usage
            );

            // If we have valid data
            if (response.data) {
                const resultData = Array.isArray(response.data) ? response.data : [response.data];
                setResults(resultData);  // Update the results state
                setError(null);           // Clear any previous errors
            } else {
                setError('No families found for this search.');
                setResults([]);
            }
        } catch (err) {
            setError('Failed to fetch pedigrees.');
            setResults([]);
        }
    };

    return (
        <div className="pedigree-search">
            <h2>Pedigree Search</h2>
            <input
                type="text"
                value={query}
                onChange={(e) => setQuery(e.target.value)} // Update the query value as the user types
                placeholder="Enter Pedigree ID"
            />
            <button onClick={handleSearch}>Search</button>

            {/* Show error message if any */}
            {error && <p className="error-message">{error}</p>}

            {/* Show results if any */}
            {results.length > 0 && (
                <div>
                    <h3>Pedigree Results</h3>
                    <ul>
                        {results.map((pedigree, index) => (
                            <li key={index}>
                                <strong>{pedigree.pedigree_id}</strong>: {pedigree.disease} (
                                {pedigree.numSubjects} subjects)
                                <br />
                                <strong>Members:</strong>{' '}
                                <span>
                                    {pedigree.members
                                        .replace('[', '')
                                        .replace(']', '')
                                        .replace(/"/g, '')
                                        .split(', ')
                                        .join(', ')}
                                </span>
                            </li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
};

export default PedigreeSearch;
