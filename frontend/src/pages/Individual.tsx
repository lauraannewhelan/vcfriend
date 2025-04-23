import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import EditIndividualForm from '../components/EditIndividualForm';

const IndividualPage = () => {
    const { id } = useParams();
    const [individual, setIndividual] = useState<any>(null);
    const [editing, setEditing] = useState(false);
    const [variants, setVariants] = useState<string[]>([]);

    // Fetch individual details
    useEffect(() => {
        const fetchData = async () => {
            const res = await axios.get(`http://localhost:8080/api/individuals/${id}`);
            const data = res.data;
            setIndividual({
                ...data,
                clinicalDiagnosis: data.clinicalDiagnosis,
                dateOfBirth: data.dateOfBirth,
            });
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

    // Save edit and refetch updated info
    const handleSave = async () => {
        const res = await axios.get(`http://localhost:8080/api/individuals/${id}`);
        setIndividual(res.data);
        setEditing(false);
    };

    if (!individual) return <p>Loading individual profile...</p>;

    return (
        <div className="p-6 max-w-3xl mx-auto border rounded shadow bg-white">
            <h2 className="text-2xl font-bold mb-4">{individual.name}</h2>

            {!editing ? (
                <>
                    <p><strong>Clinical Diagnosis:</strong> {individual.clinicalDiagnosis}</p>
                    <p><strong>Date of Birth:</strong> {individual.dateOfBirth}</p>
                    <p><strong>Proband:</strong> {individual.proband ? 'Yes' : 'No'}</p>
                    <button
                        onClick={() => setEditing(true)}
                        className="mt-4 px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
                    >
                        Edit
                    </button>
                </>
            ) : (
                <EditIndividualForm individual={individual} onSave={handleSave} />
            )}

            {/* VCF Variants Display */}
            <div className="mt-8">
                <h3 className="text-xl font-semibold mb-2">VCF Variants</h3>
                {variants.length === 0 ? (
                    <p>No variants found.</p>
                ) : (
                    <div className="bg-gray-100 p-3 rounded max-h-96 overflow-auto text-sm">
                        {variants.map((v, i) => (
                            <div key={i} className="border-b py-1">{v}</div>
                        ))}
                    </div>
                )}
            </div>
        </div>
    );
};

export default IndividualPage;
