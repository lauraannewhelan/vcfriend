// src/components/VariantTable.tsx
import { useEffect, useState } from 'react';
import { getVariants } from '../api/getVariants'; // Import the getVariants function

interface Variant {
    id: string;
    chromosome: string;
    position: number;
    ref: string;
    alt: string;
}

const VariantTable = () => {
    const [variants, setVariants] = useState<Variant[]>([]);
    const [filter, setFilter] = useState<string>('');

    useEffect(() => {
        const fetchVariants = async () => {
            try {
                const data = await getVariants(); // Fetch variants using getVariants function
                setVariants(data); // Update state with the fetched variants
            } catch (error) {
                console.error("Error fetching variants:", error);
            }
        };

        fetchVariants();
    }, []); // Empty dependency array ensures this runs once when the component mounts

    const handleFilterChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFilter(event.target.value);
    };

    const filteredVariants = variants.filter((variant) =>
        variant.id.toLowerCase().includes(filter.toLowerCase())
    );

    return (
        <div>
            <input
                type="text"
                placeholder="Filter variants"
                value={filter}
                onChange={handleFilterChange}
            />
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Chromosome</th>
                    <th>Position</th>
                    <th>Reference</th>
                    <th>Alternate</th>
                </tr>
                </thead>
                <tbody>
                {filteredVariants.map((variant) => (
                    <tr key={variant.id}>
                        <td>{variant.id}</td>
                        <td>{variant.chromosome}</td>
                        <td>{variant.position}</td>
                        <td>{variant.ref}</td>
                        <td>{variant.alt}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default VariantTable;
