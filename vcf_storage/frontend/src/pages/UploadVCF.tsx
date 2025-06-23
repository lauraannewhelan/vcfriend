// src/pages/UploadData.tsx
import React, { useState } from 'react';

// Explicitly define event type as a file input event
const UploadVCF = () => {
    const [file, setFile] = useState<File | null>(null);
    const [message, setMessage] = useState<string>('');

    const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const selectedFile = event.target.files ? event.target.files[0] : null;
        setFile(selectedFile);
    };

    const handleUpload = () => {
        if (file) {
            setMessage(`File selected: ${file.name}`);
            // Logic to handle file upload goes here
        }
    };

    return (
        <div>
            <input
                type="file"
                onChange={handleFileChange}
                className="mb-2"
            />
            <button onClick={handleUpload}>Upload VCF</button>
            {message && <p>{message}</p>}
        </div>
    );
};

export default UploadVCF;
