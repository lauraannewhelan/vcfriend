// src/pages/IndividualVariants.tsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const IndividualVariants = () => {
    const [variants, setVariants] = useState<any[]>([]);
    const { individualId } = useParams();  // Get individualId from URL

    useEffect(() => {
        const fetchVariants = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/variants/individual/${individualId}`);
                setVariants(response.data);  // Set the variants for this individual
            } catch (error) {
                console.error('Error fetching variants:', error);
            }
        };

        fetchVariants();
    }, [individualId]);

    return (
        <div>
            <h3>Variants for Individual</h3>
            <table>
                <thead>
                <tr>
                    <th>Chromosome</th>
                    <th>Position</th>
                    <th>Ref</th>
                    <th>Alt</th>
                </tr>
                </thead>
                <tbody>
                {variants.map((variant: any) => (
                    <tr key={variant.id}>
                        <td>{variant.chromosome}</td>
                        <td>{variant.position}</td>
                        <td>{variant.ref}</td>
                        <td>{variant.alt}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default IndividualVariants;
