import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import api from '../services/api';



const VariantPage = () => {
    const { vcfFileId } = useParams();
    const [variants, setVariants] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        api.get(`/variants/by-vcf/${vcfFileId}`).then((res) => {
            setVariants(res.data);
            setLoading(false);
        });
    }, [vcfFileId]);

    if (loading) return <div className="container mt-5">Loading variants...</div>;
    if (!variants.length) return <div className="container mt-5">No variants found for this VCF file.</div>;

    return (
        <div className="container mt-5">
            <h2>Variants in VCF File #{vcfFileId}</h2>

            <table className="table table-striped mt-4">
                <thead>
                <tr>
                    <th>Chr</th>
                    <th>Pos</th>
                    <th>Ref</th>
                    <th>Alt</th>
                    <th>CLNSIG</th>
                    <th>GENEINFO</th>
                    <th>MC</th>
                    <th>HGVS</th>
                </tr>
                </thead>
                <tbody>
                {variants.map((variant) => {
                    const info = variant.info || {};
                    const mc = Array.isArray(info.MC) ? info.MC.join('; ') : info.MC;
                    const sig = Array.isArray(info.CLNSIG) ? info.CLNSIG.join('; ') : info.CLNSIG;
                    const gene = Array.isArray(info.GENEINFO) ? info.GENEINFO.join('; ') : info.GENEINFO;
                    const hgvs = info.CLNHGVS;

                    return (
                        <tr key={variant.id}>
                            <td>{variant.chrom}</td>
                            <td>{variant.pos}</td>
                            <td>{variant.ref}</td>
                            <td>{variant.alt}</td>
                            <td>{sig || '–'}</td>
                            <td>{gene || '–'}</td>
                            <td>{mc || '–'}</td>
                            <td>{hgvs || '–'}</td>
                        </tr>
                    );
                })}
                </tbody>
            </table>

            <Link to="/families" className="btn btn-secondary mt-3">← Back to Families</Link>
        </div>
    );
};

export default VariantPage;
