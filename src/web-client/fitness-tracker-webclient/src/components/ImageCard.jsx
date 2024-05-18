import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';

import Logo from "../img/logo-fitness-tracker.png"
import { CardActionArea } from '@mui/material';

export default function ImageCard(dietaName) {
  return (
    <Card sx={{ maxWidth: 345}}>
      <CardActionArea>
      <CardMedia
        component="img"
        alt="Dieta Image"
        height="140"
        image={Logo}
      />
      <CardContent>
          <Typography gutterBottom variant="h5" style={{fontWeight: "bold"}} component="div">
            Dietas
          </Typography>
          <Typography variant="body2" color="text.secondary">
            Lizards are a widespread group of squamate reptiles, with over 6,000
            species, ranging across all continents except Antarctica
          </Typography>
        </CardContent>

        </CardActionArea>
        <CardActions>
          <Button size="small">Visiualizar</Button>
          <Button size="small">Modificar</Button>
        </CardActions>
    </Card>
  );
}
