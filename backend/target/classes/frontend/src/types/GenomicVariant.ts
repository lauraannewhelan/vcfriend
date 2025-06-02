// src/types/GenomicVariant.ts

export interface GenomicVariant {
    id: string;
    chromosome: string;
    position: number;
    ref: string;
    alt: string;
    // Add any additional fields here that you need
    [key: string]: any;
}
