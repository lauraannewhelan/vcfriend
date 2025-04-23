import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const IndividualPage = () => {
    const { id } = useParams();
    const [individual, setIndividual] = useState<any>(null);

    useEffect(() => {
        const fetchData = async () => {
            console.log("👀 Fetching individual with ID:", id);
            const res = await axios.get(`http://localhost:8080/api/individuals/${id}`);
            const data = res.data;
            setIndividual({
                ...data,
                clinicalDiagnosis: data.clinical_diagnosis,
                dateOfBirth: data.date_of_birth,
            });
        };
        fetchData();
    }, [id]);

    if (!individual) return <p>Loading individual profile...</p>;

    return (
        <div className="p-6 max-w-md mx-auto border rounded shadow">
            <h2 className="text-2xl font-bold mb-4">{individual.name}</h2>
            <p><strong>Clinical Diagnosis:</strong> {individual.clinicalDiagnosis}</p>
            <p><strong>Date of Birth:</strong> {individual.dateOfBirth}</p>
            <p><strong>Proband:</strong> {individual.proband ? 'Yes' : 'No'}</p>
        </div>
    );
};

export default IndividualPage;
