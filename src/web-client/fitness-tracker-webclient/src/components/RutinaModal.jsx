import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import moment from 'moment';
import Button from '@mui/material/Button';
import { useEffect } from 'react';

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

export default function RutinaModal({ rutina, wantOpen, setWantOpen }) {
    console.log("want open: " + wantOpen)

    const [open, setOpen] = React.useState(wantOpen);
  const handleClose = () => {
    setOpen(false);
    setWantOpen(false)
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
          Detalles de la Rutina
        </Typography>
        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
          Fecha Seguimiento: {moment(Date.parse(rutina?.fechaSeguimiento)).format('LL')}
        </Typography>
        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
          Tiempo de Sueño: {rutina.tiempoDeSuenio} minutos
        </Typography>
        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
          Calorías Quemadas: {rutina.caloriasQuemadas} Kcal
        </Typography>
        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
          Pasos Realizados: {rutina.pasosRealizados}
        </Typography>
        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
          Frecuencia Cardiaca: {rutina.frecuenciaCardiaca} BPM
        </Typography>
        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
          Nivel de Oxígeno en Sangre: {rutina.nivelOxigenoSangre} %
        </Typography>
        <Button onClick={handleClose} sx={{ mt: 2 }}>Cerrar</Button>
      </Box>
    </Modal>
  );
}
