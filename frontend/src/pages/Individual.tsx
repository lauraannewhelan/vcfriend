import React, { useEffect, useState } from 'react';
import axios from '../api/axios';
import { useParams } from 'react-router-dom';
import EditIndividualForm from '../components/EditIndividualForm';
import './Individual.css'; // Import the styling

const IndividualPage = () => {
    const { id } = useParams();
    const [individual, setIndividual] = useState<any>(null);
    const [editing, setEditing] = useState(false);
    const [variants, setVariants] = useState<string[]>([]);

    // Fetch individual details
    useEffect(() => {
        const fetchData = async () => {
            const res = await axios.get(`http://localhost:8080/api/individuals/${id}`);
            setIndividual(res.data);
        };
        fetchData();
    }, [id]);

    // Fetch VCF variants
    useEffect(() => {
        const fetchVariants = async () => {
            try {
                const res = await axios.get(`http://localhost:8080/api/individuals/${id}/vcf-variants`);
                setVariants(res.data);
            } catch (error) {
                setVariants(["Failed to load variants or VCF not found."]);
            }
        };
        if (id) fetchVariants();
    }, [id]);

    const handleSave = async () => {
        const res = await axios.get(`http://localhost:8080/api/individuals/${id}`);
        setIndividual(res.data);
        setEditing(false);
    };

    if (!individual) return <p>Loading individual profile...</p>;

    return (
        <div className="individual-container">
            <h2>{individual.name}</h2>

            {!editing ? (
                <>
                    <p><strong>Clinical Diagnosis:</strong> {individual.clinicalDiagnosis}</p>
                    <p><strong>Date of Birth:</strong> {individual.dateOfBirth}</p>
                    <p><strong>Proband:</strong> {individual.proband ? 'Yes' : 'No'}</p>
                    <button onClick={() => setEditing(true)}>Edit</button>
                </>
            ) : (
                <EditIndividualForm individual={individual} onSave={handleSave} />
            )}

            {/* VCF Variants */}
            <div className="variants-section">
                <h3>VCF Variants</h3>
                {variants.length === 0 ? (
                    <p>No variants found.</p>
                ) : (
                    <div className="variant-list">
                        {variants.map((v, i) => (
                            <div key={i}>{v}</div>
                        ))}
                    </div>
                )}
            </div>
        </div>
    );
};

export default IndividualPage;
