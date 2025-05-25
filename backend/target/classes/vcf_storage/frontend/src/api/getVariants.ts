// src/api/getVariants.js
import axios from 'axios';

export const getVariants = async () => {
    try {
        const response = await axios.get('http://localhost:8080/api/variants');
        if (response && response.data) {
            return response.data;  // The response should be an array of GenomicVariant
        } else {
            console.error('No data returned from backend');
            return [];
        }
    } catch (error) {
        console.error('Error fetching variants:', error);
        return [];
    }
};
