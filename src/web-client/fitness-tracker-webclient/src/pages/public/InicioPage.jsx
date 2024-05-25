import * as React from 'react';
import {
  Box,
  AppBar,
  Container,
  Typography,
  MenuItem,
  Button,
  Grid
} from '@mui/material';

import LogoFitness from '../../img/logo-fitness-tracker-oscuro.png'
import BackgroundPuntos from '../../img/background-puntos.png'
import RelojInicio from '../../img/reloj-inicio.png'
import BarraSeparacion from "../../img/barra-separacion.png"

function MainPage() {
  return (
    <Box>
      <AppBar
        position="static"
        sx={{
          background: 'white',
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
      <Grid container columnSpacing={2}>
        <Grid item lg={6} xs={12}>
          <Box sx={{ position: 'relative', marginTop: '40px' }}>
            <img src={BackgroundPuntos} style={{ position: 'absolute', width: '120px', left: '-40px' }}></img>
          </Box>
          <Typography
            variant="h3"
            component="h2"
            href="/"
            sx={{ marginTop: '150px', marginLeft: '200px', fontWeight: 'bold' }}
          >Virtualiza tu cuidado personal
          </Typography>
          <Typography
            variant="p"
            component="p"
            href="/"
            sx={{ marginTop: '20px', color: '#7D7987', marginLeft: '200px', maxWidth: '45%' }}>Fitness-Tracker provee de un seguimiento personal
            y de salud para tu beneficio personal en base a tus
            objetivos y metas</Typography>
          <Container sx={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
            <Button sx={{
              background: '#436489',
              fontWeight: 'bold',
              color: 'white',
              borderRadius: '20px',
              right: ''
            }}>Consigue nuestra app</Button>
          </Container>
        </Grid>
        <Grid item lg={6} xs={12}>
          <Box sx={{ display: 'flex', justifyContent: 'center', marginTop: '50px', width: '100%' }}>
            <img src={RelojInicio}></img>
          </Box>
        </Grid>
      </Grid>
      <Box sx={{ display: 'flex', justifyContent: 'center' }}>
        <Grid container>
          <Grid item xs={12}>
            <Box sx={{ display: 'flex', justifyContent: 'center',marginTop:'100px' }}>
              <Typography
                variant="h3"
                component="h2"
                href="/"
                sx={{fontWeight: 'bold' }}
              >Nuestros servicios
              </Typography>
            </Box>
          </Grid>
          <Grid item xs={12}>
            <Box sx={{ display: 'flex', justifyContent: 'center',marginTop:'50px' }}>
              <img src={BarraSeparacion}></img>
            </Box>
          </Grid>
          <Grid item xs={12}>
          <Box sx={{ display: 'flex', justifyContent: 'center',marginTop:'50px' }}>
              <Typography
                variant="p"
                component="p"
                href="/"
                sx={{color: '#7D7987', maxWidth:'60%', textAlign:'center'}}
              >Le brindamos las mejores opciones para usted. Ajústalo a tus necesidades de salud y metas personales  Puedes consultar con nosotros qué tipo de ejercicios son mejores para alcanzar tus objetivos.
              </Typography>
            </Box>
          </Grid>
        </Grid>
      </Box>
    </Box>
  );
}

export default MainPage