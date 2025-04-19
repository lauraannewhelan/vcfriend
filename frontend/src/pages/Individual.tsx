// src/pages/Individual.tsx
import React, { useEffect, useState } from 'react';
import { getIndividuals } from '../api/getIndividuals';  // Import the function

const IndividualPage = () => {
    const [individuals, setIndividuals] = useState<any[]>([]);

    useEffect(() => {
        const fetchIndividuals = async () => {
            const data = await getIndividuals();
            setIndividuals(data); // Set the fetched data to state
        };

        fetchIndividuals(); // Fetch the individuals on mount
    }, []);

    return (
        <div>
            <h2>Individuals</h2>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Clinical Diagnosis</th>
                    <th>Proband</th>
                </tr>
                </thead>
                <tbody>
                {individuals.length === 0 ? (
                    <tr>
                        <td colSpan={4}>No individuals found</td>
                    </tr>
                ) : (
                    individuals.map((individual) => (
                        <tr key={individual.id}>
                            <td>{individual.id}</td>
                            <td>{individual.name}</td>
                            <td>{individual.clinicalDiagnosis}</td>
                            <td>{individual.proband}</td>
                        </tr>
                    ))
                )}
                </tbody>
            </table>
        </div>
    );
};

export default IndividualPage;  // Ensure export here
