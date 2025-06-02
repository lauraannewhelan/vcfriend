import { useState } from "react";
import { uploadVCF } from "../api/uploadVCF";
import { Button } from 'react-bootstrap'; // Import Button from react-bootstrap


export default function FileUpload() {
    const [file, setFile] = useState<File | null>(null);
    const [status, setStatus] = useState("");

    const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const selectedFile = event.target.files?.[0];
        setFile(selectedFile || null);
    };

    const handleUpload = async () => {
        if (!file) return;

        try {
            setStatus("Uploading...");
            await uploadVCF(file);
            setStatus("Upload Successful!");
        } catch (err) {
            setStatus("Upload failed. Please try again.");
        }
    };

    return (
        <div className="p-4 border rounded-xl shadow-md w-full max-w-md mx-auto">
            <input
                type="file"
                onChange={handleFileChange}
                className="mb-2"
            />
            <Button onClick={handleUpload}>Upload VCF</Button>
            {status && <p className="mt-2 text-sm">{status}</p>}
        </div>
    );
}
