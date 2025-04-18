// src/pages/IndividualPage.jsx
import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import api from '../services/api';
import ListGroup from 'react-bootstrap/ListGroup';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Card from 'react-bootstrap/Card';



const IndividualPage = () => {
    const { individualId } = useParams();
    const [individual, setIndividual] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        console.log("🔍 Fetching individual with ID:", individualId);

        if (!individualId) {
            console.error("❌ individualId is undefined!");
            return;
        }

        api.get(`/individuals/${individualId}`)
            .then(res => {
                setIndividual(res.data);
                console.log("✅ Individual data loaded:", res.data);
            })
            .catch(err => {
                console.error("❌ Failed to fetch individual:", err);
                setError(err.message);
            });
    }, [individualId]);

    if (error) {
        return <Container><h4>Error: {error}</h4></Container>;
    }

    if (!individual) {
        return <Container><h4>Loading...</h4></Container>;
    }

    return (
        <Container className="mt-4">
            <Card>
                <Card.Header as="h3">{individual.name}</Card.Header>
                <Card.Body>
                    <Card.Text>
                        <strong>Sex:</strong> {individual.sex}<br />
                        <strong>DOB:</strong> {individual.dateOfBirth}<br />
                        <strong>Clinical Diagnosis:</strong> {individual.clinicalDiagnosis}
                    </Card.Text>
                </Card.Body>
            </Card>

            <h4 className="mt-4">Samples</h4>
            <Row>
                {individual.samples.map(sample => (
                    <Col key={sample.id} md={6}>
                        <Card className="mb-3">
                            <Card.Header>{sample.sampleLabel}</Card.Header>
                            <Card.Body>
                                <Card.Text>
                                    <strong>Tissue Type:</strong> {sample.tissueType}
                                </Card.Text>

                                <h5>VCF Files</h5>
                                <ListGroup>
                                    {sample.vcfFiles.map(vcf => (
                                        <ListGroup.Item key={vcf.id}>
                                            <strong>{vcf.filename}</strong><br />
                                            <span>Uploaded: {vcf.uploadDate}</span><br />
                                            <Link to={`/vcf/${vcf.id}/variants`} className="btn btn-sm btn-outline-primary mt-2">
                                                View Variants
                                            </Link>
                                        </ListGroup.Item>
                                    ))}
                                </ListGroup>

                            </Card.Body>
                        </Card>
                    </Col>
                ))}
            </Row>

            <div className="mt-3">
                <Link to="/families" className="btn btn-secondary">← Back to Families</Link>
            </div>
        </Container>
    );
};

export default IndividualPage;
