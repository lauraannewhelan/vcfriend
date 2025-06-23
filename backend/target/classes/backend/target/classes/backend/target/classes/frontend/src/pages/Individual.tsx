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
    const [expandedRow, setExpandedRow] = useState<number | null>(null);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        const fetchData = async () => {
            const res = await axios.get(`/api/individuals/${id}`);
            setIndividual(res.data);
        };
        fetchData();
    }, [id]);

    useEffect(() => {
        const fetchDbVariants = async () => {
            try {
                const res = await axios.get(`/api/individuals/${id}/variants`);
                setDbVariants(res.data);
            } catch (error) {
                console.error("❌ Failed to fetch DB variants", error);
            }
        };
        if (id) fetchDbVariants();
    }, [id]);

    const handleSave = async () => {
        const res = await axios.get(`/api/individuals/${id}`);
        setIndividual(res.data);
        setEditing(false);
    };

    const toggleRow = (rowIndex: number) => {
        setExpandedRow(expandedRow === rowIndex ? null : rowIndex);
    };

    const filteredVariants = dbVariants.filter((v) => {
        const search = searchTerm.toLowerCase();
        return (
            v.aachangeRefgene?.toLowerCase().includes(search) ||
            v.geneRefgene?.toLowerCase().includes(search) ||
            v.variant?.toLowerCase().includes(search)
        );
    });

    const formatAAChange = (raw: string | null) => {
        if (!raw) return '-';
        const trimmed = raw.split(',')[0].trim();
        return trimmed;
    };

    if (!individual) return <p>Loading individual profile...</p>;

    return (
        <div className="individual-container">
            <h2>Participant ID: {individual.id}</h2>

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

            <div className="variants-section">
                <h3>Annotated Genomic Variants</h3>

                <input
                    type="text"
                    placeholder="Search by Gene, AA Change, or Variant"
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                    className="variant-search"
                />

                {filteredVariants.length === 0 ? (
                    <p>No variants found.</p>
                ) : (
                    <table className="variant-table">
                        <thead>
                        <tr>
                            <th>Gene</th>
                            <th>AA Change</th>
                            <th>Variant</th>
                        </tr>
                        </thead>
                        <tbody>
                        {filteredVariants.map((v, i) => (
                            <React.Fragment key={v.id}>
                                <tr className="clickable-row" onClick={() => toggleRow(i)}>
                                    <td>{v.geneRefgene || '-'}</td>
                                    <td>{formatAAChange(v.aachangeRefgene)}</td>
                                    <td>{v.variant || '-'}</td>
                                </tr>
                                {expandedRow === i && (
                                    <tr className="expanded-row">
                                        <td colSpan={3}>
                                            <div className="expanded-content">
                                                <p><strong>Chr:</strong> {v.chr}</p>
                                                <p><strong>Start:</strong> {v.start}</p>
                                                <p><strong>Ref:</strong> {v.ref}</p>
                                                <p><strong>Alt:</strong> {v.alt}</p>
                                                <p><strong>Function:</strong> {v.funcRefgene}</p>
                                                <p><strong>gnomAD AF:</strong> {v.gnomad40GenomeAf}</p>
                                                <p><strong>REVEL:</strong> {v.revel}</p>
                                                <p><strong>OMIM:</strong> {v.omim}</p>
                                            </div>
                                        </td>
                                    </tr>
                                )}
                            </React.Fragment>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default IndividualPage;
