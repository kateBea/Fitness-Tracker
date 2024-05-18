import React from "react";
import { Card, CardContent, Typography, Grid, Box, CardActionArea } from "@mui/material";

const RutinaCard = ({ rutina }) => (
    <CardActionArea>

  <Card sx={{ height: '100%', borderRadius: 2, boxShadow: 3 }}>
    <CardContent>
      <Typography variant="h6" component="div">
        Rutina ID: {rutina._id}
      </Typography>
      <Typography variant="body2" color="text.secondary">
        Fecha Seguimiento: {new Date(rutina.fecha_seguimiento).toLocaleDateString()}
      </Typography>
      <Box sx={{ mt: 2 }}>
        <Typography variant="body2">
          Tiempo de Sueño: {rutina.tiempo_suenio} mins
        </Typography>
        <Typography variant="body2">
          Calorías Quemadas: {rutina.calorias_quemadas} kcal
        </Typography>
        <Typography variant="body2">
          Pasos Realizados: {rutina.pasos_realizados}
        </Typography>
        <Typography variant="body2">
          Frecuencia Cardiaca: {rutina.frecuencia_cardiaca} bpm
        </Typography>
        <Typography variant="body2">
          Nivel de Oxígeno en Sangre: {rutina.nivel_oxigeno_sangre}%
        </Typography>
        <Typography variant="body2">
          Presión Arterial: {rutina.presion_arterial} mmHg
        </Typography>
      </Box>
    </CardContent>
  </Card>
    </CardActionArea>
);

export default RutinaCard;
