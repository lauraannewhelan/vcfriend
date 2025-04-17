import React, { useState } from 'react';
import axios from 'axios';

function FamilyExplorer() {
    const [familyId, setFamilyId] = useState('');
    const [familyData, setFamilyData] = useState(null);
    const [error, setError] = useState('');

    const handleSearch = async () => {
        if (!familyId) return;

        try {
            const response = await axios.get(`http://localhost:8080/families/${familyId}`);
            setFamilyData(response.data);
            setError('');
        } catch (err) {
            setFamilyData(null);
            setError('Family not found or server error.');
        }
    };

    return (
        <div style={{ padding: '20px' }}>
            <h2>🔍 Search Family by ID</h2>
            <input
                type="number"
                value={familyId}
                onChange={(e) => setFamilyId(e.target.value)}
                placeholder="Enter Family ID"
            />
            <button onClick={handleSearch}>Search</button>

            {error && <p style={{ color: 'red' }}>{error}</p>}

            {familyData && (
                <div style={{ marginTop: '20px' }}>
                    <h3>👨‍👩‍👧 Family: {familyData.familyName}</h3>
                    <h4>👤 Members:</h4>
                    <ul>
                        {familyData.individuals.map((indiv) => (
                            <li key={indiv.id}>
                                <strong>{indiv.name}</strong> (ID: {indiv.id})<br />
                                Sex: {indiv.sex}, Diagnosis: {indiv.clinicalDiagnosis}, DOB: {indiv.dateOfBirth}
                                <br />
                                <details>
                                    <summary>🧬 Variants</summary>
                                    <ul>
                                        {indiv.variants.map((variant, index) => (
                                            <li key={index}>
                                                <code>
                                                    {variant.chrom}:{variant.pos} {variant.ref}→{variant.alt}
                                                </code>
                                            </li>
                                        ))}
                                    </ul>
                                </details>
                            </li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
}

export default FamilyExplorer;
