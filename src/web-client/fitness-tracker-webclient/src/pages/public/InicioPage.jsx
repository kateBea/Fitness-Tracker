import * as React from 'react';
import {
  Box,
  AppBar,
  Container,
  Typography,
  MenuItem,
  Button,
} from '@mui/material';

import LogoFitness from '../../img/logo-fitness-tracker-oscuro.png'
import BackgroundPuntos from '../../img/background-puntos.png'

function MainPage() {
  return (
    <Box>
      <AppBar
        position="static"
        sx={{
          background: 'blue',
          boxShadow: 'none'

        }}
      >
        <Container
          maxWidth='false'
          sx={{
            display: 'flex',
            alignContent: 'space-between',
            width: '100%',
            maxWidth: '100%',
            height: '70px'
          }}
        >
          <Box
            sx={{
              display: 'flex',
              alignItems: 'center',
              width: '50%'
            }}

          >
            <img src={LogoFitness}
              title="Logo Fitness-Tracker"
              alt="Logo Fitness-Tracker App"
              style={{
                width: '55px',
                height: '55px',
                position: 'block',
              }}
            />
            <Typography
              variant="h3"
              component="h1"
              href="/"
              sx={{
                color: '#436489',
                textDecoration: 'none',
                margin: '15px'
              }}
            >
              Fitness-Tracker
            </Typography>
          </Box>
          <Box
            sx={{
              display: 'flex',
              justifyContent: 'flex-end',
              alignContent: 'right',
              alignItems: 'center',
              width: '50%',
              color: '#436489',
              fontWeight: 'bold'
            }}
          >
            <MenuItem sx={{ fontWeight: 'bold' }} >Home</MenuItem>
            <MenuItem sx={{ fontWeight: 'bold' }} >App</MenuItem>
            <MenuItem sx={{ fontWeight: 'bold' }} >Sobre Nosotros</MenuItem>
            <MenuItem sx={{ fontWeight: 'bold' }} >Nuestros Clientes</MenuItem>
            <Button sx={{
              background: '#436489',
              fontWeight: 'bold',
              color: 'white',
              borderRadius: '20px',
              paddingLeft: '10px',
              paddingRight: '10px'
            }}>Register</Button>
          </Box>
        </Container>
      </AppBar>
      
    </Box>
  );
}

export default MainPage