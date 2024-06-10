import * as React from 'react';
import { styled } from '@mui/material/styles';
import {
  Box,
  AppBar,
  Container,
  Typography,
  MenuItem,
  Button,
  Grid,
  IconButton,
  createTheme
} from '@mui/material';
import DownloadIcon from '@mui/icons-material/Download';

import LogoFitnessOscuro from '../../img/logo-fitness-tracker-oscuro.png'
import LogoFitnessBlanco from '../../img/logo-fitness-tracker.png'
import BackgroundPuntos from '../../img/background-puntos.png'
import RelojInicio from '../../img/reloj-inicio.png'
import BarraSeparacion from "../../img/barra-separacion.png"
import DecoracionServicios from "../../img/decoracion-nuestros-servicios.png"
import BusquedaInteligente from "../../img/busqueda-inteligente.png"
import SmartwatchIco from "../../img/smartwatch-ico.png"
import ConsultaPersonalizada from "../../img/consulta-personalizada.png"
import TrackingIco from "../../img/tracking-ico.png"
import CuentaIco from "../../img/cuenta-icono.png"
import InfoDetallada from "../../img/info-detallada.png"
import LideresSalud from "../../img/lideres-salud.png"
import SmartTraclerApp from "../../img/smart-tracker-app.png"
import BackgroundPuntosBlancos from "../../img/background-puntos-blancos.png"

