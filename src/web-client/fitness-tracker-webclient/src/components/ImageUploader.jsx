import React, { useState } from 'react';
import { Button, Box } from '@mui/material';

// Componente funcional ImageUploader
const ImageUploader = ({ onImageUpload }) => {
    // Estados para manejar la imagen y la URL de vista previa de la imagen
    const [image, setImage] = useState(null);
    const [imagePreviewUrl, setImagePreviewUrl] = useState('');

    // Función para manejar el cambio de la imagen seleccionada
    const handleImageChange = (event) => {
        const file = event.target.files[0]; // Obtener el archivo seleccionado desde el evento
    
        // Verificar si se seleccionó un archivo de tipo imagen y que su tamaño sea menor a 10 MB
        if (file && file.type.startsWith('image/') && file.size <= 10 * 1024 * 1024) {
            setImage(file); // Establecer el archivo en el estado 'image'
    
            const reader = new FileReader(); // Crear un lector de archivos
            reader.onloadend = () => {
                const base64String = reader.result; // Obtener el resultado como cadena base64
                setImagePreviewUrl(base64String); // Establecer la URL de vista previa de la imagen
                onImageUpload(base64String); // Llamar a la función 'onImageUpload' con la cadena base64
            };
            reader.readAsDataURL(file); // Leer el archivo como una URL de datos
            // Llamar a la función 'onImageUpload' con el archivo completo (opcional)
            // onImageUpload(file); 
    
        } else {
            // Si el archivo no cumple con las condiciones de tipo imagen o tamaño menor a 10 MB
            if (!file) {
                alert('Por favor, seleccione un archivo de imagen.'); // Alerta si no se selecciona ningún archivo
            } else if (file.size > 10 * 1024 * 1024) {
                alert('El tamaño del archivo debe ser menor a 10 MB.'); // Alerta si el archivo excede los 10 MB
            } else {
                alert('Por favor, seleccione un archivo de imagen válido.'); // Alerta si no es un archivo de imagen válido
            }
        }
    };
    // Renderización del componente
    return (
        <div>
            {/* Input oculto para seleccionar un archivo */}
            <input
                accept="image/*" // Aceptar solo archivos de tipo imagen
                style={{ display: 'none' }} // Ocultar el input
                id="raised-button-file" // ID del input
                type="file" // Tipo de input
                onChange={handleImageChange} // Manejar el cambio de la imagen seleccionada
            />
            {/* Etiqueta para el input de archivo */}
            <label htmlFor="raised-button-file">
                {/* Botón para cargar la imagen */}
                <Button variant="contained" component="span">
                    Cargar Imagen
                </Button>
            </label>
            {/* Mostrar la vista previa de la imagen si existe */}
            {imagePreviewUrl && (
                <div>
                    {/* Imagen con la vista previa */}
                    <img src={imagePreviewUrl} alt="Preview" style={{ maxWidth: '100%', maxHeight: '300px',marginTop:'30px' }} />
                </div>
            )}
        </div>
    );
};

// Exportar el componente ImageUploader
export default ImageUploader;
