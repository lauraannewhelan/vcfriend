// src/api/uploadVCF.ts
import axiosInstance from "./axios";

export const uploadVCF = async (file: File) => {
    const formData = new FormData();
    formData.append("file", file);

    try {
        const response = await axiosInstance.post("/vcf/upload", formData, {
            headers: {
                "Content-Type": "multipart/form-data",
            },
        });
        return response.data;  // Handle success response
    } catch (error) {
        console.error("Error uploading VCF:", error);
        throw error;  // Rethrow error to be handled by the calling component
    }
};
