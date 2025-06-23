import React, { useState } from 'react';
import axios from '../api/axios';
import './UploadData.css';

const UploadData = () => {
    const [pedigree, setPedigree] = useState({
        pedigreeId: '',
        clinicalDiagnosis: '',
        members: '',
    });

    const [individual, setIndividual] = useState({
        studyId: '',
        clinicalDiagnosis: '',
        dateOfBirth: '',
        proband: false,
        sexLabel: '',
    });

    const [csvFile, setCsvFile] = useState<File | null>(null);
    const [message, setMessage] = useState('');
    const [uploading, setUploading] = useState(false);

    const handlePedigreeChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPedigree({ ...pedigree, [e.target.name]: e.target.value });
    };

    const handleIndividualChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const value = e.target.type === 'checkbox'
            ? (e.target as HTMLInputElement).checked
            : e.target.value;

        setIndividual({ ...individual, [e.target.name]: value });
    };

    const handleCsvChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const file = e.target.files?.[0] ?? null;
        if (file && file.name.endsWith('.csv')) {
            setCsvFile(file);
        } else {
            alert('❗ Please upload a .csv file only.');
        }
    };

    const handleSubmit = async () => {
        if (!csvFile) {
            setMessage('❗ Please select a CSV file.');
            return;
        }

        try {
            setUploading(true);
            setMessage('');

            // 1. Submit pedigree
            await axios.post('/api/pedigrees', {
                pedigree_id: pedigree.pedigreeId,
                clinical_diagnosis: pedigree.clinicalDiagnosis,
                members: pedigree.members,
            }).catch((error) => {
                if (error.response?.status === 409) {
                    console.warn('⚠️ Pedigree already exists. Continuing...');
                } else {
                    throw error;
                }
            });

            // 2. Submit individual
            await axios.post('/api/individuals', {
                studyId: individual.studyId,
                clinicalDiagnosis: individual.clinicalDiagnosis,
                dateOfBirth: individual.dateOfBirth,
                proband: individual.proband,
                sexLabel: individual.sexLabel,
                pedigreeId: pedigree.pedigreeId,
            }).catch((error) => {
                if (
                    error.response?.status === 400 &&
                    error.response?.data?.message?.includes('already exists')
                ) {
                    setMessage(`⚠️ Individual with Study ID '${individual.studyId}' already exists.`);
                    return;
                }
                throw error;
            });

            // 3. Upload variant CSV
            const formData = new FormData();
            formData.append('csv', csvFile);
            await axios.post('/api/individuals/upload-csv', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });

            setMessage('✅ Data submitted successfully!');
        } catch (error: any) {
            console.error('Upload error:', error);
            setMessage(`❌ Error: ${error.response?.data?.message || 'Unexpected server error'}`);
        } finally {
            setUploading(false);
        }
    };

    return (
        <div className="variant-container">
            <div className="variant-card">
                <h2>Upload Individual & Pedigree Data</h2>

                <h4>👤 Individual Info</h4>
                <input name="studyId" placeholder="Study ID" onChange={handleIndividualChange} />
                <input name="clinicalDiagnosis" placeholder="Clinical Diagnosis" onChange={handleIndividualChange} />
                <input name="dateOfBirth" type="date" onChange={handleIndividualChange} />
                <select name="sexLabel" onChange={handleIndividualChange}>
                    <option value="">Select Sex</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Unknown">Unknown</option>
                </select>
                <div className="proband-checkbox">
                    <input type="checkbox" id="proband" name="proband" onChange={handleIndividualChange} />
                    <label htmlFor="proband">Proband</label>
                </div>

                <h4 className="mt-4">🧬 Pedigree Info</h4>
                <input name="pedigreeId" placeholder="Pedigree ID" onChange={handlePedigreeChange} />
                <input name="clinicalDiagnosis" placeholder="Clinical Diagnosis" onChange={handlePedigreeChange} />
                <input name="members" placeholder="Members (comma-separated)" onChange={handlePedigreeChange} />

                <h4 className="mt-4">📄 Upload Variant CSV</h4>
                <input type="file" accept=".csv" onChange={handleCsvChange} />

                <button onClick={handleSubmit} disabled={uploading}>
                    {uploading ? 'Uploading...' : 'Submit All'}
                </button>

                {message && <p className={message.startsWith('❌') ? 'variant-error' : 'variant-help'}>{message}</p>}
            </div>
        </div>
    );
};

export default UploadData;
