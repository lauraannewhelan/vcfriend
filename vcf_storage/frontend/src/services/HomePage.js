import React, { useEffect, useState } from 'react';
import api from '../services/api';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';


const HomePage = () => {
    const [individuals, setIndividuals] = useState([]);

    useEffect(() => {
        api.get('/individuals')
            .then(res => setIndividuals(res.data))
            .catch(err => console.error('Error fetching individuals:', err));
    }, []);

    return (
        <div className="container mt-4">
            <h2>Beacon Individuals</h2>
            <ul className="list-group">
                {individuals.map(ind => (
                    <li className="list-group-item d-flex justify-content-between" key={ind.id}>
                        <div>
                            <strong>{ind.name}</strong> <br />
                            {ind.sexLabel} | {ind.dateOfBirth}
                        </div>
                        <Link to={`/individuals/${ind.id}`} className="btn btn-sm btn-outline-primary">View</Link>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default HomePage;
