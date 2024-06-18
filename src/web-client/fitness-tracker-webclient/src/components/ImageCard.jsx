import * as React from 'react';
import axios from "axios";
import { API_ROUTES } from "../ApiRoutes.jsx";
import { useState, useEffect } from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import moment from 'moment';

import Logo from "../img/comida-prueba.webp";
import { CardActionArea } from '@mui/material';

export default function ImageCard({ fechaInicio, fechaFin, caloriasTarget, datos}) {
  const [dataFecthSuccess, setDataFecthSuccess] = useState(false);
  const [comida,setComida] = useState("");
  const [existeImagen, setExisteImagen] = useState(false);

  const loadComida = async () => {
    try {
      for (let i = 0; i < datos.length; ++i) {
        const response = await axios.get(API_ROUTES.GetComida, {params: {prompt: datos[i].nombre}});
        setDataFecthSuccess(true);
        
        if (response.data.result.parsed.length != 0) {
          setComida(response.data.result.parsed[0].wrapper.image)
          setExisteImagen(true)
          break;
        }
      }

    } catch (error) {
      console.log(error);
    }
  };
  
  useEffect(() => {
    loadComida();
  }, [dataFecthSuccess]);

  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardActionArea>
        <CardMedia
          component="img"
          alt="Dieta Image"
          height="140"
          image={!existeImagen ? Logo : comida }
        />
        <CardContent>
          <Typography gutterBottom variant="h5" style={{ fontWeight: "bold" }} component="div">
            Dieta
          </Typography>
          <Typography variant="body2" color="text.secondary">
            Fecha inicio: {moment(Date.parse(fechaInicio)).format('LL')}
          </Typography>
          <Typography variant="body2" color="text.secondary">
            Fecha fin: {moment(Date.parse(fechaFin)).format('LL')}
          </Typography>
          <Typography variant="body2" color="text.secondary">
            Objetivo en calorias: {caloriasTarget}
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions>
        <Button size="small">Visualizar</Button>
      </CardActions>
    </Card>
  );
}
