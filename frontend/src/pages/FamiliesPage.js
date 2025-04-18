// src/pages/FamiliesPage.js
import React, { useEffect, useState } from 'react';
import api from '../services/api';
import { Link } from 'react-router-dom';



const FamiliesPage = () => {
    const [families, setFamilies] = useState([]);

    useEffect(() => {
        api.get('/families').then((res) => setFamilies(res.data));
    }, []);

    return (
        <div className="container mt-5">
            <h1>Families</h1>
            <ul className="list-group">
                {families.map((family) => (
                    <li key={family.id} className="list-group-item">
                        <h5>{family.familyName}</h5>
                        <ul>
                            {family.individuals.map((ind) => (
                                <li key={ind.id}>
                                    <Link to={`/individuals/${ind.id}`}>{ind.name}</Link>
                                </li>
                            ))}
                        </ul>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default FamiliesPage;
