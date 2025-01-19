import React from "react";
import { Card, CardContent, Typography, Box, CardActionArea } from "@mui/material";
import moment from "moment";

const RutinaCard = ({ rutina, onModalOpen }) => (
  <CardActionArea onClick={onModalOpen}>
    <Card sx={{ height: '100%', borderRadius: 2, boxShadow: 3 }}>
      <CardContent>
        <Typography variant="h6" component="div">
          Rutina del {moment(Date.parse(rutina?.fechaSeguimiento)).format('LL')}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          Fecha Seguimiento: {moment(Date.parse(rutina?.fechaSeguimiento)).format('LL')}
        </Typography>
        <Box sx={{ mt: 2 }}>
          <Typography variant="body2">
            Tiempo de Sueño: {rutina.tiempoDeSuenio} minutos
          </Typography>
          <Typography variant="body2">
            Calorías Quemadas: {rutina.caloriasQuemadas} Kcal
          </Typography>
          <Typography variant="body2">
            Pasos Realizados: {rutina.pasosRealizados}
          </Typography>
          <Typography variant="body2">
            Frecuencia Cardiaca: {rutina.frecuenciaCardiaca} BPM
          </Typography>
          <Typography variant="body2">
            Nivel de Oxígeno en Sangre: {rutina.nivelOxigenoSangre} %
          </Typography>
        </Box>
      </CardContent>
    </Card>
  </CardActionArea>
);

export default RutinaCard;
