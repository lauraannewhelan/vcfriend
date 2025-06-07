import React, { useState } from 'react';
import axios from 'axios';

interface Individual {
    id: number;
    name: string;
    clinicalDiagnosis: string;
    dateOfBirth: string;
    proband: boolean;
}

interface Props {
    individual: Individual;
    onSave: () => void;
}

const EditIndividualForm: React.FC<Props> = ({ individual, onSave }) => {
    const [formData, setFormData] = useState({ ...individual });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value, type, checked } = e.target;
        setFormData({
            ...formData,
            [name]: type === 'checkbox' ? checked : value,
        });
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            await axios.put(`http://localhost:8080/api/individuals/${individual.id}`, formData);
            onSave(); // call parent to refresh
        } catch (error) {
            console.error('Failed to update individual:', error);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="space-y-4 bg-white p-6 rounded shadow">
            <div>
                <label>Name:</label>
                <input name="name" value={formData.name} onChange={handleChange} className="border p-2 w-full" />
            </div>
            <div>
                <label>Diagnosis:</label>
                <input name="clinicalDiagnosis" value={formData.clinicalDiagnosis} onChange={handleChange} className="border p-2 w-full" />
            </div>
            <div>
                <label>Date of Birth:</label>
                <input type="date" name="dateOfBirth" value={formData.dateOfBirth} onChange={handleChange} className="border p-2 w-full" />
            </div>
            <div>
                <label>
                    <input type="checkbox" name="proband" checked={formData.proband} onChange={handleChange} />
                    {' '}Proband
                </label>
            </div>
            <button type="submit" className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">Save</button>
        </form>
    );
};

export default EditIndividualForm;
