// src/pages/Shortlist.tsx
import React, { useState } from 'react';
import { GenomicVariant } from '../types/GenomicVariant';  // Corrected import

const Shortlist = () => {
    const [shortlistedVariants, setShortlistedVariants] = useState<GenomicVariant[]>([]);

    const addToShortlist = (variant: GenomicVariant) => {
        setShortlistedVariants((prevList) => [...prevList, variant]);
    };

    const removeFromShortlist = (variant: GenomicVariant) => {
        setShortlistedVariants((prevList) =>
            prevList.filter((item) => item.id !== variant.id)
        );
    };

    return (
        <div>
            <h2>Shortlist</h2>
            <ul>
                {shortlistedVariants.map((variant) => (
                    <li key={variant.id}>
                        {variant.id} - {variant.chromosome}
                        <button onClick={() => removeFromShortlist(variant)}>Remove</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default Shortlist;
