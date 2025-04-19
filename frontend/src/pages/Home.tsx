import React, { useState } from 'react';
import axios from 'axios';

// Define the type for Pedigree
interface Pedigree {
    pedigree_id: string;
    disease: string;
    numSubjects: number;
    members: string;
}

const Home = () => {
    const [searchQuery, setSearchQuery] = useState('');               // State for search input
    const [results, setResults] = useState<Pedigree[]>([]);            // State for storing search results
    const [error, setError] = useState<string | null>(null);           // To store any errors

    // Fetch pedigrees based on the search query (pedigree_id)
    const handleSearch = async () => {
        if (searchQuery) {
            try {
                const response = await axios.get(
                    `http://localhost:8080/api/pedigrees/search?query=${searchQuery}`  // Fetch using query parameter
                );

                // If we have valid data
                if (response.data) {
                    setResults([response.data]);  // Store the results in state
                    setError(null);                // Clear any previous errors
                } else {
                    setError('No families found for this search.');
                    setResults([]);
                }
            } catch (err) {
                setError('Failed to fetch pedigrees.');
                setResults([]);
            }
        }
    };

    return (
        <div className="home-container">
            <div className="content-wrapper">
                <h1 className="main-title">Welcome to VCFriend</h1>
                <p className="subheading">Your secure and user-friendly web application for genomic data analysis.</p>
                <p className="description">
                    Explore, interpret, and analyze your VCF files with ease in a clinically relevant context.
                </p>

                {/* Search Section for Families */}
                <div className="search-family">
                    <input
                        type="text"
                        placeholder="Search for Families by Pedigree ID"
                        value={searchQuery}
                        onChange={(e) => setSearchQuery(e.target.value)} // Update state on change
                    />
                    <button onClick={handleSearch}>Search Families</button>
                </div>

                {/* Show error message if any */}
                {error && <p className="error-message">{error}</p>}

                {/* Show results if any */}
                {results.length > 0 && (
                    <div>
                        <h3>Pedigree Results</h3>
                        <ul>
                            {results.map((pedigree, index) => (
                                <li key={index}>
                                    <strong>{pedigree.pedigree_id}</strong>: {pedigree.disease} ({pedigree.numSubjects} subjects)
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
        </div>
    );
};

export default Home;