function MainPage() {

  const ColorButton = styled(Button)(({ theme }) => ({
    color: '#436489',
    borderWidth: '2px',
    borderStyle: 'solid',
    borderRadius: '30px',
    paddingLeft: '15px',
    paddingRight: '15px',
    marginTop: '20px',
    fontWeight: 'bold',
    fontSize: '18pt',
    '&:hover': {
      backgroundColor: '#436489',
      color: 'white',
      '& .MuiSvgIcon-root': {
        color: 'white',
      }
    },
  }));

  const theme = createTheme();

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
            height: '70px',
          }}
        >
          <Box
            sx={{
              display: 'flex',
              alignItems: 'center',
              [theme.breakpoints.up('1200')]: {
                width: '50%',
              },
              [theme.breakpoints.down('1200')]: {
                width: '40%',
              },
              [theme.breakpoints.down('900')]: {
                width: '10%',
              },
            }}
          >
            <img src={LogoFitnessOscuro}
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
              sx={{
                color: '#436489',
                textDecoration: 'none',
                margin: '15px',
                fontWeight: 'bold',
                [theme.breakpoints.down('1200')]: {
                  fontSize: '20pt'
                },
                [theme.breakpoints.down('900')]: {
                  fontSize: '0pt'
                },
              }}
            >
              Fitness-Tracker
            </Typography>
          </Box>
          <Grid container columnSpacing={1}
            sx={{
              display: 'flex',
              color: '#436489',
              display: 'flex',
              marginRight:'30px',
              [theme.breakpoints.up('1200')]: {
                justifyContent: 'flex-end',
                alignContent: 'right',
                alignItems: 'center',
                width:'50%'
              },
              [theme.breakpoints.down('1200')]: {
                justifyContent: 'flex-end',
                alignContent: 'right',
                alignItems: 'center',
                width: '70%',
              },
              [theme.breakpoints.down('900')]: {
                justifyContent: 'flex-end',
                alignContent: 'right',
                alignItems: 'center',
                width: '100%',
              },
              
            }}
          >
            <Grid item lg={1.3} md={1.3} sm={1.3} xs={1.3}>
              <MenuItem sx={{ fontWeight: 'bold'}} href='#home' >Home</MenuItem>
            </Grid>
            <Grid item lg={3.5} md={3.5} sm={3.5} xs={2.5}>
              <MenuItem sx={{ fontWeight: 'bold',[theme.breakpoints.down('650')]: {fontSize:'0pt'}}} href='#servicios' >Nuestros Servicios</MenuItem>
            </Grid>
            <Grid item lg={3} md={3} sm={3} xs={2}>
              <MenuItem sx={{ fontWeight: 'bold',[theme.breakpoints.down('650')]: {fontSize:'0pt'} }} href='#sobreNosotros' >Sobre Nosotros</MenuItem>
            </Grid>
            <Grid item lg={1.3} md={1.3} sm={1.3} xs={2}>
              <MenuItem sx={{ fontWeight: 'bold',[theme.breakpoints.down('650')]: {fontSize:'0pt'} }} href='#app' >App</MenuItem>
            </Grid>
            <Grid item lg={2.5} md={2.5} sm={2.5} xs={4}>
              <Button
                href="/Register"
                sx={{
                  backgroundColor: '#293B50',
                  fontWeight: 'bold',
                  color: 'white',
                  borderRadius: '20px',
                  paddingLeft: '10px',
                  paddingRight: '10px',
                  fontSize: '18pt',
                  '&:hover': {
                    background: '#436489',
                  },
                }}>Register</Button>
            </Grid>
          </Grid>
        </Container>
      </AppBar>
      <Grid id='#home' container columnSpacing={2}>
        <Grid item lg={6} xs={12}>
          <Box sx={{ position: 'relative', marginTop: '40px' }}>
            <img src={BackgroundPuntos} style={{ position: 'absolute', width: '120px', left: '-40px' }}></img>
          </Box>
          <Typography
            variant="h2"
            component="h2"
            href="/"
            sx={{ marginTop: '150px', marginLeft: '200px', fontWeight: 'bold', fontSize: '60pt' }}
          >Virtualiza tu cuidado personal
          </Typography>
          <Typography
            variant="p"
            component="p"
            href="/"
            sx={{ marginTop: '20px', color: '#7D7987', fontSize: '15pt', marginLeft: '200px', maxWidth: '45%' }}>Fitness-Tracker provee de un seguimiento personal
            y de salud para tu beneficio personal en base a tus
            objetivos y metas</Typography>
          <Container sx={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
            <ColorButton>Consigue nuestra app</ColorButton>
          </Container>
        </Grid>
        <Grid item lg={6} xs={12}>
          <Box sx={{ display: 'flex', justifyContent: 'center', marginTop: '50px', width: '100%' }}>
            <img src={RelojInicio} style={{ width: '70%' }}></img>
          </Box>
        </Grid>
      </Grid>
      <Box sx={{ display: 'flex', justifyContent: 'center' }}>
        <Grid container id='#servicios'>
          <Grid item xs={12}>
            <Box sx={{ display: 'flex', justifyContent: 'center', marginTop: '100px' }}>
              <Typography
                variant="h3"
                component="h2"
                href="/"
                sx={{ fontWeight: 'bold' }}
              >Nuestros servicios
              </Typography>
            </Box>
          </Grid>
          <Grid item xs={12}>
            <Box sx={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
              <img src={BarraSeparacion}></img>
            </Box>
          </Grid>
          <Grid item xs={12}>
            <Box sx={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
              <Typography
                variant="p"
                component="p"
                href="/"
                sx={{ color: '#7D7987', maxWidth: '60%', textAlign: 'center' }}
              >Le brindamos las mejores opciones para usted. Ajústalo a tus necesidades de salud y metas personales  Puedes consultar con nosotros qué tipo de ejercicios son mejores para alcanzar tus objetivos.
              </Typography>
            </Box>
          </Grid>
          <Grid item xs={12}>
            <Box sx={{ position: 'relative', marginTop: "40px" }}>
              <img src={DecoracionServicios} style={{ position: 'absolute', width: "900px", left: '-150px', zIndex: '0' }}></img>
              <img src={BackgroundPuntos} style={{ position: 'absolute', width: "120px", right: '80px', top: '320px', zIndex: '0' }}></img>
            </Box>
            <Grid container columnSpacing={2} rowSpacing={3} sx={{ display: 'flex', justifyContent: 'center', paddingTop: '30px', paddingRight: '100px', paddingLeft: '100px' }}>
              <Grid item xl={4} lg={4} md={6} sx={{ display: 'flex', justifyContent: 'center', }}>
                <Box sx={{ width: '100%', height: 'auto', boxShadow: '3px 10px 50px rgba(0, 0, 0, 0.2)', background: 'white', borderRadius: '20px', zIndex: '1', padding: '30px' }}>
                  <img src={BusquedaInteligente} style={{ width: "70px" }}></img>
                  <Typography
                    variant="h3"
                    component="h3"
                    href="/"
                    sx={{ fontSize: '23pt', fontWeight: 'bold', marginTop: '30px' }}
                  >
                    Busqueda Inteligente
                  </Typography>
                  <Typography
                    variant="p"
                    component="p"
                    href="/"
                    sx={{ color: '#7D7987', marginTop: '17px', fontSize: '15pt' }}
                  >Encuentra y conoce todo aquello que necesites gracias a nuestra IA interactiva acerca de la salud y el fitness
                  </Typography>
                </Box>
              </Grid>
              <Grid item xl={4} lg={4} md={6} sx={{ display: 'flex', justifyContent: 'center', }}>
                <Box sx={{ width: '100%', height: 'auto', boxShadow: '3px 10px 50px rgba(0, 0, 0, 0.2)', background: 'white', borderRadius: '20px', zIndex: '1', padding: '30px' }}>
                  <img src={SmartwatchIco} style={{ width: "60px" }}></img>
                  <Typography
                    variant="h3"
                    component="h3"
                    href="/"
                    sx={{ fontSize: '23pt', fontWeight: 'bold', marginTop: '15px' }}
                  >
                    Smartwatch
                  </Typography>
                  <Typography
                    variant="p"
                    component="p"
                    href="/"
                    sx={{ color: '#7D7987', marginTop: '17px', fontSize: '15pt' }}
                  >Escoge cualquier smartwatch y comienza a usar la app para mejorar tu salud y alcanzar tus objetivos
                  </Typography>
                </Box>
              </Grid>
              <Grid item xl={4} lg={4} md={6} sx={{ display: 'flex', justifyContent: 'center', }}>
                <Box sx={{ width: '100%', height: 'auto', boxShadow: '3px 10px 50px rgba(0, 0, 0, 0.2)', background: 'white', borderRadius: '20px', zIndex: '1', padding: '30px' }}>
                  <img src={ConsultaPersonalizada} style={{ width: "55px" }}></img>
                  <Typography
                    variant="h3"
                    component="h3"
                    href="/"
                    sx={{ fontSize: '23pt', fontWeight: 'bold', marginTop: '20px' }}
                  >
                    Consulta Personalizada
                  </Typography>
                  <Typography
                    variant="p"
                    component="p"
                    href="/"
                    sx={{ color: '#7D7987', marginTop: '17px', fontSize: '15pt' }}
                  >Consulta gratuitamente todo aquello que necesites para alcanzar tus objetivos con nuestra IA
                  </Typography>
                </Box>
              </Grid>
              <Grid item xl={4} lg={4} md={6} xs={12} sx={{ display: 'flex', justifyContent: 'center', }}>
                <Box sx={{ width: '100%', height: 'auto', boxShadow: '3px 10px 50px rgba(0, 0, 0, 0.2)', background: 'white', borderRadius: '20px', zIndex: '1', padding: '30px' }}>
                  <img src={InfoDetallada} style={{ width: "70px" }}></img>
                  <Typography
                    variant="h3"
                    component="h3"
                    href="/"
                    sx={{ fontSize: '23pt', fontWeight: 'bold', marginTop: '30px' }}
                  >
                    Info Detallada
                  </Typography>
                  <Typography
                    variant="p"
                    component="p"
                    href="/"
                    sx={{ color: '#7D7987', marginTop: '17px', fontSize: '15pt' }}
                  >
                    Consulta la informacion acerca de tus entrenamientos y rendimiento diario
                  </Typography>
                </Box>
              </Grid>
              <Grid item xl={4} lg={4} md={6} sx={{ display: 'flex', justifyContent: 'center', }}>
                <Box sx={{ width: '100%', height: 'auto', boxShadow: '3px 10px 50px rgba(0, 0, 0, 0.2)', background: 'white', borderRadius: '20px', zIndex: '1', padding: '30px' }}>
                  <img src={CuentaIco} style={{ width: "110px" }}></img>
                  <Typography
                    variant="h2"
                    component="h3"
                    href="/"
                    sx={{ fontSize: '23pt', fontWeight: 'bold', marginTop: '10px' }}
                  >
                    Cuenta
                  </Typography>
                  <Typography
                    variant="p"
                    component="p"
                    href="/"
                    sx={{ color: '#7D7987', marginTop: '17px', fontSize: '15pt' }}
                  >
                    Accede a tus registros desde cualquier lugar y dispositivo descargandote la app e iniciando sesion o desde nuestra web
                  </Typography>
                </Box>
              </Grid>
              <Grid item xl={4} lg={4} md={6} xs={12} sx={{ display: 'flex', justifyContent: 'center', }}>
                <Box sx={{ width: '100%', height: 'auto', boxShadow: '3px 10px 50px rgba(0, 0, 0, 0.2)', background: 'white', borderRadius: '20px', zIndex: '1', padding: '30px' }}>
                  <img src={TrackingIco} style={{ width: "80px" }}></img>
                  <Typography
                    variant="h3"
                    component="h3"
                    href="/"
                    sx={{ fontSize: '23pt', fontWeight: 'bold', marginTop: '20px' }}
                  >
                    Tracking
                  </Typography>
                  <Typography
                    variant="p"
                    component="p"
                    href="/"
                    sx={{ color: '#7D7987', marginTop: '20px', fontSize: '15pt' }}
                  >
                    Trackea tus entrenamientos, tu dia a dia ¡¡INCLUSO TUS PERIODOS DE SUEÑO!!
                  </Typography>
                </Box>
              </Grid>
            </Grid>
          </Grid>
          <Grid item xs={12}>
            <Box sx={{ display: 'flex', justifyContent: 'center', marginTop: '100px' }}>
              <img src={BarraSeparacion} style={{ width: '30%', height: '3px' }}></img>
            </Box>
          </Grid>
          <Grid item xs={12} fullWidth sx={{ marginTop: '100px' }}>
            <Grid container fullWidth>
              <Grid item md={6} xs={12}>
                <img src={LideresSalud} style={{ width: '95%' }}></img>
              </Grid>
              <Grid item md={6} xs={12}>
                <Box sx={{ marginTop: '60px', marginLeft: '30px' }}>
                  <Typography
                    variant="h3"
                    component="h3"
                    href="/"
                    sx={{ fontWeight: 'bold', fontSize: '50pt' }}
                  >Lideres en salud y fitness con nuevas tecnologias
                  </Typography>
                  <Box sx={{ display: 'flex', justifyContent: 'start', marginTop: '30px' }}>
                    <img src={BarraSeparacion} style={{ width: '60px', height: '3px' }}></img>
                  </Box>
                  <Typography
                    variant="p"
                    component="p"
                    href="/"
                    sx={{ color: '#7D7987', maxWidth: '80%', textAlign: 'left', marginTop: '30px', fontSize: '18pt' }}
                  >Le brindamos las mejores opciones para usted. Ajústalo a tus necesidades de salud y metas personales  Puedes consultar con nosotros qué tipo de ejercicios son mejores para alcanzar tus objetivos.
                  </Typography>
                </Box>
              </Grid>
            </Grid>
          </Grid>
          <Grid item xs={12}>
            <Box sx={{ display: 'flex', justifyContent: 'center', marginTop: '100px' }}>
              <img src={BarraSeparacion} style={{ width: '30%', height: '3px' }}></img>
            </Box>
          </Grid>
          <Grid item xs={12} fullWidth sx={{ marginTop: '100px' }}>
            <Grid container fullWidth>

              <Grid item md={6} xs={12}>
                <Box sx={{ marginTop: '60px', marginLeft: '90px' }}>
                  <Typography
                    variant="h3"
                    component="h3"
                    href="/"
                    sx={{ fontWeight: 'bold', fontSize: '50pt' }}
                  >Descarga nuestra app para dispositivos moviles
                  </Typography>
                  <Box sx={{ display: 'flex', justifyContent: 'start', marginTop: '30px' }}>
                    <img src={BarraSeparacion} style={{ width: '60px', height: '3px' }}></img>
                  </Box>
                  <Typography
                    variant="p"
                    component="p"
                    href="/"
                    sx={{ color: '#7D7987', maxWidth: '80%', textAlign: 'left', marginTop: '30px', fontSize: '18pt' }}
                  >Nuestra aplicación dedicada a la recolección de datos a través del software del smartwatch y soluciones para salud, nutrición y rutinas fitness para el beneficio personal del usuaio. Comienza a alcanzar tus objetivos.
                  </Typography>
                  <ColorButton endIcon={<DownloadIcon />}>
                    Descargar
                  </ColorButton>
                  {/* sx={{color:'#436489',fontWeight:'bold',borderRadius:'30px',marginTop:'30px',borderWidth:'2px',borderColor:'#436489'}}  */}
                </Box>
              </Grid>
              <Grid item md={6} xs={12}>
                <img src={SmartTraclerApp} style={{ width: '95%', marginLeft: '30px' }}></img>
              </Grid>
            </Grid>
          </Grid>
          <Grid item xs={12}>
            <Box sx={{ background: 'linear-gradient(to top, #436388 0%,#4F7198 50%, #436388 100%)', width: '100%' }}>
              <Grid container columnSpacing={3} rowSpacing={2} sx={{ padding: '50px', verticalAlign: 'center', paddingTop: '30px', paddingBottom: '100px', marginTop: '100px' }}>
                <Box sx={{ position: 'relative', width: '100%', height: '100%' }}>
                  <img src={BackgroundPuntos} style={{ position: 'absolute', width: '120px', right: '0px', top: '-160px' }}></img>
                </Box>
                <Grid item lg={4} md={6} sm={6}>
                  <Box
                    sx={{
                      display: 'flex',
                      alignItems: 'center',
                    }}
                  >
                    <img src={LogoFitnessBlanco}
                      title="Logo Fitness-Tracker"
                      alt="Logo Fitness-Tracker App"
                      style={{
                        width: '100px',
                        height: '100px',
                        position: 'block',
                      }}
                    />
                    <Typography
                      variant="h3"
                      component="h3"
                      href="/"
                      sx={{
                        color: 'white',
                        textDecoration: 'none',
                        marginLeft: '15px',
                        fontSize: '40pt'
                      }}
                    >
                      Fitness-Tracker
                    </Typography>
                  </Box>
                  <Typography
                    variant="p"
                    component="p"
                    sx={{ color: '#FFF', textAlign: 'left', width: '65%', marginTop: '15px', marginLeft: '100px', fontSize: '15pt', fontWeight: 'light' }}
                  >Fitness-Tracker provee de soluciones saludables para tu beneficio personal
                  </Typography>
                </Grid>
                <Grid item lg={4} md={3} sm={3} sx={{ marginTop: '20px' }}>
                  <Typography
                    variant="h3"
                    component="h3"
                    sx={{ color: '#FFF', textAlign: 'left', marginTop: '15px', fontSize: '25pt', fontWeight: 'bold' }}
                  >Compañia
                  </Typography>
                  <Typography
                    variant="p"
                    component="p"
                    sx={{ color: '#FFF', textAlign: 'left', marginTop: '15px', fontSize: '15pt', fontWeight: 'light' }}
                  >Sobre nosotros
                  </Typography>
                  <Typography
                    variant="p"
                    component="p"
                    sx={{ color: '#FFF', textAlign: 'left', marginTop: '15px', fontSize: '15pt', fontWeight: 'light' }}
                  >App
                  </Typography>
                </Grid>
                <Grid item lg={4} md={3} sm={3} sx={{ marginTop: '20px' }}>
                  <Typography
                    variant="h3"
                    component="h3"
                    sx={{ color: '#FFF', textAlign: 'left', marginTop: '15px', fontSize: '25pt', fontWeight: 'bold' }}
                  >Region
                  </Typography>
                  <Typography
                    variant="p"
                    component="p"
                    sx={{ color: '#FFF', textAlign: 'left', marginTop: '15px', fontSize: '15pt', fontWeight: 'light' }}
                  >España
                  </Typography>
                </Grid>
                <Box sx={{ position: 'relative', width: '100%', height: '100%' }}>
                  <img src={BackgroundPuntosBlancos} style={{ position: 'absolute', width: '120px', left: '-70px', bottom: '-100px' }}></img>
                </Box>
              </Grid>
            </Box>
          </Grid>
        </Grid>
      </Box>
    </Box>
  );
}
export default MainPage