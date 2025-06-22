import React, { useEffect, useState } from 'react';
import axios from '../api/axios';
import { useParams } from 'react-router-dom';
import EditIndividualForm from '../components/EditIndividualForm';
import './Individual.css';

const IndividualPage = () => {
    const { id } = useParams();
    const [individual, setIndividual] = useState<any>(null);
    const [editing, setEditing] = useState(false);
    const [dbVariants, setDbVariants] = useState<any[]>([]);

    // Fetch individual details
    useEffect(() => {
        const fetchData = async () => {
            const res = await axios.get(`http://localhost:8080/api/individuals/${id}`);
            setIndividual(res.data);
        };
        fetchData();
    }, [id]);

    // Fetch variants from database
    useEffect(() => {
        const fetchDbVariants = async () => {
            try {
                const res = await axios.get(`http://localhost:8080/api/individuals/${id}/variants`);
                setDbVariants(res.data);
            } catch (error) {
                console.error("❌ Failed to fetch DB variants", error);
            }
        };
        if (id) fetchDbVariants();
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

            {/* Database Variants */}
            <div className="variants-section">
                <h3>Annotated Genomic Variants</h3>
                {dbVariants.length === 0 ? (
                    <p>No variants found.</p>
                ) : (
                    <table className="variant-table">
                        <thead>
                        <tr>
                            <th>Chr</th>
                            <th>Start</th>
                            <th>Ref</th>
                            <th>Alt</th>
                            <th>Gene</th>
                            <th>Func</th>
                            <th>gnomAD AF</th>
                        </tr>
                        </thead>
                        <tbody>
                        {dbVariants.map((v: any) => (
                            <tr key={v.id}>
                                <td>{v.chr}</td>
                                <td>{v.start}</td>
                                <td>{v.ref}</td>
                                <td>{v.alt}</td>
                                <td>{v.geneRefgene}</td>
                                <td>{v.funcRefgene}</td>
                                <td>{v.gnomad40GenomeAf}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default IndividualPage;
