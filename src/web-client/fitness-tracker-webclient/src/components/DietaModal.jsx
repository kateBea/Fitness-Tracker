import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import moment from 'moment';
import Button from '@mui/material/Button';
import { useEffect } from 'react';
import { API_ROUTES } from '../ApiRoutes';
import axios from 'axios';
import { toast, ToastContainer } from 'react-toastify';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};

export default function DietaModal({ datosDieta, wantOpen, setWantOpen }) {
    console.log("want open: " + wantOpen)

    const [open, setOpen] = React.useState(wantOpen);
  const handleClose = () => {
    setOpen(false);
    setWantOpen(false)
  }

  // Axios setup
  const token = localStorage.getItem("token");
  axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;

  const activarDieta = async () => {
    try {
      const requestData = {
        id: datosDieta?.id,
        fechaInicio: datosDieta?.fechaInicio,
        fechaFin: datosDieta?.fechaFin,
        activa: !datosDieta?.activa
      };
      console.log("requestData", requestData)
      const response = await axios.put(API_ROUTES.ModificarDieta, requestData);

      console.log("addadadasdasda")
      
      toast.success(response.data.responseDescription); 

    } catch (error) {
      toast.error(error.message);
      console.log(error);
    }
  }
  
  return (
    <Modal
      open={open}
      onClose={handleClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box sx={style}>
        <Typography id="modal-modal-title" variant="h6" component="h2">
          Detalles de la dieta
        </Typography>
        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
          Tiempo de Sueño: {datosDieta.caloriasTarget} minutos
        </Typography>
        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
        Fecha de inicio: {moment(Date.parse(datosDieta?.fechaInicio)).format('LL')}
        </Typography>
        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
        Fecha de fin: {moment(Date.parse(datosDieta?.fechaFin)).format('LL')}
        </Typography>
        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
        Fecha de última modificación: {moment(Date.parse(datosDieta?.fechaUltimaModificacion)).format('LL')}
        </Typography>
        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
        Fecha de registro: {moment(Date.parse(datosDieta?.fechaRegistro)).format('LL')}
        </Typography>
        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
          Consumo de agua: {datosDieta.consumoDeAgua} litros
        </Typography>
        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
          Habilitada: {datosDieta.activa ? "Sí" : "No"}
        </Typography>
        <Button onClick={handleClose} sx={{ mt: 2 }}>Cerrar</Button>
        <Button onClick={activarDieta} sx={{ mt: 2 }}>Switch</Button>
      </Box>
    </Modal>
  );
}
