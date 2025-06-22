import React, { useState } from 'react';
import axios from '../api/axios';
import { Link } from 'react-router-dom';
import './VariantBrowser.css';

const VariantBrowser = () => {
    const [query, setQuery] = useState('');
    const [results, setResults] = useState<any[]>([]);
    const [error, setError] = useState<string | null>(null);

    const handleSearch = async () => {
        const trimmedQuery = query.trim();
        if (!trimmedQuery) {
            setError("Please enter a variant string.");
            setResults([]);
            return;
        }

        try {
            const response = await axios.get(`/api/variants/search-by-variant`, {
                params: { variant: trimmedQuery },
                withCredentials: true,
            });

            if (response.data.length > 0) {
                setResults(response.data);
                setError(null);
            } else {
                setResults([]);
                setError("No matching variants found.");
            }
        } catch (err) {
            console.error(err);
            setError("Failed to fetch variants.");
            setResults([]);
        }
    };

    const summary = results[0]; // take first match for variant summary

    return (
        <div className="variant-container">
            <div className="variant-card">
                <h2>Genomic Variant Browser</h2>
                <input
                    type="text"
                    value={query}
                    onChange={(e) => setQuery(e.target.value)}
                    placeholder="e.g. chr10:2501851:T:0 or NM_001354609.2:c.2122A>G"
                    className="variant-input"
                />
                <button onClick={handleSearch} className="variant-button">Search</button>
                <small className="variant-help">Search using full variant string from the database.</small>

                {error && <p className="variant-error">{error}</p>}

                {summary && (
                    <div className="variant-summary">
                        <h4>Variant Info</h4>
                        <ul>
                            <li><strong>Gene:</strong> {summary.geneRefgene}</li>
                            <li><strong>AA Change:</strong> {summary.aachangeRefgene?.split(",")[0]}</li>
                            <li><strong>Chromosome:</strong> {summary.chr}</li>
                            <li><strong>Start:</strong> {summary.start}</li>
                            <li><strong>End:</strong> {summary.end}</li>
                            <li><strong>Ref / Alt:</strong> {summary.ref} / {summary.alt}</li>
                            <li><strong>Function:</strong> {summary.funcRefgene}</li>
                            <li><strong>REVEL:</strong> {summary.revel}</li>
                        </ul>
                    </div>
                )}

                {results.length > 0 && (
                    <div className="variant-results">
                        <h4>Individuals with this variant</h4>
                        <table>
                            <thead>
                            <tr>
                                <th>Individual ID</th>
                                <th>Gene</th>
                                <th>AA Change</th>
                                <th>Variant</th>
                            </tr>
                            </thead>
                            <tbody>
                            {results.map((v: any) => (
                                <tr key={v.id}>
                                    <td>
                                        <Link to={`/individuals/${v.individualId}`} className="individual-link">
                                            {v.individualId}
                                        </Link>
                                    </td>
                                    <td>{v.geneRefgene}</td>
                                    <td>{v.aachangeRefgene?.split(",")[0]}</td>
                                    <td>{v.variant}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                )}
            </div>
        </div>
    );
};

export default VariantBrowser;
