import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import api from '../services/api';

const IndividualPage = () => {
    const { id } = useParams();
    const [individual, setIndividual] = useState(null);

    useEffect(() => {
        api.get(`/individuals/${id}`)
            .then(res => setIndividual(res.data))
            .catch(err => console.error('Error:', err));
    }, [id]);

    if (!individual) return <p>Loading...</p>;

    return (
        <div className="container mt-4">
            <h3>{individual.name}</h3>
            <p><strong>Sex:</strong> {individual.sexLabel}</p>
            <p><strong>DOB:</strong> {individual.dateOfBirth}</p>
            <p><strong>Diagnosis:</strong> {individual.clinicalDiagnosis}</p>
            <pre>{JSON.stringify(individual.info, null, 2)}</pre>
        </div>
    );
};

export default IndividualPage;
