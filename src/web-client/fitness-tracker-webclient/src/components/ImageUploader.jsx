import React, { useState } from 'react';
import { Button, Box } from '@mui/material';

const ImageUploader = ({ onImageUpload }) => {
    const [image, setImage] = useState(null);
    const [imagePreviewUrl, setImagePreviewUrl] = useState('');

    const handleImageChange = (event) => {
        const file = event.target.files[0];
        if (file && file.type.startsWith('image/')) {
            setImage(file);
            const reader = new FileReader();
            reader.onloadend = () => {

                setImagePreviewUrl(reader.result);
            };
            reader.readAsDataURL(file);
            onImageUpload(file);
        } else {
            alert('Por favor, seleccione un archivo de imagen.');
        }
    };

    return (
        <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', gap: 2 }}>
            <input
                accept="image/*"
                style={{ display: 'none' }}
                id="raised-button-file"
                type="file"
                onChange={handleImageChange}
            />
            <label htmlFor="raised-button-file">
                <Button variant="contained" component="span">
                    Cargar Imagen
                </Button>
            </label>
            {imagePreviewUrl && (
                <Box mt={2}>
                    <img src={imagePreviewUrl} alt="Preview" style={{ maxWidth: '100%', maxHeight: '300px' }} />
                </Box>
            )}
        </Box>
    );
};

export default ImageUploader;